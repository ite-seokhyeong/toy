package com.sh.simpleproj.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/shopping")
public class ShoppingController {

    static final Logger logger = LoggerFactory.getLogger(ShoppingController.class);

    @RequestMapping("")
    public String goShoppingHome() {
        logger.info("goShoppingHome()!");
        return "shopping.html";
    }

    @ResponseBody
    @RequestMapping("/name")
    public String getShopName() {
        logger.info("getShopName()!");
        return "NHN Game Shop";
    }

}
