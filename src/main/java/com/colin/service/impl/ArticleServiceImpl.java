package com.colin.service.impl;

import com.colin.dao.ArticleMapper;
import com.colin.entity.ArticleVo;
import com.colin.entity.PageVo;
import com.colin.entity.User;
import com.colin.service.ArticleService;
import com.colin.service.UserService;
import com.colin.tool.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UserService userService;

    @Override
    public Map<String, Object> findArticleList(Integer page, Integer limit) {
        PageVo pageVo = new PageVo();
        pageVo.setPage(page);
        pageVo.setLimit(limit);
        pageVo.setCount(articleMapper.findArticleCount());
        Integer starRows = pageVo.getStarRows();
        Integer endRows = pageVo.getEndRows();
        List<ArticleVo> list = articleMapper.findArticlePage(starRows,endRows);
        Map<String,Object> map = new HashMap<String,Object>();
        if (list != null && list.size() != 0) {
            map.put("data",list);
            map.put("code", 0);
            map.put("msg","");
            map.put("count",pageVo.getCount());
        }
        return  map;
    }

    @Override
    @Transactional
    public void addArticle(ArticleVo articleVo) {
        articleMapper.addArticle(articleVo);
    }

    @Override
    @Transactional
    public void deleteArticle(List<Integer> articleIds) {
        articleMapper.deleteArticle(articleIds);
    }

    @Override
    public ArticleVo FindArticleById(Integer articleId) {
        return articleMapper.FindArticleById(articleId);
    }

    @Override
    public List<ArticleVo> FindArticleNameList() {
        return articleMapper.FindArticleNameList();
    }

    @Override
    public void updateArticle(ArticleVo articleVo) {
        articleMapper.updateArticle(articleVo);
    }

    @Override
    public void updateArticleEmailStatus(Integer articleIds, String status) {
        articleMapper.updateArticleEmailStatus(articleIds,status);
    }

    @Override
    public Integer selectObjectId() {
        return articleMapper.selectObjectId();
    }

    @Override
    @Async
    public void sendArticleEmail(Integer articleIds) {
        ArticleVo articleVo = this.FindArticleById(articleIds);
        List<User> user = userService.findAllEmail();
        if (articleVo == null || user == null || articleVo.getIsEmail() != 1){
            return;
        }
        String email = "";
        for (int i = 0; i < user.size(); i++) {
            if ( user.get(i).getEmail() != null ){
                email += user.get(i).getEmail() + ",";
            }
            if(user.get(i).getFinanceEmail() != null){
                email += user.get(i).getFinanceEmail() + ",";
            }
        }
        MailUtils.SendHtml(articleVo.getDepartment(),email,articleVo.getArticleName(),articleVo.getArticleContent());
        this.updateArticleEmailStatus(articleIds,"2");
        userService.addEmailHistory("supplier",email,articleVo.getArticleContent());
        return;
    }

    @Override
    public void activeSendArticleEmail(Integer articleIds) {
        ArticleVo articleVo = this.FindArticleById(articleIds);
        List<User> user = userService.findAllEmail();
        if (articleVo == null || user == null){
            return;
        }
        String email = "";
        for (int i = 0; i < user.size(); i++) {
            if ( user.get(i).getEmail() != null ){
                email += user.get(i).getEmail() + ",";
            }
            if(user.get(i).getFinanceEmail() != null){
                email += user.get(i).getFinanceEmail() + ",";
            }
        }
        MailUtils.SendHtml(articleVo.getDepartment(),email,articleVo.getArticleName(),articleVo.getArticleContent());
        this.updateArticleEmailStatus(articleIds,"2");
        userService.addEmailHistory("supplier",email,articleVo.getArticleContent());
        return;
    }
}
