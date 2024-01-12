package com.example.register2.repo;

import com.example.register2.models.Comment;
import com.example.register2.models.Question;
import com.example.register2.models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Collection<Comment> findByQuestion(Question question);
    Collection<Comment> findByAuthor(UserAccount author);
}
