package com.app.service;

import com.app.common.Result;
import com.app.entity.NurseOrder;
import com.app.entity.request.NurseOrderDto;
import com.app.entity.response.NurseOrderListVo;
import com.app.entity.response.NurseOrderVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Byterain
 * @since 2024-09-24
 */
public interface NurseOrderService extends IService<NurseOrder> {
    //保存订单
    public Result<String> placeOrder(NurseOrderDto nod);

    //查询所有订单
    public Result<List<NurseOrderListVo>> getAll();

    //根据id查询订单
    public Result<NurseOrderVo> getOne(Integer noId);

    //支付订单
    public Result<String> pay(Integer orderId);
    //取消订单
    public Result<String> cancel(Integer orderId);
    //申请退款
    public Result<String> applyRefund(Integer orderId);

}
