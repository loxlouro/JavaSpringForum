package com.example.register2.services;

import com.example.register2.models.Comment;
import com.example.register2.models.UserAccount;
import com.example.register2.repo.CommentRepository;
import com.example.register2.repo.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LikesService {
    @Autowired
    UserAccountRepository userAccountRepository;
    @Autowired
    CommentRepository commentRepository;
    public boolean doLike(Comment comment, UserAccount userAccount){
        try{
            Comment commentFromDB = commentRepository.getById(comment.getId());
            UserAccount userAccountFromDB = userAccountRepository.findByEmail(userAccount.getEmail());
            if (commentFromDB==null||userAccountFromDB==null)throw new RuntimeException();
            if (commentFromDB.getLikes()==null)commentFromDB.setLikes(new ArrayList<>());
            if (userAccountFromDB.getLiked()==null)userAccountFromDB.setLiked(new ArrayList<>());
            if (commentFromDB.getLikes().contains(userAccountFromDB)
                    ||userAccountFromDB.getLiked().contains(commentFromDB)){
                commentFromDB.getLikes().remove(userAccountFromDB);
                userAccountFromDB.getLiked().remove(commentFromDB);
            }else {
                commentFromDB.getLikes().add(userAccountFromDB);
                userAccountFromDB.getLiked().add(commentFromDB);
            }
            userAccountRepository.save(userAccountFromDB);
            commentRepository.save(commentFromDB);
            return true;
        }catch (RuntimeException e){
            e.printStackTrace();
            return false;
        }
    }
}
