package com.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Byterain
 * @since 2024-09-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("question")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 题目编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 题目
     */
    @TableField("title")
    private String title;

    /**
     * 类型
     */
    @TableField("type")
    private String type;

    /**
     * 选项
     */
    @TableField("options")
    private String options;

    /**
     * 从属问卷
     */
    @TableField("qn_id")
    private Integer qnId;


}
