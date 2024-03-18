package com.example.blog.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleResponse {
    private Long id;
    private String title;
    private String content;

    public ArticleResponse(Article article) { // article을 articleResponse로 변경하여 보냄.
        id = article.getId();
        title = article.getTitle();
        content = article.getContent();
    }

}