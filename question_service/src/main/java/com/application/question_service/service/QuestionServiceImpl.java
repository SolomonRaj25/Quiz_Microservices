package com.application.question_service.service;

import com.application.question_service.entity.Question;
import com.application.question_service.model.QuestionModel;
import com.application.question_service.model.UserResponse;
import com.application.question_service.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public List<Question> fetchQuestionByCategory(String category) {
        return questionRepository.findByCategory(category);
    }

    @Override
    public List<Question> fetchAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public List<Integer> generateQuiz(String category, int numQ) {
        return questionRepository.findRandomQuestionsByCategory(category,numQ);
    }

    @Override
    public List<QuestionModel> getQuestionsFromIds(List<Integer> questionIds) {
        List<Question> questions = new ArrayList<>();
        for (Integer id : questionIds) {
            questions.add(questionRepository.findById(id).get());
        }
        List<QuestionModel> quizQuestions = new ArrayList<>();
        for (Question q : questions) {
            QuestionModel qModel = QuestionModel.builder()
                    .id(q.getId()).category(q.getCategory())
                    .questionTitle(q.getQuestionTitle())
                    .option1(q.getOption1()).option2(q.getOption2())
                    .option3(q.getOption3()).option4(q.getOption4()).build();
            quizQuestions.add(qModel);
        }
        return quizQuestions;
    }

    @Override
    public Integer getScore(List<UserResponse> userResponses) {
        int correct = 0;
        for (UserResponse response : userResponses) {
            Question question = questionRepository.findById(response.getId()).get();
            if (response.getResponse().equalsIgnoreCase(question.getRightAnswer())) {
                correct++;
            }
        }
        return correct;
    }

}
