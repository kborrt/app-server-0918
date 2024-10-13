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
 * @since 2024-09-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("nurse_category")
public class NurseCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 上门护理服务项编号
     */
    @TableId(value = "nc_id", type = IdType.AUTO)
    private Integer ncId;

    /**
     * 上门护理服务项名称
     */
    @TableField("name")
    private String name;


}
