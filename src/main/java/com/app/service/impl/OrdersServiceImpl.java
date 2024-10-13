package com.app.service.impl;

import com.app.common.Result;
import com.app.entity.Hospital;
import com.app.entity.Orders;
import com.app.entity.Users;
import com.app.entity.request.CheckAvailabilityDto;
import com.app.entity.request.PlaceOrderDto;
import com.app.entity.response.*;
import com.app.exception.CommonException;
import com.app.mapper.HospitalMapper;
import com.app.mapper.OrdersMapper;
import com.app.mapper.UsersMapper;
import com.app.service.HospitalService;
import com.app.service.OrdersService;
import com.app.utils.UserContext;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Byterain
 * @since 2024-09-23
 */
@Service
@Slf4j
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {
    @Autowired
    private CalendarServiceImpl calendarService;
    @Autowired
    private HospitalService hospitalService;
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private HospitalMapper hospitalMapper;
    @Autowired
    private SetmealServiceImpl setmealService;

    //将医院每周体检量转换为一个Map集合
    private static Map<Integer, Integer> parseRuleToMap(String rule){
        //200,100,100,100,100,100,100字符串按照逗号分割，转换为数组
        String[] num=rule.split(",");
        Map<Integer, Integer> weekAvailability=new HashMap<>();
        for(int i=0;i<num.length;i++){
            //数组下标作为Map的key，数组值作为Map的value
            weekAvailability.put(i, Integer.parseInt(num[i]));
        }
        return weekAvailability;
    }

    //将医院每周每天 最大预约量填入万年历日期中
    //Map<LocalDate,Interger>第一个参数对应日期，第二个参数对应最大预约量
    private static Map<LocalDate, Integer> initAvailability(LocalDate startDate, Map<Integer, Integer> weekAvailability){
        //当前往后30天，每天医院的最大预约量的集合
        Map<LocalDate, Integer> availability = new HashMap<>();
        LocalDate date=startDate;
        LocalDate endDate=startDate.plusDays(30);
        while(!startDate.isAfter(endDate)){
            DayOfWeek dayOfWeek = startDate.getDayOfWeek(); // getDayOfWeek()方法获取星期几英文单词
            int weekValue = dayOfWeek.getValue(); //星期二 2 星期日 7
            weekValue=(weekValue==7)?0:weekValue;
            //按照weekValue中的值，作为map的key，获取对应的最大预约量
            int count=weekAvailability.get(weekValue);
            //将日期和最大预约量放入Map中
            availability.put(startDate, count);
            //循环一次，日期+1
            startDate=startDate.plusDays(1);

        }
        return availability;
    }

    //获得万年历 和 往后30天的 体检预约量
    @Override
    public Result<AvailabilityVo> checkAvailability(CheckAvailabilityDto dto) {
        //查询医院每天最大预约数
        //getById()方法是业务逻辑层mybaties-plus自动生成的，可以直接使用
        Hospital hospital = hospitalService.getById(dto.getHpId());
        String rule=hospital.getRule();
        Map<Integer, Integer> weekAvailability = parseRuleToMap(rule);

        //计算每月每天 预约余量
        LocalDate nowDate = LocalDate.now();
        Map<LocalDate, Integer> availability = initAvailability(nowDate, weekAvailability);

        //求实际每天预约量，查询订单数量
        dto.setEndDate(dto.getStartDate().plusDays(30));
        CheckAvailabilityDto temp=new CheckAvailabilityDto();
        //复制dto对象
        BeanUtils.copyProperties(dto,temp);
        temp.setStartDate(nowDate);
        //查询日期以及 实际下单量
        List<OrderConutByDateVo> odcList = ordersMapper.selectOrderConutByDateRange(temp);
        for(OrderConutByDateVo ocd : odcList){
            //已经下过单的日期 ，每天最大医院的预约量
            //23(200),24(100),25(100),26....
            //9-25 3
            Integer num = availability.getOrDefault(ocd.getOrderDate(),0);
            availability.put(ocd.getOrderDate(),num - ocd.getOrderCount());
        }

        //构建万年历数据
        int year=dto.getStartDate().getYear();
        int month=dto.getStartDate().getMonthValue();

        AvailabilityVo availabilityVo = calendarService.getAvailability(year, month);

        for(AvailabilityVo.DayAvailability d : availabilityVo.getDays()){
            LocalDate date = d.getDate();
            Integer count = availability.get(date); //按照日期取 实际预约量量
            if(count==null || count<0){
                d.setOpen(false);
            }else{
                d.setRemainingSlots(count);
                d.setOpen(true);
            }
        }
        return Result.ok(availabilityVo);
    }

    //查询新订单的详细数据(包括 用户、医院、体检项明细)
    private OrderDetailVo getOrderDetail(Integer orderId){
        OrderDetailVo vo = new OrderDetailVo();
        Orders orders = ordersMapper.selectById(orderId);
        BeanUtils.copyProperties(orders,vo);
        //查询用户的详细数据
        Users users = usersMapper.selectById(UserContext.currentUserId());
        vo.setUsers(users);
        //查询医院的详细数据
        Hospital hospital = hospitalMapper.selectById(orders.getHpId());
        vo.setHospital(hospital);
        //查询体检项和相关明细
        SetmealVo setmealVo = setmealService.getSetmealWithCheckItems(orders.getSmId());
        vo.setSetmealVo(setmealVo);
        return vo;
    }
    //创建新订单
    private Orders createNewOrder(String userId,PlaceOrderDto dto){
        Orders orders = new Orders();
        orders.setUserId(userId);
        orders.setHpId(dto.getHpId());
        orders.setSmId(dto.getSmId());
        orders.setOrderDate(dto.getOrderDate());
        orders.setState(1); //1表示下单 但是未支付
        ordersMapper.insert(orders);
        return orders;
    }
    //检查是否已经存在该订单
    private Orders checkExistsOrder(String userId,PlaceOrderDto dto){
        QueryWrapper<Orders> qw = new QueryWrapper<>();
        qw.eq("user_id",userId)
                .eq("hp_id",dto.getHpId())
                .eq("sm_id",dto.getSmId())
                .eq("order_date",dto.getOrderDate())
                .eq("state",1);
        return  ordersMapper.selectOne(qw);
    }
    //检测下单日期，医院的实际余量，防止超卖
    private Integer checkOneDay(PlaceOrderDto dto){
        //TODO: 返回下单日期 下单这个医院这天实际余量
        LocalDate Date = dto.getOrderDate();
        DayOfWeek dayOfWeek = Date.getDayOfWeek(); // getDayOfWeek()方法获取星期几英文单词
        int weekValue = dayOfWeek.getValue();
        weekValue=(weekValue==7)?0:weekValue;
        Hospital hospital = hospitalService.getById(dto.getHpId());
        String rule=hospital.getRule();
        Map<Integer, Integer> weekAvailability = parseRuleToMap(rule);
        int count=weekAvailability.get(weekValue);
        PlaceOrderDto temp=new PlaceOrderDto();
        //复制dto对象
        BeanUtils.copyProperties(dto,temp);
        int num = ordersMapper.selectOrderCountByDate(temp);
        return count-num;
    }

    @Override
    public Result<OrderDetailVo> placeOrder(PlaceOrderDto dto) {
        String userId = UserContext.currentUserId(); //直接从线程取得 用户id
        //TODO:设置锁： Redis数据库 分布式锁。
        //TODO: 检查是否已经存在该订单
        Orders one =checkExistsOrder(userId,dto);
        if(one!=null){
            //如果已存在该订单信息，就不再录入，直接返回订单信息
            OrderDetailVo vo=getOrderDetail(one.getOrderId());
            return Result.ok(vo);
        }
        //TODO: 检查预约余量
        Integer count = checkOneDay(dto);
        if(count<=0){
            return Result.error("预约余量不足，请选择其他日期或医院");
        }
        //创建新订单:
        Orders orders = createNewOrder(userId, dto);
        log.info("新订单的编号是:"+orders.getOrderId());
        //返回创建订单信息
        OrderDetailVo orderDetailVo = getOrderDetail(orders.getOrderId());
        return Result.ok(orderDetailVo);
    }

    @Override
    public Result<String> pay(Integer orderId) {
        Orders one = ordersMapper.selectById(orderId);
        one.setState(2);
        ordersMapper.updateById(one);
        return Result.ok("支付成功");
    }
    @Override
    public Result<String> cancel(Integer orderId) {
        Orders one = ordersMapper.selectById(orderId);
        one.setState(6);
        ordersMapper.updateById(one);
        return Result.ok("取消成功");
    }
    @Override
    public Result<String> applyRefund(Integer orderId) {
        Orders one = ordersMapper.selectById(orderId);
        one.setState(7);
        ordersMapper.updateById(one);
        return Result.ok("退款成功");
    }



    @Override
    public Result<List<OrderOfSetmealVo>> selectOrder(String userId) {
        List<OrderOfSetmealVo> orderOfSetmealVo = ordersMapper.selectOrderWithSetmeal(userId);
        return Result.ok(orderOfSetmealVo);
    }

    @Override
    public Result<OrderDetailVo> selectByOrderId(Integer orderId, String userId) {
        //直接调用之前 已经封装好的方法 getOrderDetail()
        //该方法 查询订单详细数据（包括用户具体信息，医院具体信息，体检套餐具体信息（体检明细））
        OrderDetailVo orderDetailVo = this.getOrderDetail(orderId);
        return Result.ok(orderDetailVo);
    }
}
