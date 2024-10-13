package com.app.service;

import com.app.common.Result;
import com.app.entity.Orders;
import com.app.entity.request.CheckAvailabilityDto;
import com.app.entity.request.PlaceOrderDto;
import com.app.entity.response.AvailabilityVo;
import com.app.entity.response.OrderDetailVo;
import com.app.entity.response.OrderOfSetmealVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Byterain
 * @since 2024-09-23
 */
public interface OrdersService extends IService<Orders> {
    //获得万年历 和 往后30天的体检预约量
    public Result<AvailabilityVo> checkAvailability(CheckAvailabilityDto dto);

    //创建订单方法
    public Result<OrderDetailVo> placeOrder(PlaceOrderDto dto);

    //支付订单
    public Result<String> pay(Integer orderId);
    //取消订单
    public Result<String> cancel(Integer orderId);
    //申请退款
    public Result<String> applyRefund(Integer orderId);


    //根据用户的编号 查询该用户所有的订单数据
    public Result<List<OrderOfSetmealVo>> selectOrder(String userId);

    //根据 订单号和手机号查询订单详细
    public Result<OrderDetailVo> selectByOrderId(Integer orderId,String userId);

}
