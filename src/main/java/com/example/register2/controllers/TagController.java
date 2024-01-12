package com.example.register2.controllers;

import com.example.register2.models.Tag;
import com.example.register2.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/tags")
public class TagController {
    @Autowired
    QuestionService questionService;
    @GetMapping("/{id}")
    public String searchByTag(Model model, @PathVariable("id") Long id){
        Tag tag = questionService.getTagService().findById(id);
        model.addAttribute("questions", questionService.getQuestionsByTag(tag));
        model.addAttribute("searchParam",tag.getName());
        return "search-results";
    }
}
