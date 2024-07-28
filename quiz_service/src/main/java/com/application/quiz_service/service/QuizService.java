package com.application.quiz_service.service;

import com.application.quiz_service.model.QuestionModel;
import com.application.quiz_service.model.UserResponse;

import java.util.List;

public interface QuizService {

    void createQuiz(String category, int numQstn, String title);

    List<QuestionModel> getQuizQuestions(int id);

    int calculateScore(Integer id,List<UserResponse> userResponse);
}
