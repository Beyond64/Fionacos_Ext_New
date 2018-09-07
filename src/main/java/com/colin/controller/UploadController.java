package com.colin.controller;

import com.colin.entity.User;
import com.colin.realm.MyRealm;
import com.colin.service.UserService;
import com.colin.tool.UploadUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private UserService userService;

    //上传图片
    @RequestMapping(value = "/image/{type}",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> image(MultipartFile file, HttpServletRequest request, @PathVariable String type, String userName, @RequestParam(value = "userId",required = false)Integer userId){
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            String path = request.getSession().getServletContext().getRealPath("\\upload\\" + type + "\\" + userName + "\\");
            String image = UploadUtils.uploadFile(file,path);
            String relativePath = "/upload/" + type + "/" + userName + "/" + image;
            System.out.println(relativePath);
            userService.saveImagePath(relativePath,type,userName);
            if(userId != null){
                //更新用户session
                Session session = SecurityUtils.getSubject().getSession();
                User user = userService.findByName(userName);
                session.setAttribute("user",user);
            }
            map.put("code",0);
            map.put("image",relativePath);
        } catch (IOException e) {
            map.put("code",1);
            e.printStackTrace();
        }
        return map;

    }
}
