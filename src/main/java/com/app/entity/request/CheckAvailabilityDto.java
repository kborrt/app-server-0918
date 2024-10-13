package com.app.entity.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
//该实体类用于接收万年历页面传递参数
@Data
public class CheckAvailabilityDto {
    private Integer hpId;
    private Integer smId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
}
