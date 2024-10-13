package com.app.entity.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;
@Data
public class QuestionVo {
    public String name;
    public List<item> items;
    @Data
    public static class item{
        private Integer id;
        private String title;
        private String type;
        private List<String> options;
    }

}
