package com.example.register2.controllers;

import com.example.register2.models.Question;
import com.example.register2.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/all")
    public List<Question> getAllQuestions(Model model){
        return new ArrayList<>(questionService.getAllQuestions());
    }
}
