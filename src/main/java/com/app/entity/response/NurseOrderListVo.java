package com.app.entity.response;

import lombok.Data;

import java.time.LocalDate;
@Data
public class NurseOrderListVo {
    private Integer no_id;
    private String name;
    private LocalDate date;
    private Integer state;
}
