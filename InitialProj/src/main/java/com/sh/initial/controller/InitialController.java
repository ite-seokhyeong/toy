package com.sh.initial.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitialController {

    @RequestMapping("/initial")
    public String initial() {
        return "initial project ~ :)";
    }

}

