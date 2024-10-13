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
@TableName("setmealdetailed")
public class Setmealdetailed implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 体检套餐项目明细编号（无意义主键）
     */
    @TableId(value = "sd_id", type = IdType.AUTO)
    private Integer sdId;

    /**
     * 体检套餐编号
     */
    @TableField("sm_id")
    private Integer smId;

    /**
     * 体检检查项编号
     */
    @TableField("ci_id")
    private Integer ciId;


}
