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
 * @since 2024-09-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("setmeal")
public class Setmeal implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 体检套餐编号
     */
    @TableId(value = "sm_id", type = IdType.AUTO)
    private Integer smId;

    /**
     * 体检套餐名称
     */
    @TableField("name")
    private String name;

    /**
     * 体检套餐类型(1 男士套餐 0 女士套餐)
     */
    @TableField("type")
    private Integer type;

    /**
     * 体检套餐价格
     */
    @TableField("price")
    private Integer price;


}
