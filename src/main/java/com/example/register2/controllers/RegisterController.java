package com.example.register2.controllers;


import com.example.register2.models.UserAccount;
import com.example.register2.services.UserAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {
    @Autowired
    private UserAccountService userAccountService;
    @GetMapping("/registration")
    private String showRegistration(Model model){
        model.addAttribute("userForm",new UserAccount());
        return "register";
    }
    @PostMapping("/registration")
    private String registration(Model model,
                                @ModelAttribute("userForm") @Valid UserAccount userForm){
        userAccountService.saveUserAccount(userForm);
        return "redirect:/";
    }
//    @GetMapping("/")
//    public String showMain(Model model){
//
//        return "main";
//    }


//    @PostMapping("/add")
//    public String registerUser(
//            @ModelAttribute("userForm") @Valid User userForm
//            , @RequestParam("firstname") String firstname
//            , @RequestParam("lastname") String lastname
//            , BindingResult bindingResult, Model model) {
//        try {
//            if (bindingResult.hasErrors()) {
//                throw new RuntimeException("Error");
//            }
//            if (!userForm.getPassword().equals(userForm.getConfirmPassword())){
//                model.addAttribute("passwordError", "Пароли не совпадают");
//                return "register";
//            }
////            if (firstname==null || lastname==null){
////                model.addAttribute("passwordError", "Пароли не совпадают");
////                return "register";
////            }
//            userForm.setFirstName(firstname);
//            userForm.setLastName(lastname);
//
//            if (!userService.saveUser(userForm)){
//                model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
//                return "register";
//            }
//
//        } catch (Exception e) {
//            return "register";
//        }
//        return "redirect:/main";
//    }
}

