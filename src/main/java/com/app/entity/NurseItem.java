package com.app.entity;

import java.math.BigDecimal;
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
 * @since 2024-09-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("nurse_item")
public class NurseItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 服务编号
     */
    @TableId(value = "ni_id", type = IdType.AUTO)
    private Integer niId;

    /**
     * 服务名称
     */
    @TableField("name")
    private String name;

    /**
     * 服务价格
     */
    @TableField("price")
    private Double price;

    /**
     * 服务图片路径
     */
    @TableField("imageUrl")
    private String imageurl;

    /**
     * 服务说明
     */
    @TableField("illustrate")
    private String illustrate;

    /**
     * 服务内容
     */
    @TableField("info")
    private String info;

    /**
     * 从属服务项
     */
    @TableField("nc_id")
    private Integer ncId;


}
