package com.example.register2.services.opt;

import com.example.register2.models.ProfileImage;
import com.example.register2.models.UserAccount;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Converter {
//    public static MultipartFile ProfileImageToMultipartFile(ProfileImage profileImage){
//        MultipartFile multipartFile= new MultipartFile() {
//            @Override
//            public String getName() {
//                return profileImage.getName();
//            }
//
//            @Override
//            public String getOriginalFilename() {
//                return profileImage.getOriginalFileName();
//            }
//
//            @Override
//            public String getContentType() {
//                return profileImage.getContentType();
//            }
//
//            @Override
//            public boolean isEmpty() {
//                return profileImage.getSize()==0;
//            }
//
//            @Override
//            public long getSize() {
//                return profileImage.getSize();
//            }
//
//            @Override
//            public byte[] getBytes() throws IOException {
//                return profileImage.getBytes();
//            }
//
//            @Override
//            public InputStream getInputStream() throws IOException {
//                return null;
//            }
//
//            @Override
//            public void transferTo(File dest) throws IOException, IllegalStateException {
//
//            }
//        };
//        return multipartFile;
//    }
//    public static ProfileImage MultipartFileToProfileImage(MultipartFile multipartFile, UserAccount userAccount) throws IOException {
//        ProfileImage profileImage = new ProfileImage();
//        profileImage.setOriginalFileName(multipartFile.getOriginalFilename());
//        profileImage.setName(multipartFile.getName());
//        profileImage.setSize(multipartFile.getSize());
//        profileImage.setContentType(multipartFile.getContentType());
//        profileImage.setBytes(multipartFile.getBytes());
//        profileImage.setUserAccount(userAccount);
//        return profileImage;
//    }
}
