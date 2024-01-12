package com.example.register2.repo;

import com.example.register2.models.Question;
import com.example.register2.models.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>
//        , ElasticsearchRepository<Question, Long>
{
    Collection<Question> findByTags(Tag tag);

    //    Collection<Question> findByQuestionHead(String questionHead);
//    Page<Question> searchSimilar(Question question, String[] fields, Pageable pageable);
//    @Query("{\"match\": {\"content\": {\"query\": \"?0\"}}}")
//    Collection<Question> findByContent(String content);
    @Query("{\"match\": {\"questionHead\": {\"query\": \"?0\"}}}")
    Collection<Question> findByQuestionHead(String questionHead);
}
