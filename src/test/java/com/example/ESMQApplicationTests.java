package com.example;

import cn.hutool.json.JSONUtil;
import com.example.Elasticsearch.model.BlogVO;
import com.example.Elasticsearch.repository.ElasticSearchRepository;
import com.example.Elasticsearch.service.ElasticSearchService;
import com.example.comm.CommonResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ES_MQ_Application.class)
public class ESMQApplicationTests {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;



    @Autowired
    ElasticSearchRepository repository;
    /**
     * 创建索引和映射
     */
    @Test
    public void createIndexAndMapping() {
        //创建索引，会根据EsGoods类的@Document注解信息来创建
        elasticsearchTemplate.createIndex(BlogVO.class);
        //配置映射，会根据EsGoods类中的id、Field等字段来自动完成映射
        elasticsearchTemplate.putMapping(BlogVO.class);
    }

    //初始化搜索引擎
    @Test
    public void contextLoads() {
        String url ="http://127.0.0.1:8086/blog/article/es/";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);

        for (int i =1 ;i <= 110;i++){
            String s = url + i;
            ResponseEntity<BlogVO> response = restTemplate.exchange(s, HttpMethod.GET, requestEntity, BlogVO.class);
            BlogVO blogVO = response.getBody();
            if(blogVO != null){
                repository.save(blogVO);
            }
        }
    }

    @Autowired
    ElasticSearchService elasticSearchService;

    @Test
    public void testSearch(){
        Pageable pageable = PageRequest.of(1-1,10);
        Page<BlogVO> s = elasticSearchService.search(pageable, "流");

        System.out.println(s.toString());
    }

}
