package com.app.controller;


import com.app.common.Result;
import com.app.entity.Question;
import com.app.entity.Questionnaire;
import com.app.entity.Users;
import com.app.entity.request.QuestionAnswersDto;
import com.app.entity.response.QuestionVo;
import com.app.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.app.common.BaseController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Byterain
 * @since 2024-09-27
 */
@RestController
@RequestMapping("/question")
@Slf4j
public class QuestionController extends BaseController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/getAll")
    public Result<List<Questionnaire>> getAll() {
        return questionService.getAll();
    }

    @GetMapping("/getQuestion")
    public Result<QuestionVo> getQuestion(@RequestParam("Id") Integer questionId) {
        return questionService.getQuestion(questionId);
    }


    @PostMapping("/answers")
    public Result<String> answers(@RequestBody QuestionAnswersDto questionAnswersDto){
        log.info("answers: {}", questionAnswersDto);
        return questionService.answers(questionAnswersDto);
    }
}
