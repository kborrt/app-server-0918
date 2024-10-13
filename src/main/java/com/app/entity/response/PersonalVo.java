package com.app.entity.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonalVo {
    private String userId;
    private String realName;
    private Integer sex;
    private String identityCard;
    private LocalDate birthday;
    private Integer userType;
}
