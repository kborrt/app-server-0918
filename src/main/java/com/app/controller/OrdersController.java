package com.app.controller;


import com.app.common.Result;
import com.app.entity.request.CheckAvailabilityDto;
import com.app.entity.request.PlaceOrderDto;
import com.app.entity.response.AvailabilityVo;
import com.app.entity.response.OrderDetailVo;
import com.app.entity.response.OrderOfSetmealVo;
import com.app.service.OrdersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.app.common.BaseController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Byterain
 * @since 2024-09-23
 */
@RestController
@RequestMapping("/orders")
public class OrdersController extends BaseController {
    @Autowired
    private OrdersService ordersService;
    @GetMapping("/checkAvailability")
    public Result<AvailabilityVo> checkAvailability(CheckAvailabilityDto dto) {
        return ordersService.checkAvailability(dto);

    }
    //生成订单
    @PostMapping("/placeOrder")
    public Result<OrderDetailVo> placeOrder(@RequestBody PlaceOrderDto dto){
        return ordersService.placeOrder(dto);
    }
    //支付订单
    @PostMapping("/pay/{orderId}")
    public Result<String> pay(@PathVariable Integer orderId){
        return ordersService.pay(orderId);
    }
    //取消订单
    @PostMapping("/cancel/{orderId}")
    public Result<String> cancelOrder(@PathVariable Integer orderId){
        return ordersService.cancel(orderId);
    }
    //申请退款
    @PostMapping("/applyRefund/{orderId}")
    public Result<String> applyRefund(@PathVariable Integer orderId){
        return ordersService.applyRefund(orderId);
    }
    //查看当前用户所有的订单
    @GetMapping("/selectOrder/{userId}")
    public Result<List<OrderOfSetmealVo>> selectOrder(@PathVariable String userId){
        return ordersService.selectOrder(userId);
    }

    //根据orderId和UserId查询订单详细信息
    @GetMapping("/selectByOrderId/{orderId}/{userId}")
    public Result<OrderDetailVo> selectByOrderId(@PathVariable Integer
                                                         orderId,@PathVariable String userId){
        return ordersService.selectByOrderId(orderId,userId);
    }

}
