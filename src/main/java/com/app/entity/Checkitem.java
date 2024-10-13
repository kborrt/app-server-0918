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
@TableName("checkitem")
public class Checkitem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 检查项编号
     */
    @TableId(value = "ci_id", type = IdType.AUTO)
    private Integer ciId;

    /**
     * 检查项名称
     */
    @TableField("ci_name")
    private String ciName;

    /**
     * 检查项内容
     */
    @TableField("ci_content")
    private String ciContent;

    /**
     * 检查项意义
     */
    @TableField("meaning")
    private String meaning;

    /**
     * 备注
     */
    @TableField("remarks")
    private String remarks;


}
