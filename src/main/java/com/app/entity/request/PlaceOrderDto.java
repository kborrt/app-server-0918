package com.app.entity.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
public class PlaceOrderDto {
    private String userId;
    private Integer hpId;
    private Integer smId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;
}
