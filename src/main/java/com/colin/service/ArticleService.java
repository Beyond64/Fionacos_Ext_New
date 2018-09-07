package com.colin.service;

import com.colin.entity.ArticleVo;

import java.util.List;
import java.util.Map;

public interface ArticleService {

    Map<String, Object> findArticleList(Integer page, Integer limit);

    void addArticle(ArticleVo articleVo);

    void deleteArticle(List<Integer> articleIds);

    ArticleVo FindArticleById(Integer articleId);

    List<ArticleVo> FindArticleNameList();

    void updateArticle(ArticleVo articleVo);

    void updateArticleEmailStatus(Integer articleIds, String status);

    Integer selectObjectId();

    void sendArticleEmail(Integer articleIds);

    void activeSendArticleEmail(Integer articleIds);
}
