package com.application.quiz_service.feign;

import com.application.quiz_service.model.QuestionModel;
import com.application.quiz_service.model.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION")
public interface ConnectingInterface {

    @GetMapping("question/generateQuiz")
    ResponseEntity<List<Integer>> generateQuiz(@RequestParam String category, @RequestParam int numQ);


    @PostMapping("question/getQuestionsForQuiz")
    ResponseEntity<List<QuestionModel>> getQuestionsFromIds(@RequestBody List<Integer> questionIds);

    @PostMapping("question/getScore")
    ResponseEntity<Integer> getScore(@RequestBody List<UserResponse> userResponses);

}
