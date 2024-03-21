package com.example.blog.controller;

import com.example.blog.domain.Comment;
import com.example.blog.dto.CommentRequest;
import com.example.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping("/{article_id}")
    public ResponseEntity<Comment> addComment(@PathVariable Long article_id, @RequestBody CommentRequest commentRequest){
        Comment comment = commentService.addComment(article_id, commentRequest);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @GetMapping("/article/{articleId}")
    public ResponseEntity<List<Comment>> getCommentsByArticleId(@PathVariable Long articleId) {
        List<Comment> comments = commentService.findCommentByArticle(articleId);
        return ResponseEntity.ok(comments);
    }

}
