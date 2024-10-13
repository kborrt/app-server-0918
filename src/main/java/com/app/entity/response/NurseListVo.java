package com.app.entity.response;

import lombok.Data;

import java.util.List;
@Data
public class NurseListVo {
    private List<NurseListVo.item> items;

    @Data
    public static class item{
        private int id;
        private String name;
        private Double price;
        private String imageUrl;

    }
}
