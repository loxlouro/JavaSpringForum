package com.example.register2.services;

import com.example.register2.exceptions.TagAlreadyExistsException;
import com.example.register2.exceptions.TagNotFoundException;
import com.example.register2.models.Question;
import com.example.register2.models.Tag;
import com.example.register2.repo.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    @Autowired
    TagRepository tagRepository;

    public Collection<Tag> getAllTags(){
        try{
            Collection<Tag> allTags = tagRepository.findAll();
            return allTags;
        } catch (Exception e){
            return null;
        }
    }
    public Tag findById(Long id){
        try{
            Optional<Tag> tag = tagRepository.findById(id);
            if (tag.isPresent())return tag.get();
            throw new TagNotFoundException("No tag with id: "+id);
        } catch (TagNotFoundException e){
            return null;
        }
    }
    public boolean saveTag(Tag tag){
        try{
            Tag tagFromDB = tagRepository.findByName(tag.getName());
            System.out.println("saving tag: "+tag.getName());
            if (tagFromDB == null) throw new TagAlreadyExistsException("Tag already exists");
            tagRepository.save(tag);
            return true;
        } catch (TagAlreadyExistsException e){
            return false;
        }
    }
    public boolean updateTag(Tag tag){
        try{
            Tag tagFromDB = tagRepository.findByName(tag.getName());
            if (tagFromDB == null) throw new TagNotFoundException("Tag: "+tag.getName()+" not found");
            tagFromDB.setQuestions(tag.getQuestions());
            return true;
        } catch (TagNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Tag> updateQuestionList(Question question){
        try {
            List<Tag> tagsFromDB = new ArrayList<>();
            for (Tag i: question.getTags()
                 ) {
                try {
                    Tag tagFromDB = tagRepository.findByName(i.getName());
                    if (tagFromDB == null) throw new TagNotFoundException("Tag not found");
                    tagFromDB.getQuestions().add(question);
                    tagsFromDB.add(tagRepository.save(tagFromDB));
                } catch (TagNotFoundException e) {
                    tagsFromDB.add(tagRepository.save(i));
                }
            }
            return tagsFromDB;
        }catch (Exception exception){
            return null;
        }
    }
//    public Tag updateQuestionList(Tag tag, Question question){
//        try{
//            Tag j = tagRepository.findByName(tag.getName());
//            if (j==null)throw new TagNotFoundException("Tag not found");
//            j.getQuestions().add(question);
//            tagRepository.save(j);
//            return true;
//        }catch (TagNotFoundException e){
//            return false;
//        }
//    }
    public List<Tag> readTagsFromString(String string){
        try {
            String temp = string.toLowerCase().replace(" ","");
            String[] tagStrings = temp.split(",");
            List<Tag> tags = new ArrayList<>();
            for (String i : tagStrings
            ) {
                Tag tagFromDB = tagRepository.findByName(i);
                if(tagFromDB!=null)tags.add(tagFromDB);
                else {
                    Tag tag = new Tag();
                    tag.setName(i);
                    tags.add(tag);
                }
            }
            return tags;
        } catch (Exception e){
            return null;
        }
    }

}
