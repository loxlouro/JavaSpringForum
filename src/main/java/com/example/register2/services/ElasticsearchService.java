package com.example.register2.services;

//import com.example.register2.models.Question;
//import org.springframework.data.elasticsearch.client.elc.QueryBuilders;
//import org.springframework.data.elasticsearch.client.erhlc.NativeSearchQueryBuilder;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//import org.springframework.data.elasticsearch.core.SearchHits;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.stream.Collectors;

import static org.apache.commons.collections4.CollectionUtils.collect;

//@Component
//public class ElasticsearchService {
//    private final ElasticsearchOperations elasticsearchOperations;
//
//    public ElasticsearchService(ElasticsearchOperations elasticsearchOperations) {
//        this.elasticsearchOperations = elasticsearchOperations;
//    }
//
//    public List<Question> searchQuestions(String query) {
//        SearchHits<Question> searchHits = elasticsearchOperations.search(
//                new NativeSearchQueryBuilder()
//                        .withQuery(QueryBuilders.matchQuery("questionHead", query))
//                        .build(),
//                Question.class
//        );
//        return searchHits.stream().map(searchHits::get).collect(Collectors.toList()).reversed();
//    }
//}
//@Component
//public class ElasticsearchService {
//    private final ElasticsearchOperations elasticsearchOperations;
//
//    public ElasticsearchService(ElasticsearchOperations elasticsearchOperations) {
//        this.elasticsearchOperations = elasticsearchOperations;
//    }
//
//    public List<Question> searchQuestions(String query) {
//        SearchHits<Question> searchHits = elasticsearchOperations.search(
//                new NativeSearchQueryBuilder()
//                        .withQuery(QueryBuilders.matchQuery("questionHead", query))
//                        .build(),
//                Question.class
//        );
//        return searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
//    }
//}


//@Component
//public class ElasticsearchService {
//    private final ElasticsearchOperations elasticsearchOperations;
//
//    public ElasticsearchService(ElasticsearchOperations elasticsearchOperations) {
//        this.elasticsearchOperations = elasticsearchOperations;
//    }
//    QueryBuilder matchQuery = QueryBuilders.matchQuery("fieldName", "query");
//    public List<Question> searchQuestions(String query) {
//        Query searchQuery = new NativeSearchQueryBuilder()
//                .withQuery(QueryBuilders.queryStringQuery(query))
//                .build();
//        return elasticsearchOperations.queryForList(searchQuery, Question.class);
//    }
//}

