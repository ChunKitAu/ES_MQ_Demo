package com.example.Elasticsearch.Controller;

import com.example.Elasticsearch.model.BlogVO;
import com.example.Elasticsearch.repository.ElasticSearchRepository;
import com.example.Elasticsearch.service.ElasticSearchService;
import com.example.comm.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

/**
 * @auther ChunKit.Ou
 * @data 7/27/2020 11:46 AM
 **/
@RestController
public class ElasticSearchController {

    @Autowired
    ElasticSearchService elasticSearchService;

    @RequestMapping("/search")
    public CommonResult search(@RequestParam(defaultValue = "1")Integer current,
                               @RequestParam(defaultValue = "10")Integer size,
                               @RequestParam(value = "keyWord") String keyWord){
        Pageable pageable = PageRequest.of(current-1,size, Sort.Direction.DESC, "createTime");

        Page<BlogVO> query = elasticSearchService.search(pageable, keyWord);

        return CommonResult.success(query);
    }

}
