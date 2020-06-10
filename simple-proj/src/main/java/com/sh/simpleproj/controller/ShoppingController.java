package com.sh.simpleproj.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shopping")
public class ShoppingController {

    static final Logger logger = LoggerFactory.getLogger(SimpleController.class);

    @RequestMapping("")
    public String goShoppingHome() {
        logger.info("goShoppingHome()!");
        return "shopping.html";
    }

}
