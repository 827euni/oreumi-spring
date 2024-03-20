package com.example.blog.controller;

import com.example.blog.domain.Article;
import com.example.blog.domain.ModifyArticleRequest;
import com.example.blog.dto.ArticleViewResponse;
import com.example.blog.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class BlogPageController {
    private BlogService blogService;

    public BlogPageController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/articles")
    public String getArticles(Model model){ // 모든 블로그 다 가져옴
        List<ArticleViewResponse> articles = blogService.findAll().stream()
                .map(ArticleViewResponse::new) //변환된 객체를 리스트로
                .toList();
        model.addAttribute("articles", articles); //model에 articles인 이름으로 리스트를 추가함

        return "articleList";
    }
    @GetMapping("/articles/{id}")
    public String showArticle(@PathVariable Long id, Model model){
        Article article  = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));
        //Article 객체를 받아서  ArticleViewResponse 객체로 변환하고 이를 article이라는 이름으로 저장함

        return "article";
    }
}
