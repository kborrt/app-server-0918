package com.app.entity.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderConutByDateVo {
    //订单预约日期
    private LocalDate orderDate;
    //订单数量
    private int orderCount;
}
