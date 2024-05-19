package com.jpozarycki.springairagintro.services;

import com.jpozarycki.springairagintro.model.Answer;
import com.jpozarycki.springairagintro.model.Question;

public interface QuestionService {
    Answer getAnswer(Question question);
}
