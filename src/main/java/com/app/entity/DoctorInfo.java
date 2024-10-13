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
 * @since 2024-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("doctor_info")
public class DoctorInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 医生编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 医生姓名
     */
    @TableField("name")
    private String name;

    /**
     * 医生职位
     */
    @TableField("job")
    private String job;

    /**
     * 医生头像
     */
    @TableField("pic")
    private String pic;

    /**
     * 擅长
     */
    @TableField("excel")
    private String excel;

    /**
     * 个人简介
     */
    @TableField("info")
    private String info;


}
