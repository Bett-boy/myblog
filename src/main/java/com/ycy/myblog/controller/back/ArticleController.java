package com.ycy.myblog.controller.back;

import com.ycy.myblog.bean.ArticleInfo;
import com.ycy.myblog.bean.CategoryInfo;
import com.ycy.myblog.bean.UserInfo;
import com.ycy.myblog.service.ArticleService;
import com.ycy.myblog.service.CategoryInfoService;
import com.ycy.myblog.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/back/article/")
public class ArticleController {

    //栏目管理逻辑;
    @Autowired
    private CategoryInfoService categoryInfoService;

    //文章管理逻辑;
    @Autowired
    private ArticleService articleService;

    //查询;
    @RequestMapping("list")
    public String list(ArticleInfo articleInfo,Model model,Integer page) {
        PageBean<ArticleInfo> list = articleService.getArticleList(articleInfo,page);
        model.addAttribute("pageBean",list);
        model.addAttribute("articleinfo",articleInfo);

        CategoryInfo categoryInfo = new CategoryInfo();
        List<CategoryInfo> catelist = categoryInfoService.getCateList(categoryInfo);
        model.addAttribute("cateList",catelist);
        return "back/article/article_list";
    }

    //添加;
    @RequestMapping("loadadd")
    public String add(Model model,CategoryInfo categoryInfo) {
        List<CategoryInfo> catelist = categoryInfoService.getCateList(categoryInfo);
        model.addAttribute("cateList",catelist);
        return "back/article/article_add";
    }

    //加载修改;
    @RequestMapping("loadupdate")
    public String loadupdate(ArticleInfo articleInfo,Model model) {
        ArticleInfo articleinfo = articleService.getArticleOfId(articleInfo);
        model.addAttribute("articleinfo",articleinfo);

        CategoryInfo categoryInfo = new CategoryInfo();
        List<CategoryInfo> catelist = categoryInfoService.getCateList(categoryInfo);
        model.addAttribute("cateList",catelist);
        return "back/article/article_update";
    }

    @RequestMapping("update")
    public String update(ArticleInfo articleInfo,Model model,HttpSession session) {
        //获取用户id;
        UserInfo userinfo = (UserInfo) session.getAttribute("userinfo");
        if (userinfo != null) {
            articleInfo.setUserId(userinfo.getUserId());
            articleInfo.setArticleTime(new Date());
        }
        boolean add = articleService.update(articleInfo);
        if (add) {
            model.addAttribute("message","修改文章信息成功");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = format.format(new Date());
            session.setAttribute("addTime",time);
        }
        else {
            model.addAttribute("message","修改文章信息失败！");
        }
            CategoryInfo categoryInfo = new CategoryInfo();
            List<CategoryInfo> cateList = categoryInfoService.getCateList(categoryInfo);
            model.addAttribute("cateList",cateList);
            model.addAttribute("articleinfo",articleInfo);
            return "back/article/article_update";
    }

    //添加文章信息;
    @RequestMapping("add")
    public String add(ArticleInfo articleInfo, Model model, HttpSession session){
        //获取用户id;
        UserInfo userinfo = (UserInfo) session.getAttribute("userinfo");
        if (userinfo != null) {
            articleInfo.setUserId(userinfo.getUserId());
            articleInfo.setArticleTime(new Date());
        }
        boolean add = articleService.add(articleInfo);
        if (add) {
            model.addAttribute("message","添加文章信息成功");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = format.format(new Date());
            session.setAttribute("addTime",time);
        }
        else {
            model.addAttribute("message","添加文章信息失败！");
        }
            CategoryInfo categoryInfo = new CategoryInfo();
            List<CategoryInfo> cateList = categoryInfoService.getCateList(categoryInfo);
            model.addAttribute("cateList",cateList);
            return "back/article/article_add";
    }

    //图片上传;
    @RequestMapping("upload")
    @ResponseBody
    public String upload(@RequestParam MultipartFile upload) {
        String url = articleService.uploadPic(upload);
        return url;
    }

    //文本编辑器上传图片;
    @RequestMapping("uploadfile")
    public void uploadFile(@RequestParam MultipartFile upload, HttpServletRequest request, HttpServletResponse response) {
        try {
            String pic = articleService.uploadPic(upload);
            PrintWriter writer = response.getWriter();
            String callBack = request.getParameter("CKEDitorFuncNum");
            writer.println("<script>window.parent.CKEDITOR.tools.callFunction("+callBack+",'"+pic+"')</script>");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除;
    @RequestMapping("delete/{id}")
    public String delete(@PathVariable("id")Integer id, Model model){
        boolean delete = articleService.delete(id);
        if (delete) {
            model.addAttribute("message","删除成功");
        }
        else {
            model.addAttribute("message","删除失败!");
        }
        return "back/article/article_info";
    }
}
