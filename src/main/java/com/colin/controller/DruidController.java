package com.colin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/druid")
public class DruidController {
    @RequestMapping("/index")
    public String index(Model model, HttpServletRequest request) {
        return "durid/index";
    }
}
