package com.example.askme.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {
    @RequestMapping("/") // 왜 GetMapping이 아니라 RequestMapping인가?
    public String home() {
        log.info("home controller");
        return "home";
    }
}
