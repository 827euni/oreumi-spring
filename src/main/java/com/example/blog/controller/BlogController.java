package com.example.blog.controller;
import com.example.blog.service.BlogService;
import com.example.blog.domain.Article;
import com.example.blog.domain.ArticleResponse;
import com.example.blog.domain.ModifyArticleRequest;
import com.example.blog.dto.AddArticleRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController        // 객체 데이터를 JSON 형식으로 변환함.
public class BlogController {
    private BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    // HTTP요청이 'POST /api/articles' 경로일 때 해당 메소드로 매핑
    @PostMapping("/api/articles")   // json  { "title": "제목", "content": "내용"}
    public ResponseEntity<ArticleResponse> addArticle(@RequestBody AddArticleRequest request) {
        Article article = blogService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(article.toResponse());    // json { "title": "제목", "content": "내용"}
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> list = blogService.findAll()
                .stream().map(ArticleResponse::new)
                .toList();
        return ResponseEntity.status(HttpStatus.OK)
                .body(list);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findOne(@PathVariable Long id) {
        Article article = blogService.findById(id);
        if (article == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(article.toResponse());
    }

}