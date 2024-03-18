package com.example.blog.dto;

import com.example.blog.domain.Article;
import lombok.Getter;

@Getter
public class ArticleViewResponse {
    private Long id;
    private String title;
    private String content;

    public ArticleViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
