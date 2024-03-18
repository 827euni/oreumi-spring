package com.example.blog.dto;

import com.example.blog.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {
    private String title;
    private String content;

    public Article toEntity() { // 빌더 패턴을 사용해서 DTO를 엔티티로 만들어줌.
        return Article.builder() // 추후에 블로그 글을 추가할 때 저장할 엔티티로 변환하는 용도로 사용.
                .title(title)
                .content(content)
                .build();
    }
}
