package com.app.service;

import com.app.common.Result;
import com.app.entity.Question;
import com.app.entity.Questionnaire;
import com.app.entity.request.QuestionAnswersDto;
import com.app.entity.response.QuestionVo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Byterain
 * @since 2024-09-27
 */
public interface QuestionService extends IService<Question> {

    //获取所有问卷
    public Result<List<Questionnaire>> getAll();

    //获取单个问卷题目
    public Result<QuestionVo> getQuestion(int questionnaireId);

    //提交问卷
    public Result<String> answers(@RequestBody QuestionAnswersDto questionAnswersDto);

}
