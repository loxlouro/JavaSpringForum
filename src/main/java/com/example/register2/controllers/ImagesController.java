package com.example.register2.controllers;

import com.example.register2.exceptions.UserNotFoundException;
import com.example.register2.models.ProfileImage;
import com.example.register2.models.UserAccount;
import com.example.register2.services.UserAccountService;
import com.example.register2.services.opt.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/images")
public class ImagesController {
    @Autowired
    UserAccountService userAccountService;

    @GetMapping("/profile_image/{id}")
    private ResponseEntity<?> getImageById(@PathVariable Long id) {
        try {
            UserAccount userAccountFromDB = userAccountService.findById(id);
            ProfileImage profileImage = userAccountService.findByUserAccount(userAccountFromDB);
            return ResponseEntity.ok()
                    .header("filename", profileImage.getOriginalFileName())
                    .contentType(MediaType.valueOf(profileImage.getContentType()))
                    .contentLength(profileImage.getSize())
                    .body(new InputStreamResource(new ByteArrayInputStream(profileImage.getBytes())));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity
                    .noContent().build();
        }
    }

//    @PostMapping("/profile_image/{id}/temp")
//    public ResponseEntity<?> setTempProfileImage(@PathVariable Long id
//            , @ModelAttribute("seenProfile") UserAccount seenProfile
//            , @RequestParam("profileImage") MultipartFile multipartFile) {
//        try {
////            System.out.println("searching profile" + id);
////            UserAccount userAccountFromDB = userAccountService.findById(id);
//            ProfileImage profileImage = new ProfileImage(multipartFile, seenProfile);
//            System.out.println(profileImage.getOriginalFileName());
//            System.out.println(profileImage.getSize());
//            System.out.println(profileImage.getContentType());
//            System.out.println(profileImage.getBytes());
//            seenProfile.setProfileImage(profileImage);
//
//            return ResponseEntity.ok()
//                    .header("filename", profileImage.getOriginalFileName())
//                    .contentType(MediaType.valueOf(profileImage.getContentType()))
//                    .contentLength(profileImage.getSize())
//                    .body(new InputStreamResource(new ByteArrayInputStream(profileImage.getBytes())));
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }
}
