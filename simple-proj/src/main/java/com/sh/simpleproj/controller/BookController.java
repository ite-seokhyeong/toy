package com.sh.simpleproj.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/book")
public class BookController {

    static final Logger logger = LoggerFactory.getLogger(SimpleController.class);

    @RequestMapping("")
    public String goBookHome() {
        logger.info("goBookHome()!");
        return "book.html";
    }

    @ResponseBody
    @RequestMapping("/name")
    public String getBookName() {
        logger.info("getBookName()!");
        return "SH's JAVA Programming";
    }

}
