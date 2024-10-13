package com.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
 * @since 2024-09-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("nurse_order")
public class NurseOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单编号
     */
    @TableId(value = "no_id", type = IdType.AUTO)
    private Integer noId;

    /**
     * 账号id
     */
    @TableField("user_id")
    private String userId;

    /**
     * 联系号码
     */
    @TableField("tel")
    private String tel;

    /**
     * 真实姓名
     */
    @TableField("realName")
    private String realname;

    /**
     * 年龄
     */
    @TableField("age")
    private Integer age;

    /**
     * 性别（0男1女）
     */
    @TableField("sex")
    private Integer sex;

    /**
     * 身份证号
     */
    @TableField("identityCard")
    private String identitycard;

    /**
     * 地址
     */
    @TableField("address")
    private String address;

    /**
     * 上门日期
     */
    @TableField("date")
    private LocalDate date;

    /**
     * 服务编号
     */
    @TableField("ni_id")
    private Integer niId;

    /**
     * 订单状态（1 未归档 2 已归档）
     */
    @TableField("state")
    private Integer state;


}
