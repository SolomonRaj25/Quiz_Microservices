package com.application.question_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionModel {

    private Integer id;
    private String category;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

}
