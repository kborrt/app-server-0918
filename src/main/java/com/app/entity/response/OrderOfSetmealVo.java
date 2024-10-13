package com.app.entity.response;

import com.app.entity.Orders;
import lombok.Data;

@Data
public class OrderOfSetmealVo extends Orders {
    private String setmealName;
}
