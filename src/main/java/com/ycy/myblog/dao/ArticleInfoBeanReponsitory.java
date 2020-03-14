package com.ycy.myblog.dao;

import com.ycy.myblog.bean.ArticleInfoBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ArticleInfoBeanReponsitory extends ElasticsearchRepository<ArticleInfoBean,Integer> {

   Page<ArticleInfoBean> findDistinctArticleInfoBeanByArticleTitleContainingOrArticleContent(String articleTitle, String articleContent, Pageable pageable);

}
