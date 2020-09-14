package com.example.Elasticsearch.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;

/**
 * @auther ChunKit.Ou
 * @data 7/27/2020 11:21 AM
 **/
@Document( indexName = "blog",type ="blogVO" ,shards = 1,replicas = 1)
@Data
public class BlogVO {

    @Id
    private  Long id;
    /**
     * 标题
     */
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String title;

    /**
     * 文章摘要
     */
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String summary;

    /**
     * 用户主键
     */
    private Long userId;

    /**
     * 类型主键
     */
    private Long typeId;

    /**
     * 标签
     */
    private List<Long> tags;

    /**
     * 封面图片
     */
    private String picture;


    /**
     * 版权
     */
    private Boolean copyRight;

    /**
     * 浏览次数
     */
    private Integer views;

    /**
     * 点赞次数
     */
    private Integer likeCount;

    /**
     * 是否推荐
     */
    private Boolean recommend;

    /**
     * 评论开启
     */
    private Boolean commentabled;

    /**
     * 赞赏开启
     */
    private Boolean appreciation;

    /**
     * 评论次数
     */
    private Integer comments;

    /**
     * 发布/保存状态
     */
    private Boolean status;

    /**
     * 创建时间
     */
    @Field(type = FieldType.Date, format = DateFormat.custom,pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd",timezone="GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @Field(type = FieldType.Date, format = DateFormat.custom,pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd",timezone="GMT+8")
    private Date updateTime;

}
