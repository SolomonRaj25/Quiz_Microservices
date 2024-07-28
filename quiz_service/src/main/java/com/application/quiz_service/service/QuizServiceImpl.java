package com.application.quiz_service.service;

import com.application.quiz_service.entity.Quiz;
import com.application.quiz_service.feign.ConnectingInterface;
import com.application.quiz_service.model.QuestionModel;
import com.application.quiz_service.model.UserResponse;
import com.application.quiz_service.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService{

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private ConnectingInterface connectingInterface;

    @Override
    public void createQuiz(String category, int numQstn, String title) {
        List<Integer> questions = connectingInterface.generateQuiz(category,numQstn).getBody();
        Quiz quiz = Quiz.builder().title(title).questionIds(questions).build();
        quizRepository.save(quiz);
    }

    @Override
    public List<QuestionModel> getQuizQuestions(int id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Integer> questionIds = quiz.get().getQuestionIds();
        ResponseEntity<List<QuestionModel>> questions = connectingInterface.getQuestionsFromIds(questionIds);
        return questions.getBody();
    }

    @Override
    public int calculateScore(Integer id,List<UserResponse> userResponse) {
        ResponseEntity<Integer> getScore = connectingInterface.getScore(userResponse);
        return getScore.getBody();
    }

}
