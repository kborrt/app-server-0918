package com.app.entity.response;

import com.app.entity.NurseCategory;
import lombok.Data;

import java.util.List;

@Data
public class NurseHomeVo extends NurseCategory {
    private List<item> items;

    @Data
    public static class item{
        private int id;
        private String name;
        private Double price;
        private String imageUrl;
    }

}

