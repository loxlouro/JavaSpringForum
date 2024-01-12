package com.example.register2.controllers;

import com.example.register2.models.Comment;
import com.example.register2.models.Question;
import com.example.register2.models.UserAccount;
import com.example.register2.services.CommentService;
import com.example.register2.services.LikesService;
import com.example.register2.services.QuestionService;
import com.example.register2.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@Controller
@RequestMapping("/question/comments")
public class CommentController {
    @Autowired
    LikesService likesService;
    @Autowired
    QuestionService questionService;
    @Autowired
    UserAccountService userAccountService;
//    @GetMapping("/{id}")
//    public List<Comment> getAllCommentsForQuestion(@PathVariable("id") Long id
//    , Model model){
//
//    }
    @PostMapping("/{id}")
    public String addComment(Model model,
            @PathVariable("id")Long id
            ,@ModelAttribute("commentForm") Comment commentForm
    , @AuthenticationPrincipal UserAccount author){
//        System.out.println(commentForm.getCommentBody()+" "+commentForm.getDateOfCreation());
//        System.out.println(author.getEmail());
        Question questionFromDB = questionService.getQuestionById(id);
        UserAccount userAccountFromDB = userAccountService.findByEmail(author.getEmail());
        questionService.addNewCommentToQuestion(questionFromDB, commentForm, userAccountFromDB);
        return "redirect:/questions/"+id;
    }
    @PostMapping("/{id}/like")
    public String doLike(Model model,
                         @PathVariable("id")Long id
//            ,@ModelAttribute("isLiked")
            , @AuthenticationPrincipal UserAccount author){
        Comment commentFromDB = questionService.getCommentService().getCommentById(id);
        UserAccount userAccountFromDB = userAccountService.findByEmail(author.getEmail());
        likesService.doLike(commentFromDB, userAccountFromDB);
        return "redirect:/questions/"+commentFromDB.getQuestion().getId();
    }
}
