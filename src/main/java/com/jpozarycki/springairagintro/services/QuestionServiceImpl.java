package com.jpozarycki.springairagintro.services;

import com.jpozarycki.springairagintro.model.Answer;
import com.jpozarycki.springairagintro.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final ChatClient chatClient;
    @Override
    public Answer getAnswer(Question question) {
        PromptTemplate promptTemplate = new PromptTemplate(question.question());
        ChatResponse chatResponse = chatClient.call(promptTemplate.create());

        return new Answer(chatResponse.getResult().getOutput().getContent());
    }
}
