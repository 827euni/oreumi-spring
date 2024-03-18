package com.example.blog.domain;

import com.example.blog.dto.AddArticleRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Getter
@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public ArticleResponse toResponse() {
        return ArticleResponse.builder()
                .title(title)
                .content(content)
                .build();
    }
}
