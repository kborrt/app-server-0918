package com.app.controller;


import com.app.common.Result;
import com.app.entity.request.NurseOrderDto;
import com.app.entity.response.NurseOrderListVo;
import com.app.entity.response.NurseOrderVo;
import com.app.service.NurseOrderService;
import lombok.extern.slf4j.Slf4j;
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
 * @since 2024-09-24
 */
@RestController
@RequestMapping("/nurseOrder")
@Slf4j
public class NurseOrderController extends BaseController {
    @Autowired
    private NurseOrderService nurseOrderService;
    @PostMapping("placeOrder")
    public Result<String> placeOrder(@RequestBody NurseOrderDto nod) {
        log.info(String.valueOf(nod));
        return nurseOrderService.placeOrder(nod);
    }

    @GetMapping("getAll")
    public Result<List<NurseOrderListVo>> getAll() {
        return nurseOrderService.getAll();
    }

    @GetMapping("/getOne")
    public Result<NurseOrderVo> getOne(@RequestParam("noId") Integer noId) {
        return nurseOrderService.getOne(noId);
    }

    //支付订单
    @PostMapping("/pay/{orderId}")
    public Result<String> pay(@PathVariable Integer orderId){
        return nurseOrderService.pay(orderId);
    }
    //取消订单
    @PostMapping("/cancel/{orderId}")
    public Result<String> cancelOrder(@PathVariable Integer orderId){
        return nurseOrderService.cancel(orderId);
    }
    //申请退款
    @PostMapping("/applyRefund/{orderId}")
    public Result<String> applyRefund(@PathVariable Integer orderId){
        return nurseOrderService.applyRefund(orderId);
    }


}
