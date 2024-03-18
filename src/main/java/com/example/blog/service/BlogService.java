package com.example.blog.service;

import com.example.blog.domain.Article;
import com.example.blog.dto.AddArticleRequest;
import com.example.blog.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(Long id) {
        Optional<Article> article = blogRepository.findById(id);
        return article.orElse(null);
    }
}