package com.example.register2.models;

import com.example.register2.models.enums.Difficulty;
import com.example.register2.services.DateTimeFormatService;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private UserAccount author;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "questions_tags"
            , joinColumns = @JoinColumn(name = "question_id", referencedColumnName = "id")
            , inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private Collection<Tag> tags;
    @OneToMany(mappedBy = "question")
    private Collection<Comment> comments;
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;
    @Field(type = FieldType.Text)
    private String questionHead;
    @Column(columnDefinition = "varchar(1000)",length = 1000)
    private String questionBody;
    private LocalDateTime dateOfCreation;
    @Transient
    private int commentsSize;
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

    public Collection<Tag> getTags() {
        return tags;
    }

    public void setTags(Collection<Tag> tags) {
        this.tags = tags;
    }

    public String getQuestionHead() {
        return questionHead;
    }

    public void setQuestionHead(String questionHead) {
        this.questionHead = questionHead;
    }

    public String getQuestionBody() {
        return questionBody;
    }

    public void setQuestionBody(String questionBody) {
        this.questionBody = questionBody;
    }

    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDateTime dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
    public String getFormattedDateOfCreation(){
        return DateTimeFormatService.toFormat(dateOfCreation);
    }

    public int getCommentsSize() {
        return comments.size();
    }

    public void setCommentsSize(int commentsSize) {
        this.commentsSize = commentsSize;
    }
}
