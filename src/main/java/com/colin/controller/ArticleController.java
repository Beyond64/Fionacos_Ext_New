package com.colin.controller;

import com.colin.entity.ArticleVo;
import com.colin.entity.User;
import com.colin.service.ArticleService;
import com.colin.service.UserService;
import com.colin.tool.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 *
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;

    @RequestMapping("/articleList")
    @ResponseBody
    public Map<String, Object> findArticleList(Integer page, Integer limit){
        Map<String, Object> map = articleService.findArticleList(page, limit);
        return map;
    }

    @RequestMapping("/FindArticleById")
    @ResponseBody
    public ArticleVo FindArticleById(Integer ArticleId){
        ArticleVo articleVo =  articleService.FindArticleById(ArticleId);
        return articleVo;
    }

    @RequestMapping("/FindArticleNameList")
    @ResponseBody
    public List<ArticleVo> FindArticleNameList(){
        List<ArticleVo> list =  articleService.FindArticleNameList();
        return list;
    }

    @RequestMapping("/addArticle")
    @ResponseBody
    public String addArticle(ArticleVo articleVo) {
        Integer objectId = articleService.selectObjectId();
        articleVo.setArticleId(objectId);
        articleService.addArticle(articleVo);
        if(articleVo.isEmail == 1){
            articleService.sendArticleEmail(objectId);
        }
        return "true";
    }

    @RequestMapping("/updateArticle")
    @ResponseBody
    public String updateArticle(ArticleVo articleVo) {
        articleService.updateArticle(articleVo);
        if(null != articleVo.isEmail && articleVo.isEmail == 1){
            articleService.sendArticleEmail(articleVo.getArticleId());
        }
        return "true";
    }

    @RequestMapping("/deleteArticle")
    @ResponseBody
    public String deleteArticle(@RequestBody List<Integer> articleIds) {
        articleService.deleteArticle(articleIds);
        return "true";
    }

    @RequestMapping("/sendArticleEmail")
    public void sendArticleEmail(Integer articleIds) {
        articleService.sendArticleEmail(articleIds);
    }


    /**主动发送邮件,不考虑文章状态
     * @param articleIds
     */
    @RequestMapping("/activeSendArticleEmail")
    @ResponseBody
    public String activeSendArticleEmail(Integer articleIds) {
        articleService.activeSendArticleEmail(articleIds);
        return "true";
    }


}
