package com.example.blog.service;

import com.example.blog.domain.Article;
import com.example.blog.dto.AddArticleRequest;
import com.example.blog.dto.UpdateArticleRequest;
import com.example.blog.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void delete(Long id) {
        blogRepository.deleteById(id); // JPA 제공 기능
    }

    @Transactional // 하나의 트랜젝션으로 묶는역할, 중간에 에러가 발생하더라도 제대로 된 값 수정을 보장함.
    public Article update(Long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found " + id));

        article.update(request.getTitle(), request.getContent());
        return article;
    }
}
