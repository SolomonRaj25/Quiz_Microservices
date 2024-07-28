package com.application.question_service.controller;

import com.application.question_service.entity.Question;
import com.application.question_service.model.QuestionModel;
import com.application.question_service.model.UserResponse;
import com.application.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/addQuestion")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return new ResponseEntity<>(questionService.addQuestion(question), HttpStatus.CREATED);
    }

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> fetchAllQuestions() {
        List<Question> questions = questionService.fetchAllQuestions();
        if (Optional.ofNullable(questions).isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> fetchQuestionsByCategory(@PathVariable String category) {
        List<Question> questions = questionService.fetchQuestionByCategory(category);
        if (Optional.ofNullable(questions).isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    @GetMapping("/generateQuiz")
    public ResponseEntity<List<Integer>> generateQuiz(@RequestParam String category,@RequestParam int numQ) {
        return new ResponseEntity<>(questionService.generateQuiz(category,numQ), HttpStatus.CREATED);
    }

    @PostMapping("/getQuestionsForQuiz")
    public ResponseEntity<List<QuestionModel>> getQuestionsFromIds(@RequestBody List<Integer> questionIds) {
        return new ResponseEntity<>(questionService.getQuestionsFromIds(questionIds),HttpStatus.OK);
    }

    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<UserResponse> userResponses) {
        return new ResponseEntity<>(questionService.getScore(userResponses),HttpStatus.ACCEPTED);
    }

}
