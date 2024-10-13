package com.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("question_answers")
public class QuestionAnswers implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 问卷id
     */
    @TableField("qn_id")
    private Integer qnId;

    /**
     * 问题id
     */
    @TableField("q_id")
    private Integer qId;

    /**
     * 选项id
     */
    @TableField("id")
    private Integer id;

    /**
     * 分值
     */
    @TableField("Score")
    private Integer score;


}
