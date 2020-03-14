package com.ycy.myblog.service;

import com.ycy.myblog.bean.CategoryInfo;

import java.util.List;

/**
 * 栏目管理
 */
public interface CategoryInfoService {
    //查询所有栏目;
    List<CategoryInfo> getCateList(CategoryInfo record);
    //添加栏目信息;
    boolean add(CategoryInfo categoryInfo);
    //根据id查询栏目信息;
    CategoryInfo getCategoryInfo(Integer categoryId);
    //更新栏目信息；
    boolean update(CategoryInfo categoryInfo);
    //删除栏目信息；
    void delete(Integer categoryId) throws Exception;

}
