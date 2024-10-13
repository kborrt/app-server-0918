package com.app.entity.response;

import lombok.Data;
@Data
public class NurseDetailVo {
    private String category;
    private NurseDetailVo.item item;
    @Data
    public static class item{
        private int id;
        private String name;
        private Double price;
        private String imageUrl;
        private String illustrate;
        private String info;
    }
}
