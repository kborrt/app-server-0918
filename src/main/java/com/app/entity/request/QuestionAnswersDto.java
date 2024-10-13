package com.app.entity.request;

import lombok.Data;

import java.util.List;
@Data
public class QuestionAnswersDto<T> {
    private Integer id;
    private List<T> answers;
}
