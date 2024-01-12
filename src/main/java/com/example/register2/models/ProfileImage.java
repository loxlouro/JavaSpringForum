package com.example.register2.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

@Entity
@Data
@RequiredArgsConstructor
public class ProfileImage implements MultipartFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String originalFileName;
    private Long size;
    private String contentType;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] bytes;
    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private UserAccount userAccount;
//    public ProfileImage(){
//        super();
//    }
    public ProfileImage(String name, String originalFileName, Long size, String contentType, byte[] bytes) {
        super();
        this.name = name;
        this.originalFileName = originalFileName;
        this.size = size;
        this.contentType = contentType;
        this.bytes = bytes;
    }

    public ProfileImage(MultipartFile multipartFile, UserAccount userAccount) throws IOException {
        this.userAccount=userAccount;
        this.size=multipartFile.getSize();
        this.bytes= multipartFile.getBytes();
        this.originalFileName=multipartFile.getOriginalFilename();
        this.name=multipartFile.getName();
        this.contentType=multipartFile.getContentType();
    }
    public ProfileImage(MultipartFile multipartFile) throws IOException {
        this.size=multipartFile.getSize();
        this.bytes= multipartFile.getBytes();
        this.originalFileName=multipartFile.getOriginalFilename();
        this.name=multipartFile.getName();
        this.contentType=multipartFile.getContentType();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getOriginalFilename() {
        return this.originalFileName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getContentType() {
        return contentType;
    }

    @Override
    public boolean isEmpty() {
        return this.getSize()==0;
    }

    @Override
    public long getSize() {
        return this.size;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return null;
    }

    @Override
    public Resource getResource() {
        return MultipartFile.super.getResource();
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {

    }

    @Override
    public void transferTo(Path dest) throws IOException, IllegalStateException {
        MultipartFile.super.transferTo(dest);
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
}
