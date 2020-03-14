package com.ycy.myblog.controller.front;

import com.ycy.myblog.bean.ArticleInfo;
import com.ycy.myblog.bean.ArticleInfoBean;
import com.ycy.myblog.bean.CategoryInfo;
import com.ycy.myblog.bean.MessageInfo;
import com.ycy.myblog.dao.ArticleInfoBeanReponsitory;
import com.ycy.myblog.service.ArticleService;
import com.ycy.myblog.service.CategoryInfoService;
import com.ycy.myblog.service.MessageInfoService;
import com.ycy.myblog.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/blog")
public class FrontIndexController {

    @Autowired
    private CategoryInfoService categoryInfoService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private MessageInfoService messageInfoService;

    @Autowired
    private ArticleInfoBeanReponsitory articleInfoBeanReponsitory;

   //通过es查询;
    @RequestMapping("es")
    public String getList(String name,Model model) {
        Pageable page = PageRequest.of(0,50);
        Page<ArticleInfoBean> pages = articleInfoBeanReponsitory.findDistinctArticleInfoBeanByArticleTitleContainingOrArticleContent(name, name, page);
        List<ArticleInfoBean> content = pages.getContent();
        model.addAttribute("ExList",content);

        CategoryInfo categoryInfo = new CategoryInfo();
        List<CategoryInfo> cateList = categoryInfoService.getCateList(categoryInfo);
        model.addAttribute("cateList",cateList);

        init(model);
        //查询站长推荐文章;
        List<ArticleInfo> artDocList = articleService.getArtDocList();
        model.addAttribute("artDocList",artDocList);
        return "back/EsList";
    }

    public void init(Model model) {
        //查询最新的15条文章信息；
        List<ArticleInfo> newArticleList = articleService.getNewArticleList();
        model.addAttribute("articleInfoList",newArticleList);
        
        ////查询站长推荐文章;
        List<ArticleInfo> artDocList = articleService.getArtDocList();
        model.addAttribute("artDocList",artDocList);
    }

    @RequestMapping(value = "index")
    //博客主页显示栏目信息;
    public String index(Model model,CategoryInfo categoryInfo) {
        List<CategoryInfo> cateList = categoryInfoService.getCateList(categoryInfo);
        model.addAttribute("cateList",cateList);
        init(model);
        return "back/indexBlog";
    }

    //阅读原文;
    @RequestMapping("indexview/{id}")
    public String indexView(@PathVariable("id")Integer id , Model model) {
        init(model);
        CategoryInfo categoryInfo = new CategoryInfo();
        List<CategoryInfo> cateList = categoryInfoService.getCateList(categoryInfo);
        model.addAttribute("cateList",cateList);
        //查询文章内容;
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setArticleId(id);
        ArticleInfo articleOfId = articleService.getArticleOfId(articleInfo);
        model.addAttribute("articleList",articleOfId);
        return "back/indexBlogview";
    }

    //根据栏目的类别查询文章信息;
    @RequestMapping("list")
    public String listIndex(ArticleInfo articleInfo,Model model,Integer page) {
        init(model);

        CategoryInfo categoryInfo = new CategoryInfo();
        List<CategoryInfo> cateList = categoryInfoService.getCateList(categoryInfo);
        model.addAttribute("cateList",cateList);

        PageBean<ArticleInfo> list = articleService.getArticleList(articleInfo,page);
        model.addAttribute("pageBean",list);
        model.addAttribute("articleinfo",articleInfo);
        return "back/listIndex";
    }

    //查询留言信息;
    @RequestMapping("message")
    public String message(Model model) {
        init(model);

        CategoryInfo categoryInfo = new CategoryInfo();
        List<CategoryInfo> cateList = categoryInfoService.getCateList(categoryInfo);
        model.addAttribute("cateList",cateList);

        List<MessageInfo> messages = messageInfoService.getMessages();
        model.addAttribute("message",messages);
        return "back/listMessage";
    }

    //添加留言;
    @RequestMapping("addMessage")
    public String add(MessageInfo messageInfo, Model model, HttpSession session) {
        init(model);

        CategoryInfo categoryInfo = new CategoryInfo();
        List<CategoryInfo> cateList = categoryInfoService.getCateList(categoryInfo);
        model.addAttribute("cateList",cateList);

        boolean add = messageInfoService.add(messageInfo);
        if(add) {
            session.setAttribute("info","留言成功");
        }
        else {
            session.setAttribute("info","留言失败");
        }

        return "redirect:/blog/message";
    }
}
