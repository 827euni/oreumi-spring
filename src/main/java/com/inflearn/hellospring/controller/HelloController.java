package com.inflearn.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") // 'hello'라고 들어오면 아래함수를 실행함
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello"; //hello.html로 가서 랜더링 하라는 뜻.
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //프로토콜의 body부분에 내가 직접 아래 부분을 넣어주겠다.
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; //hello spring 오로 그냥 바로 body에 들어감 <- view 필요 없음
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloapi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;

    }

    static class Hello{
        private String name;

        public void setName(String name){
            this.name = name;
        }

        public String getName(){
            return name;
        }
    }
}
