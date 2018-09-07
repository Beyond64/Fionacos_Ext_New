package com.colin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ArticleVo {
    public Integer articleId;
    public String articleName;
    public String articleContent;
    public String articleAuthor;
    public Integer isEmail;
    public Integer emailStatus;
    public Integer IsHide;
    public Integer releaseStatus;
    public String department;
    public Date articleTime;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(Integer emailStatus) {
        this.emailStatus = emailStatus;
    }

    public Integer getReleaseStatus() {
        return releaseStatus;
    }

    public void setReleaseStatus(Integer releaseStatus) {
        this.releaseStatus = releaseStatus;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor;
    }

    public Integer getIsEmail() {
        return isEmail;
    }

    public void setIsEmail(Integer isEmail) {
        this.isEmail = isEmail;
    }

    public Integer getIsHide() {
        return IsHide;
    }

    public void setIsHide(Integer isHide) {
        IsHide = isHide;
    }

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public Date getArticleTime() {
        return articleTime;
    }

    public void setArticleTime(Date articleTime) {
        this.articleTime = articleTime;
    }
}
