package com.example.register2.controllers;

import com.example.register2.models.Comment;
import com.example.register2.models.Question;
import com.example.register2.models.Tag;
import com.example.register2.models.UserAccount;
import com.example.register2.models.enums.Difficulty;
import com.example.register2.services.DateTimeFormatService;
import com.example.register2.services.QuestionService;
import com.example.register2.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainPagesController {
    @Autowired
    QuestionService questionService;
    @Autowired
    UserAccountService userAccountService;
    @GetMapping("/")
    public String showMainPage(Model model){
        model.addAttribute("newQuestions"
                , questionService.getNewQuestionsForMainPage());
        model.addAttribute("popularQuestions"
                , questionService.getPopularQuestionsForMainPage());
        return "main";
    }
    @GetMapping("/ask-question")
    public String showAskQuestion(Model model){
        model.addAttribute("questionForm", new Question());
        model.addAttribute("difficulties", Difficulty.values());
        return "newQ";
    }
    @PostMapping("/ask-question")
    public String askQuestion(Model model
                              , @AuthenticationPrincipal UserAccount userAccount
                              , @RequestParam("tagString") String tagString
    , @ModelAttribute("questionForm") Question questionForm){
//        System.out.println(questionForm.getDifficulty().name());
//        System.out.println(questionForm.getQuestionHead());
//        System.out.println(questionForm.getQuestionBody());
//        System.out.println(userAccount.getEmail());
//        System.out.println(tagString);
        questionForm.setTags(new ArrayList<>());
        UserAccount userAccountFromDB = userAccountService.findByEmail(userAccount.getEmail());
        questionForm.setAuthor(userAccountFromDB);
        for (Tag i:questionService.getTagService().readTagsFromString(tagString)
             ) {
            questionForm.getTags().add(i);
        }
        questionService.saveQuestion(questionForm);
        return "redirect:/";
    }
    @GetMapping("/questions/{id}")
    public String showQuestion(Model model
    , @PathVariable("id")Long id
    , @AuthenticationPrincipal UserAccount userAccount){
        Question questionFromDB =questionService.getQuestionById(id);
        List<Comment> commentList = questionService.getCommentService().getCommentsForQuestion(questionFromDB);
        model.addAttribute("questionSeen", questionFromDB);
        model.addAttribute("comments", commentList);
        model.addAttribute("commentForm", new Comment());
        return "question";
    }
    @GetMapping("/search")
    public String searchByParam(Model model, @RequestParam("searchForm") String searchForm){
//        System.out.println(searchForm);
        List<Question> questions = questionService.searchSubstring(searchForm);
        model.addAttribute("questions", questions);
        model.addAttribute("searchParam", searchForm);
        return "search-results";
    }
}
