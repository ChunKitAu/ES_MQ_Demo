package com.example.RabbitMQ.Receiver;

import cn.hutool.json.JSONUtil;
import com.example.Elasticsearch.model.BlogVO;
import com.example.Elasticsearch.service.ElasticSearchService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @auther ChunKitAu
 * @create 2020-07-28 28
 */
@Component
@RabbitListener(queues = "blog.addOrModify")
public class TopicAddOrModifyReceiver {


    @Autowired
    ElasticSearchService searchService;

    @RabbitHandler
    public void process(String blogVOJson){
        BlogVO blogVO = JSONUtil.toBean(blogVOJson, BlogVO.class);
        System.out.println(blogVO.toString());
        searchService.saveOrModify(blogVO);
        System.out.println("1");
    }

}
