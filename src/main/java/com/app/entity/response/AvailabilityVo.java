package com.app.entity.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

//万年历请求数据 返回封装类
@Data
public class AvailabilityVo {
    private int year;
    private int month;
    private List<DayAvailability> days;

    //内部类
    @Data
    public static class DayAvailability {
        private LocalDate date;//日期
        private int remainingSlots;//剩余预约量
        private boolean open;//是否可预约
    }
}
