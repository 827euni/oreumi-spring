package com.example.blog.controller;

import com.example.blog.domain.Article;
import com.example.blog.domain.ModifyArticleRequest;
import com.example.blog.dto.ArticleViewResponse;
import com.example.blog.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model){
        if(id == null){
            model.addAttribute("article", new ArticleViewResponse());
        }
        else{
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }
        return "newArticle";
    }

    /*
    @RequestParam과 @PathVariable의 차이점은,

    @RequestParam: 쿼리 파라미터(query parameter)를 처리할 때 사용.
    즉, URL 뒤에 ?를 사용하여 전달되는 파라미터를 처리
    HTTP 요청의 쿼리 파라미터를 메소드의 파라미터로 매핑.
    ?key1=value1&key2=value2

    @PathVariable: URL 경로 자체에 포함된 변수를 처리할 때 사용.
    URL 경로의 일부로 전달된 값을 메소드의 파라미터로 매핑합니다.
    /resource/{id}

    -> 쿼리 파라미터를 처리해야 할 때는 @RequestParam을 사용하고,
    -> 경로 자체에 변수가 포함된 RESTful 요청을 처리해야 할 때는 @PathVariable을 사용
     */
}
