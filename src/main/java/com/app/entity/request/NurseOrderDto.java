package com.app.entity.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NurseOrderDto {
    private String tel;
    private String realName;
    private Integer age;
    private Integer sex;
    private String identityCard;
    private NurseOrderDto.address address;
    private LocalDate date;
    private Integer niId;


    @Data
    public static class address{
        private String province;
        private String city;
        private String district;
        private String detail;
    }


}
