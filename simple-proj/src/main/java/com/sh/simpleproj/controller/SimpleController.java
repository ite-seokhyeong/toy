package com.sh.simpleproj.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SimpleController {

    static final Logger logger = LoggerFactory.getLogger(SimpleController.class);

    @RequestMapping("/home")
    public String home() {
        logger.info("home()!");
        return "index.html";
    }

    /** TODO
     * 로그 내용에 따라 적절한 로그 레벨을 적용합니다.
     * 오류 발생 시, StackTrace 전체를 로그 파일에 남깁니다.
     */

    @GetMapping("/error")
    public void logbackTest() {
        try {
            throw  new Exception("에러발생시킴");
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("message", e);
        }
    }

}
