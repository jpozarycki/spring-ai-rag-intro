package com.jpozarycki.springairagintro.controllers;

import com.jpozarycki.springairagintro.model.Answer;
import com.jpozarycki.springairagintro.model.Question;
import com.jpozarycki.springairagintro.services.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping("/ask")
    public Answer askQuestion(@RequestBody Question question) {
        return questionService.getAnswer(question);
    }
}
