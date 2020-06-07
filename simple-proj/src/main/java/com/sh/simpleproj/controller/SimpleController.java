package com.sh.simpleproj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@PropertySource("classpath:json-config/config.json")
@Controller
public class SimpleController {

    @Autowired
    private Environment environment;

    @GetMapping("/error")
    public String errorPage() {
        throw new IllegalStateException("error");
    }

    @RequestMapping("/home")
    public String home() {
        System.out.println("home!!");
        System.out.println("port = " + environment.getProperty("server-port"));
        return "index.html";
    }
//
//    @RequestMapping(value = "/a/login")
//    public RedirectView goLoginPageA() {
//        System.out.println("a!!");
//        return new RedirectView("/loginPageA.html");
//    }

}
