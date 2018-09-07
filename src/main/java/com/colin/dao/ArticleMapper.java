package com.colin.dao;

import com.colin.entity.ArticleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {

    Integer findArticleCount();

    List<ArticleVo> findArticlePage(@Param("starRows") Integer starRows, @Param("endRows") Integer endRows);

    void addArticle(@Param("articleVo") ArticleVo articleVo);

    void deleteArticle(@Param("articleIds") List<Integer> articleIds);

    ArticleVo FindArticleById(@Param("articleId") Integer articleId);

    List<ArticleVo> FindArticleNameList();

    void updateArticle(@Param("articleVo") ArticleVo articleVo);

    void updateArticleEmailStatus(@Param("articleIds") Integer articleIds, @Param("status")String status);

    Integer selectObjectId();
}
