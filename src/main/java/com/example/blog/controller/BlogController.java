package com.example.blog.controller;
import com.example.blog.dto.UpdateArticleRequest;
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

    // HTTP요청이 'POST/api/articles' 경로일 때 해당 메소드로 매핑
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

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody UpdateArticleRequest request){
    /* @RequestBody는 요청 본문에 있는 데이터를 객체를 매핑하기 위한 DTO 클래스
    요청 본문에 포함되어있는 데이터를 UpdateArticleRequest 객체로 자동으로 변환하여 사용할 수 있음

    -> updateArticle 메서드의 매개변수로 @PathVariable Long id와 @RequestBody UpdateArticleRequest request를 사용하여
    클라이언트로부터 전달되는 경로 변수와 요청 본문을 받아와서 해당 값을 사용하여 블로그 글을 업데이트하고,
    업데이트된 글을 ResponseEntity 객체에 담아서 반환함
    */
        Article updatedArticle = blogService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK) // 성공하면 HTTP 상태 코드를 200으로 설정하여 클라이언트에게 반환
                .body(updatedArticle); // 응답 본문에 updatedArticle 객체를 담아서 반환
    }

}