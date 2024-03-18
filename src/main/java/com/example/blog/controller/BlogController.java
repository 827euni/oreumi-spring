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
    public ResponseEntity<List<ArticleResponse>> findAllArticles() { //GET에 요청에 대한 으답으로 ArticleResponse 객체를 반환하기 위함
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

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) { // DELETE 요청에 대해 별다른 데이터를 반환할 필요가 없으므로 VOID
        blogService.delete(id); //@PathVariable을 선언해야 요청으로 들어온 id 값을 받아올 수 있음.

        return ResponseEntity.ok().build(); // 상태보드 200(OK)를 가지는 ResponseEntity 객체를 만듬
    }

}