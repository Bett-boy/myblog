package com.ycy.myblog.dao;

import com.ycy.myblog.bean.ArticleInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleInfoMapper {

    int deleteByPrimaryKey(Integer articleId);


    int insert(ArticleInfo record);


    int insertSelective(ArticleInfo record);


    ArticleInfo selectByPrimaryKey(Integer articleId);


    int updateByPrimaryKeySelective(ArticleInfo record);


    int updateByPrimaryKey(ArticleInfo record);


    //根据栏目编号删除文章信息;
    int deleteByCategoryId(Integer categoryId);

    //根据文章条件查询文章信息；
    List<ArticleInfo> getArticleList(ArticleInfo articleInfo);

    //查询文章的总数；
    Long getArticleCount(ArticleInfo articleInfo);
}