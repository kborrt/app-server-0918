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
@TableName("questionnaire")
public class Questionnaire implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 问卷编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 问卷名称
     */
    @TableField("name")
    private String name;

    /**
     * 问卷图片
     */
    @TableField("imageUrl")
    private String imageurl;


}
