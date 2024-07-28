package com.application.question_service.service;

import com.application.question_service.entity.Question;
import com.application.question_service.model.QuestionModel;
import com.application.question_service.model.UserResponse;

import java.util.List;

public interface QuestionService {

    Question addQuestion(Question question);

    List<Question> fetchQuestionByCategory(String category);

    List<Question> fetchAllQuestions();

    List<Integer> generateQuiz(String category, int numQ);

    List<QuestionModel> getQuestionsFromIds(List<Integer> questionIds);

    Integer getScore(List<UserResponse> userResponses);
}
