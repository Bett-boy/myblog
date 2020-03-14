package com.ycy.myblog.bean;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class CategoryInfo  implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer categoryId;


    private String categoryName;


    private String categoryAlias;


    private String categoryDesc;


    private Integer number = 0;


    public Integer getNumber() { return number;}


    public void setNumber(Integer number) { this.number = number ;}


    public Integer getCategoryId() {
        return categoryId;
    }


    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }


    public String getCategoryName() {
        return categoryName;
    }


    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    public String getCategoryAlias() {
        return categoryAlias;
    }


    public void setCategoryAlias(String categoryAlias) {
        this.categoryAlias = categoryAlias;
    }


    public String getCategoryDesc() {
        return categoryDesc;
    }


    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }
}