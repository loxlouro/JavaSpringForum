package com.example.register2.services;

import com.example.register2.exceptions.QuestionNotFoundException;
import com.example.register2.models.Comment;
import com.example.register2.models.Question;
import com.example.register2.models.Tag;
import com.example.register2.models.UserAccount;
import com.example.register2.repo.QuestionRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;
    @Setter
    @Getter
    @Autowired
    CommentService commentService;
    @Setter
    @Getter
    @Autowired
    TagService tagService;
    public Collection<Question> getAllQuestions(){
        try {
            return questionRepository.findAll();
        } catch (Exception e){
            return new ArrayList<>();
        }
    }

    public Question getQuestionById(Long id){
        try {
            Optional<Question> questionFromDB=questionRepository.findById(id);
            if (questionFromDB.isEmpty()) throw new QuestionNotFoundException("Question not found");
            return questionFromDB.get();
        } catch (QuestionNotFoundException e){
            return null;
        }
    }
    public Collection<Question> getQuestionsByTags(Collection<Tag> tags){
        try {
            Collection<Question> questionsFromDB = questionRepository
                    .findAll(Sort.by(Sort.Direction.ASC,"tags"));
            return questionsFromDB;
        } catch (Exception e){
            return new ArrayList<>();
        }
    }
    public Collection<Question> getQuestionsByTag(Tag tag){
        try {
            Collection<Question> questionsFromDB = questionRepository.findByTags(tag);
//                    .findAll(Sort.by(Sort.Direction.ASC,"tags"));
            return questionsFromDB;
        } catch (Exception e){
            return new ArrayList<>();
        }
    }
    public Collection<Question> getNewQuestionsForMainPage(){
        try{
            Collection<Question> questionsFromDB = questionRepository
                    .findAll(Sort.by(Sort.Direction.DESC,"dateOfCreation"));
            return questionsFromDB;
        } catch (Exception e){
            return null;
        }
    }
    public Collection<Question> getPopularQuestionsForMainPage(){
        try{
            Collection<Question> questionsFromDB = questionRepository
                    .findAll(Sort.by(Sort.Direction.ASC,"comments"));
            return questionsFromDB;
        } catch (Exception e){
            return null;
        }
    }
    public boolean saveQuestion(Question question){
        try {
//            Question questionFromDB = questionRepository.getById(question.getId());
//
//            if (questionFromDB!=null)throw new RuntimeException("Question already exists");

            Question temp = new Question();
            temp.setDifficulty(question.getDifficulty());
            temp.setAuthor(question.getAuthor());
            temp.setTags(tagService.updateQuestionList(question));
            temp.setQuestionHead(question.getQuestionHead());
            temp.setQuestionBody(question.getQuestionBody());
//            for (Tag i:question.getTags()
//                 ) {
//                if(!tagService.updateQuestionList(i, question)){
////                    System.out.println(i.getName());
//                    i.setQuestions(new ArrayList<>(List.of(question)));
//                    tagService.saveTag(i);
//                }
//                temp.getTags().add(i);
//                tagService.saveTag(i);
//            }
//
//            System.out.println(question.getQuestionHead());
//            System.out.println(question.getQuestionBody());
//            System.out.println(question.getDifficulty());
//            System.out.println(question.getAuthor().getEmail());
//            for (Tag i:question.getTags()
//                 ) {
//                System.out.println(i.getName());
//            }
            questionRepository.save(temp);

            return true;
        } catch (RuntimeException e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean addNewCommentToQuestion(Question question, Comment comment, UserAccount userAccount){
        try {
            comment.setQuestion(question);
            comment.setAuthor(userAccount);
            Comment commentFromDB=commentService.saveComment(comment);
            if (commentFromDB!=null)return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }
    public List<Comment> getTopCommentsOfUser(UserAccount userAccount){
        try{
            List<Comment> topComments = new ArrayList<>();
            List<Comment> allComments = commentService.getCommentsOfAuthor(userAccount);
            try {
                for (int i = 0; i < 3; i++) {
                    try {
                        if (allComments.get(i) != null) topComments.add(allComments.get(i));
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                        return topComments;
                    }
                }
            }catch (ArrayIndexOutOfBoundsException e){
                return topComments;
            }
            return topComments;
        } catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public List<Question> searchQuestionsByQuestionHead(String questionHead){
        try{
            List<Question> questionsFromDB = List.copyOf(questionRepository.findByQuestionHead(questionHead));
            if(questionsFromDB==null)throw new QuestionNotFoundException("Question with parameters not found");
            return questionsFromDB;
        } catch (QuestionNotFoundException e){
            return new ArrayList<>();
        }
    }
    public List<Question> searchSubstring(String substring) {
        List<Question> result = new ArrayList<>();
        for (Question element : questionRepository.findAll()) {
            if (element.getQuestionHead().toLowerCase().contains(substring.toLowerCase())) {
                result.add(element);
            }
        }
        return result;
    }
//    private boolean stringContainsSubstring(String string, String substring){
//
//    }
}
