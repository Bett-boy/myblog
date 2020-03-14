package com.ycy.myblog.service.impl;

import com.ycy.myblog.bean.CategoryInfo;
import com.ycy.myblog.dao.ArticleInfoMapper;
import com.ycy.myblog.dao.CategoryInfoMapper;
import com.ycy.myblog.service.CategoryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CategoryInfoServiceImpl implements CategoryInfoService {

    @Autowired
    private CategoryInfoMapper categoryInfoMapper;

    @Autowired
    private ArticleInfoMapper articleInfoMapper;

    @Override
    //查询；#p0：第一个参数;,@Cacheable：缓存数据
    @Cacheable(cacheNames="ycy",key ="#p0")
    public List<CategoryInfo> getCateList(CategoryInfo record) {
        return categoryInfoMapper.getCateList();
    }

    @Override
    //添加栏目信息;
    @CacheEvict(cacheNames="ycy",allEntries=true)
    public boolean add(CategoryInfo categoryInfo) {
        try {
            int i = categoryInfoMapper.insertSelective(categoryInfo);
            if (i > 0 ) {
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    //根据id查询栏目信息;
    @CacheEvict(cacheNames="ycy",allEntries=true)
    public CategoryInfo getCategoryInfo(Integer categoryId) {
        try {
            return categoryInfoMapper.selectByPrimaryKey(categoryId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @CacheEvict(cacheNames="ycy",allEntries=true)
    public boolean update(CategoryInfo categoryInfo) {
        try {
            int i = categoryInfoMapper.updateByPrimaryKeySelective(categoryInfo);
            if (i > 0 ) {
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    //删除栏目信息(级联删除);
    @Transactional
    @CacheEvict(cacheNames="ycy",allEntries=true)
    public void delete(Integer categoryId) {
        //先删除文章信息；
        articleInfoMapper.deleteByCategoryId(categoryId);
        //后删除栏目信息；
        categoryInfoMapper.deleteByPrimaryKey(categoryId);
    }
}
