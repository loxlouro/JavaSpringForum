package com.example.register2.services;

import com.example.register2.exceptions.SpammingException;
import com.example.register2.models.Comment;
import com.example.register2.models.Question;
import com.example.register2.models.UserAccount;
import com.example.register2.repo.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public List<Comment> getCommentsForQuestion(Question question){
        try{
            Collection<Comment> commentsFromDB= commentRepository.findByQuestion(question);
            if (commentsFromDB!=null)return (List<Comment>) commentsFromDB;
            return new ArrayList<>();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public List<Comment> getCommentsOfAuthor(UserAccount userAccount){
        try{
            Collection<Comment> commentsFromDB= commentRepository
                    .findByAuthor(userAccount);
            commentsFromDB.stream().sorted(sortCommentByLikes);
            if (commentsFromDB!=null)return (List<Comment>) commentsFromDB;
            return new ArrayList<>();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Comment saveComment(Comment comment){
        try{
            if (!isNotSpam(comment.getQuestion(),comment, comment.getAuthor()))
                throw new SpammingException("This comment is spam");

            return commentRepository.save(comment);
        } catch (SpammingException e) {
            return null;
        }
    }
    private static Comparator<Comment> sortCommentByLikes =
            Comparator.comparing(Comment::getLikesCount).reversed();
    private boolean isNotSpam(Question question, Comment comment, UserAccount account){
        return true;
    }
    public Comment getCommentById(Long id){
        try{
            Comment commentFromDB = commentRepository.getById(id);
            if (commentFromDB==null)throw new RuntimeException();
            return commentFromDB;
        } catch (RuntimeException e){
            return null;
        }
    }
}
