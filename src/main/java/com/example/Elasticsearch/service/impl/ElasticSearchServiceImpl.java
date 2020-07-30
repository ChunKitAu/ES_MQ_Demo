package com.example.Elasticsearch.service.impl;

import com.example.Elasticsearch.model.BlogVO;
import com.example.Elasticsearch.repository.ElasticSearchRepository;
import com.example.Elasticsearch.service.ElasticSearchService;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

/**
 * @auther ChunKitAu
 * @create 2020-07-29 29
 */
@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {

    public static final String POST_TITLE = "title";
    public static final String POST_SUMMARY = "summary";

    @Autowired
    ElasticSearchRepository postRepository;

    @Override
    public Page<BlogVO> search(Pageable pageable, String keyWord) {
        MultiMatchQueryBuilder builder = QueryBuilders.multiMatchQuery(keyWord,
                POST_SUMMARY,POST_TITLE);
        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(builder)
                .withPageable(pageable)
                .build();
        Page<BlogVO> search = postRepository.search(query);

        return search;
    }

    @Override
    public void saveOrModify(BlogVO blogVO) {
        postRepository.save(blogVO);
    }

    @Override
    public void deleteByBlogId(Long blogId) {
        postRepository.deleteById(blogId);
    }
}
