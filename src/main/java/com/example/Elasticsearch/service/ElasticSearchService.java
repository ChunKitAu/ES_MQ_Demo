package com.example.Elasticsearch.service;

import com.example.Elasticsearch.model.BlogVO;
import com.example.Elasticsearch.repository.ElasticSearchRepository;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

/**
 * @auther ChunKit.Ou
 * @data 7/27/2020 2:02 PM
 **/
public interface ElasticSearchService {


    Page<BlogVO> search(Pageable pageable, String keyWord);

    void saveOrModify(BlogVO blogVO);

    void deleteByBlogId(Long blogId);

}
