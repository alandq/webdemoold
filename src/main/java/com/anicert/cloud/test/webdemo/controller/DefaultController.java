package com.anicert.cloud.test.webdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @GetMapping("/")
    public String getDefalutPage(){
        return "redirect:/index";
    }
}
