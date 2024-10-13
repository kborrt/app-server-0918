package com.app.service.impl;

import cn.hutool.core.util.StrUtil;
import com.app.common.Result;
import com.app.entity.NurseOrder;
import com.app.entity.Question;
import com.app.entity.QuestionAnswers;
import com.app.entity.Questionnaire;
import com.app.entity.request.QuestionAnswersDto;
import com.app.entity.response.QuestionVo;
import com.app.mapper.QuestionAnswersMapper;
import com.app.mapper.QuestionMapper;
import com.app.mapper.QuestionnaireMapper;
import com.app.service.QuestionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Byterain
 * @since 2024-09-27
 */
@Service
@Slf4j
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionnaireMapper questionnaireMapper;
    @Autowired
    private QuestionAnswersMapper questionAnswersMapper;

    @Override
    public Result<List<Questionnaire>> getAll() {
        List<Questionnaire> questionnaires = questionnaireMapper.selectList(null);
        return Result.ok(questionnaires);
    }

    @Override
    public Result<QuestionVo> getQuestion(int questionnaireId) {
        QuestionVo questionVo = new QuestionVo();
        List<QuestionVo.item> i = new ArrayList<>();
        questionVo.setName(questionnaireMapper.selectById(questionnaireId).getName());
        QueryWrapper<Question> qw = new QueryWrapper<>();
        qw.eq("qn_id",questionnaireId);
        List<Question> questions = questionMapper.selectList(qw);
        for (Question question : questions) {
            QuestionVo.item item = new QuestionVo.item();
            item.setId(question.getId());
            item.setTitle(question.getTitle());
            item.setType(question.getType());
            List<String> optionsArray = List.of(question.getOptions().split(","));
            item.setOptions(optionsArray);
            i.add(item);
        }
        questionVo.setItems(i);
        return Result.ok(questionVo);
    }

    @Override
    public Result<String> answers(QuestionAnswersDto questionAnswersDto) {
        int score = 0;
        Integer qnId = questionAnswersDto.getId();
        int count = 0;
        for (Object answer : questionAnswersDto.getAnswers()){
            QueryWrapper<Question> qw = new QueryWrapper<>();
            qw.eq("qn_id",qnId)
                .eq("id",count+1);
            List<String> questionOptions = List.of(questionMapper.selectOne(qw).getOptions().split(","));
            int answerIndex=0;
            if (answer instanceof String) {
                for(String option : questionOptions){
                    if(StrUtil.equals(option, (String) answer)){
                        QueryWrapper<QuestionAnswers> qa = new QueryWrapper<>();
                        qa.eq("qn_id",qnId)
                                .eq("q_id",count+1)
                                .eq("id",answerIndex+1);
                        QuestionAnswers questionAnswers = questionAnswersMapper.selectOne(qa);
                        score=score+questionAnswers.getScore();
                        break;
                    }
                    answerIndex++;
                }
            }else{
                ArrayList answerList = (ArrayList) answer;
                int index=answerList.size();
                QueryWrapper<QuestionAnswers> qa = new QueryWrapper<>();
                qa.eq("qn_id",qnId)
                        .eq("q_id",count+1);
                QuestionAnswers questionAnswers = questionAnswersMapper.selectOne(qa);
                score=score+questionAnswers.getScore()*index;


            }


            count++; // 每次循环结束后增加 count
        }
        String result=Integer.toString(score);

        return Result.ok(result);
    }
}
