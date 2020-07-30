package com.example.Elasticsearch.repository;

import com.example.Elasticsearch.model.BlogVO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @auther ChunKit.Ou
 * @data 7/27/2020 11:20 AM
 **/
@Repository
public interface ElasticSearchRepository extends ElasticsearchRepository<BlogVO, Long> {
}
