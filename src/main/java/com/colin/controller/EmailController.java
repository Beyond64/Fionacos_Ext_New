package com.colin.controller;

import com.colin.entity.EmailVo;
import com.colin.entity.User;
import com.colin.service.EmailService;
import com.colin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String,Object> findEmailList(@RequestParam(name = "date",required = false) String date){
        return emailService.findEmailList(date);
    }

}
