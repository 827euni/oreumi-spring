package com.example.blog.service;

import com.example.blog.domain.Article;
import com.example.blog.domain.ArticleResponse;
import com.example.blog.domain.Comment;
import com.example.blog.dto.CommentRequest;
import com.example.blog.repository.BlogRepository;
import com.example.blog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private BlogRepository blogRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, BlogRepository blogRepository){
        this.commentRepository = commentRepository;
        this.blogRepository = blogRepository;
    }

    public Comment addComment(Long id, CommentRequest commentRequest){
        Optional<Article> article = blogRepository.findById(id);
        Comment comment = new Comment();
        comment.setContent(commentRequest.getContent());
        comment.setArticle(article.get());

        return commentRepository.save(comment);
    }

    public List<Comment> findCommentByArticle(Long id){
        return commentRepository.findByArticleId(id);
    }
}
