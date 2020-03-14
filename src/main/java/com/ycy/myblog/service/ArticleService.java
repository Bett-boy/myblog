package com.ycy.myblog.service;

import com.ycy.myblog.bean.ArticleInfo;
import com.ycy.myblog.utils.PageBean;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArticleService {
    //添加文章;
    boolean add(ArticleInfo articleInfo);
    //文件上传;
    String uploadPic(MultipartFile file);
    //分页;
    PageBean<ArticleInfo> getArticleList(ArticleInfo articleInfo,Integer page);
    //根据id进行删除;
    boolean delete(Integer articleId);
    //文章信息修改;
    boolean update(ArticleInfo articleId);
    //根据文章id查询信息;
    ArticleInfo getArticleOfId(ArticleInfo articleInfo);
    //查询最新15条信息；
    List<ArticleInfo> getNewArticleList();
    //查询站长推荐文章;
    List<ArticleInfo> getArtDocList();
    //查询文章的总数；
    Long getArticleCount(ArticleInfo articleInfo);
}
