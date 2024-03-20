package com.example.blog.dto;

import com.example.blog.domain.Article;
import lombok.Getter;

import java.time.LocalDateTime;

public class ArticleViewResponse {
    //여기서 @Getter를 쓰면 타임리프에서 가져오지 못하는 문제가 있음.
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public ArticleViewResponse() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public ArticleViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
    }
}