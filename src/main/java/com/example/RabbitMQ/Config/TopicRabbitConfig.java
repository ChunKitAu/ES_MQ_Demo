package com.example.RabbitMQ.Config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther ChunKitAu
 * @create 2020-07-28 28
 */
@Configuration
public class TopicRabbitConfig {

    //绑定键
    public final static String addOrModify = "blog.addOrModify";
    public final static String delete = "blog.delete";

    //队列
    @Bean
    public Queue blogAddOrUpdateQueue(){
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        //   return new Queue("TestDirectQueue",true,true,false);
        return  new Queue(TopicRabbitConfig.addOrModify);
    }

    @Bean
    public Queue blogDeleteQueue(){
        return new Queue(TopicRabbitConfig.delete);
    }

    //主题交换机
    @Bean
    TopicExchange exchange() {
        return new TopicExchange("topicExchange");
    }

    @Bean
    Binding bindingExchangeMessage() {
        return BindingBuilder.bind(blogAddOrUpdateQueue()).to(exchange()).with(addOrModify);
    }

    @Bean
    Binding bindingExchangeMessage2() {
        return BindingBuilder.bind(blogDeleteQueue()).to(exchange()).with(delete);
    }



}
