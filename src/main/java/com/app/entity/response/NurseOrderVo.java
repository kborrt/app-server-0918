package com.app.entity.response;

import lombok.Data;

import java.time.LocalDate;
@Data
public class NurseOrderVo {
    private Integer id;
    private String tel;
    private String realName;
    private Integer age;
    private Integer sex;
    private String identityCard;
    private String address;
    private LocalDate date;
    private Integer state;
    private String item_name;
}
