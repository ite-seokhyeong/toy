package com.sh.simpleproj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SimpleController {

    @RequestMapping("/home")
    public String home() {
        //기본페이지
        return "index.html";
    }

}
