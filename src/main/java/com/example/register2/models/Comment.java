package com.example.register2.models;

import com.example.register2.services.DateTimeFormatService;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Collection;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private UserAccount author;
    @ManyToOne
    private Question question;
    @Column(columnDefinition = "varchar(1000)",length = 1000)
    private String commentBody;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "comments_likes"
            , joinColumns = @JoinColumn(name = "comment_id", referencedColumnName = "id")
            , inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Collection<UserAccount> likes;
    private LocalDateTime dateOfCreation;

    @PrePersist
    private void init(){
        dateOfCreation= LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserAccount getAuthor() {
        return author;
    }

    public void setAuthor(UserAccount author) {
        this.author = author;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public Collection<UserAccount> getLikes() {
        return likes;
    }

    public void setLikes(Collection<UserAccount> likes) {
        this.likes = likes;
    }
    public int getLikesCount(){
        return this.likes.size();
    }

    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }
    public String getFormattedDateOfCreation(){
        return DateTimeFormatService.toFormat(dateOfCreation);
    }
    public void setDateOfCreation(LocalDateTime dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}
