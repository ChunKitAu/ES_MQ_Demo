package com.example.RabbitMQ.Receiver;

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
@RabbitListener(queues = "blog.delete")
public class TopicDeleteReceiver {

    @Autowired
    ElasticSearchService searchService;

    @RabbitHandler
    public void process(Long id){
        System.out.println("getMessage:" + id);
        searchService.deleteByBlogId(id);
        System.out.println("1");

    }
}
