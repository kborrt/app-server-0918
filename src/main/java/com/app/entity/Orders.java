package com.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
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
 * @since 2024-09-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("orders")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单编号
     */
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    /**
     * 预约日期
     */
    @TableField("order_date")
    private LocalDate orderDate;

    /**
     * 客户编号
     */
    @TableField("user_id")
    private String userId;

    /**
     * 所属医院编号
     */
    @TableField("hp_id")
    private Integer hpId;

    /**
     * 所属套餐编号
     */
    @TableField("sm_id")
    private Integer smId;

    /**
     * 订单状态（1 未归档 2 已归档）
     */
    @TableField("state")
    private Integer state;


}
