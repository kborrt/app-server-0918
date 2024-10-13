package com.app.service.impl;

import com.app.common.Result;
import com.app.entity.NurseItem;
import com.app.entity.NurseOrder;
import com.app.entity.Orders;
import com.app.entity.request.NurseOrderDto;
import com.app.entity.request.PlaceOrderDto;
import com.app.entity.response.NurseOrderListVo;
import com.app.entity.response.NurseOrderVo;
import com.app.mapper.NurseItemMapper;
import com.app.mapper.NurseOrderMapper;
import com.app.service.NurseOrderService;
import com.app.utils.UserContext;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Byterain
 * @since 2024-09-24
 */
@Service
@Slf4j
public class NurseOrderServiceImpl extends ServiceImpl<NurseOrderMapper, NurseOrder> implements NurseOrderService {
    @Autowired
    private NurseOrderMapper nurseOrderMapper;
    @Autowired
    private NurseItemMapper nurseItemMapper;
    //创建新订单
    private NurseOrder createNewOrder(String userId, NurseOrderDto nod) {
        NurseOrder order = new NurseOrder();
        String Address = nod.getAddress().getProvince()+nod.getAddress().getCity()+nod.getAddress().getDistrict()+nod.getAddress().getDetail();
        order.setUserId(userId);
        order.setTel(nod.getTel());
        order.setRealname(nod.getRealName());
        order.setAge(nod.getAge());
        order.setSex(nod.getSex());
        order.setIdentitycard(nod.getIdentityCard());
        order.setAddress(Address);
        order.setDate(nod.getDate());
        order.setNiId(nod.getNiId());
        order.setState(1);

        nurseOrderMapper.insert(order);
        return order;
    }
    @Override
    public Result<String> placeOrder(NurseOrderDto nod) {
        String userId = UserContext.currentUserId(); //直接从线程取得 用户id
        //TODO: 检查是否已经存在该订单
        NurseOrder order = createNewOrder(userId, nod);
        log.info("新订单的编号是:"+order.getNoId());
        return Result.ok("订单创建成功");
    }

    //查询所有订单

    @Override
    public Result<List<NurseOrderListVo>> getAll() {
        List<NurseOrderListVo> orderList = new ArrayList<>();
        String userId = UserContext.currentUserId(); //直接从线程取得 用户id
        QueryWrapper<NurseOrder> qw = new QueryWrapper<>();
        qw.eq("user_id",userId);
        List<NurseOrder> orders = nurseOrderMapper.selectList(qw);
        for (NurseOrder order : orders) {
            Integer niId=order.getNiId();
            NurseItem item=nurseItemMapper.selectById(niId);
            NurseOrderListVo vo=new NurseOrderListVo();
            vo.setNo_id(order.getNoId());
            vo.setName(item.getName());
            vo.setDate(order.getDate());
            vo.setState(order.getState());
            orderList.add(vo);
        }
        return Result.ok(orderList);
    }

    //查询单个订单

    @Override
    public Result<NurseOrderVo> getOne(Integer noId) {
        String userId = UserContext.currentUserId(); //直接从线程取得 用户id
        QueryWrapper<NurseOrder> qw = new QueryWrapper<>();
        qw.eq("user_id",userId)
                .eq("no_id",noId);
        NurseOrder order = nurseOrderMapper.selectOne(qw);
        if(order!=null){
            NurseItem item=nurseItemMapper.selectById(order.getNiId());
            NurseOrderVo vo=new NurseOrderVo();
            vo.setId(order.getNoId());
            vo.setTel(order.getTel());
            vo.setRealName(order.getRealname());
            vo.setAge(order.getAge());
            vo.setSex(order.getSex());
            vo.setIdentityCard(order.getIdentitycard());
            vo.setAddress(order.getAddress());
            vo.setDate(order.getDate());
            vo.setState(order.getState());
            vo.setItem_name(item.getName());
            return Result.ok(vo);
        }else{
            return Result.error(3000,"订单不存在");
        }
    }

    @Override
    public Result<String> pay(Integer noid) {
        NurseOrder one = nurseOrderMapper.selectById(noid);
        one.setState(2);
        nurseOrderMapper.updateById(one);
        return Result.ok("支付成功");
    }
    @Override
    public Result<String> cancel(Integer orderId) {
        NurseOrder one = nurseOrderMapper.selectById(orderId);
        one.setState(6);
        nurseOrderMapper.updateById(one);
        return Result.ok("取消成功");
    }
    @Override
    public Result<String> applyRefund(Integer orderId) {
        NurseOrder one = nurseOrderMapper.selectById(orderId);
        one.setState(7);
        nurseOrderMapper.updateById(one);
        return Result.ok("退款成功");
    }
}




