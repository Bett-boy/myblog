package com.ycy.myblog.bean;

import com.ycy.myblog.utils.BaseBean;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@ToString
public class ArticleInfo extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer articleId;


    private Integer userId;


    private Integer categoryId;


    private String articleTitle;


    private String articleContent;


    private String articleImg;


    private String articleRecom;


    private Date articleTime;


    private String articleMark;


    private String categoryName;


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getArticleId() {
        return articleId;
    }


    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }


    public Integer getUserId() {
        return userId;
    }


    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public Integer getCategoryId() {
        return categoryId;
    }


    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }


    public String getArticleTitle() {
        return articleTitle;
    }


    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }


    public String getArticleContent() {
        return articleContent;
    }


    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }


    public String getArticleImg() {
        return articleImg;
    }


    public void setArticleImg(String articleImg) {
        this.articleImg = articleImg;
    }


    public String getArticleRecom() {
        return articleRecom;
    }


    public void setArticleRecom(String articleRecom) {
        this.articleRecom = articleRecom;
    }


    public Date getArticleTime() {
        return articleTime;
    }


    public void setArticleTime(Date articleTime) {
        this.articleTime = articleTime;
    }


    public String getArticleMark() {
        return articleMark;
    }


    public void setArticleMark(String articleMark) {
        this.articleMark = articleMark;
    }
}