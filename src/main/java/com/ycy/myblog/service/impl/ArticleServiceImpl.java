package com.ycy.myblog.service.impl;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.ycy.myblog.bean.ArticleInfo;
import com.ycy.myblog.bean.ArticleInfoBean;
import com.ycy.myblog.dao.ArticleInfoBeanReponsitory;
import com.ycy.myblog.dao.ArticleInfoMapper;
import com.ycy.myblog.service.ArticleService;
import com.ycy.myblog.utils.Const;
import com.ycy.myblog.utils.PageBean;
import com.ycy.myblog.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleInfoMapper articleInfoMapper;

    //引入es
    @Autowired
    private ArticleInfoBeanReponsitory articleInfoBeanReponsitory;

    @Override
    public boolean add(ArticleInfo articleInfo) {
       try{
           int count = articleInfoMapper.insertSelective(articleInfo);
           System.out.println(articleInfo.getArticleId());

           //将数据保存至es中;
           ArticleInfoBean articleInfoBean = new ArticleInfoBean();
           articleInfoBean.setArticleId(articleInfo.getArticleId());
           articleInfoBean.setArticleTitle(articleInfo.getArticleTitle());
           articleInfoBean.setArticleContent(articleInfo.getArticleContent());
           articleInfoBean.setArticleImg(articleInfo.getArticleImg());
           articleInfoBean.setArticleTime(articleInfo.getArticleTime());
           articleInfoBeanReponsitory.save(articleInfoBean);

           if (count  > 0) {
               return true;
           }
       }catch (Exception e) {
           e.printStackTrace();
       }
        return false;
    }

    @Override
    //上传图片;
    public String uploadPic(MultipartFile file) {
        try {
            //获取图片名称;
            String fileName = file.getOriginalFilename();
            //获取上传时间;
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            String uploadTime = format.format(new Date());
            //上传的路径全名称;
            String url = Const.FILE_URL + uploadTime + fileName;
            System.out.println("url------"+url);

            //Jersey客户端;
            Client client = new Client();
            WebResource resource = client.resource(url);

            //将文件转化为字节类型;
            byte[] bytes = file.getBytes();
            resource.put(String.class,bytes);
            return url;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    //分页查询;
    public PageBean<ArticleInfo> getArticleList(ArticleInfo articleInfo, Integer page) {
        //获取总记录数;
        int allRow = articleInfoMapper.getArticleCount(articleInfo).intValue();
        //获取总页数;
        int totalPage = PageUtil.countTotalPage(allRow, Const.PAGE_SIZE);
        //获取当前是第几页；
        int currentPage = PageUtil.countCurrentPage(page);
        //获取起始记录页数；
        int startPage = PageUtil.startPage(Const.PAGE_SIZE, currentPage);
        //对page进行判断;
        if (page == null) {
            articleInfo.setStart(startPage);
            articleInfo.setLength(Const.PAGE_SIZE);
        }
        else {
            articleInfo.setStart(-1);
            articleInfo.setLength(-1);
        }
        List<ArticleInfo> userList = articleInfoMapper.getArticleList(articleInfo);
        PageBean<ArticleInfo> pageBean = new PageBean<>();
        pageBean.setAllRow(allRow);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalPage(totalPage);
        pageBean.setList(userList);
        return pageBean;
    }

    @Override
    //删除;通过id；
    public boolean delete(Integer articleId) {
        try {
            int i = articleInfoMapper.deleteByPrimaryKey(articleId);
            //删除（Es）
            ArticleInfoBean articleInfoBean = new ArticleInfoBean();
            articleInfoBean.setArticleId(articleId);
            articleInfoBeanReponsitory.delete(articleInfoBean);
            if (i > 0) {
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    //文章信息修改;
    public boolean update(ArticleInfo articleInfo) {
        try {
            int i = articleInfoMapper.updateByPrimaryKeySelective(articleInfo);

            //将数据保存至es中;
            ArticleInfoBean articleInfoBean = new ArticleInfoBean();
            articleInfoBean.setArticleId(articleInfo.getArticleId());
            articleInfoBean.setArticleTitle(articleInfo.getArticleTitle());
            articleInfoBean.setArticleContent(articleInfo.getArticleContent());
            articleInfoBean.setArticleImg(articleInfo.getArticleImg());
            articleInfoBean.setArticleTime(articleInfo.getArticleTime());
            articleInfoBeanReponsitory.save(articleInfoBean);

            if (i > 0) {
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    //根据文章id查询信息;
    public ArticleInfo getArticleOfId(ArticleInfo articleInfo) {
        try {
            return  articleInfoMapper.selectByPrimaryKey(articleInfo.getArticleId());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    //查询最新的15条文章信息；
    public List<ArticleInfo> getNewArticleList() {
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setArticleMark(Const.MARK_YEX);
        articleInfo.setStart(0);
        articleInfo.setLength(10);
        List<ArticleInfo> articleList = articleInfoMapper.getArticleList(articleInfo);
        return articleList;
    }

    @Override
    //查询站长推荐文章;
    public List<ArticleInfo> getArtDocList() {
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setArticleRecom(Const.MARK_YEX);
        articleInfo.setArticleMark(Const.MARK_YEX);
        articleInfo.setStart(0);
        articleInfo.setLength(8);
        List<ArticleInfo> articleList = articleInfoMapper.getArticleList(articleInfo);
        return articleList;
    }

    @Override
    public Long getArticleCount(ArticleInfo articleInfo) {
        return articleInfoMapper.getArticleCount(articleInfo).longValue();
    }
}
