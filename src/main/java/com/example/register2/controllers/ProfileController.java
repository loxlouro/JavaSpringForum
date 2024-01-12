package com.example.register2.controllers;

import com.example.register2.exceptions.UserNotFoundException;
import com.example.register2.models.Comment;
import com.example.register2.models.ProfileImage;
import com.example.register2.models.UserAccount;
import com.example.register2.services.QuestionService;
import com.example.register2.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user/profile")
public class ProfileController {
    @Autowired
    UserAccountService userAccountService;
    @Autowired
    QuestionService questionService;
    @GetMapping("/{id}")
    public String showProfile(@PathVariable("id") Long id
                              , @AuthenticationPrincipal UserAccount userAccount
            , Model model){

        try {
            UserAccount seenProfile = userAccountService.findById(id);
            List<Comment> topComments = questionService.getTopCommentsOfUser(seenProfile);
            if (seenProfile.equals(userAccountService.findByEmail(userAccount.getEmail()))){
                model.addAttribute("seenProfile", seenProfile);
                model.addAttribute("topComments", topComments);
//                model.addAttribute("status", false);
                return "self-profile";
            }else {
                model.addAttribute("seenProfile", seenProfile);
                model.addAttribute("topComments", topComments);
                return "profile";
            }
        } catch (UserNotFoundException | NullPointerException e) {
            return "404";
        }
    }
    @PostMapping("/{id}")
    public String saveProfile(Model model
                              ,@PathVariable("id") Long id
                            , @ModelAttribute("seenProfile") UserAccount seenProfile
                              , @RequestParam("multipart")MultipartFile profileImage
                              ) throws IOException {
//        System.out.println(seenProfile.getEmail());
//        System.out.println(seenProfile.getProfileStatus());
//        System.out.println(seenProfile.getAboutMe());
//        System.out.println(seenProfile.getId());

        userAccountService.updateUserAccountProfile(seenProfile, profileImage);

        return "redirect:/user/profile/"+id;
    }
}
