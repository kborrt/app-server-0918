package com.app.mapper;

import com.app.entity.Orders;
import com.app.entity.request.CheckAvailabilityDto;
import com.app.entity.request.PlaceOrderDto;
import com.app.entity.response.OrderConutByDateVo;
import com.app.entity.response.OrderOfSetmealVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Byterain
 * @since 2024-09-23
 */
@Repository
public interface OrdersMapper extends BaseMapper<Orders> {
    //自定义查询方法:
    //OrderConutByDateVo自定义实体类： 包含 订单日期 该日期实际下单量

    @Select("SELECT DATE(order_date) AS orderDate,count(*) AS orderCount FROM orders WHERE hp_id = #{temp.hpId} and sm_id = #{temp.smId} and order_date BETWEEN #{temp.startDate} AND #{temp.endDate} GROUP BY DATE(order_date)")
    List<OrderConutByDateVo> selectOrderConutByDateRange(@Param("temp") CheckAvailabilityDto temp);

    @Select("SELECT count(*)  FROM orders WHERE hp_id = #{temp.hpId} and sm_id = #{temp.smId} and order_date = #{temp.orderDate}")
    int selectOrderCountByDate(@Param("temp") PlaceOrderDto temp);

    @Select("SELECT o.*, s.name AS setmealName FROM orders o LEFT JOIN setmeal s ON o.sm_id = s.sm_id WHERE o.user_id = #{userId}")
    List<OrderOfSetmealVo> selectOrderWithSetmeal(String userId);


}

