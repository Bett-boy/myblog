package com.ycy.myblog.controller.back;

import com.ycy.myblog.bean.CategoryInfo;
import com.ycy.myblog.service.CategoryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;
import javax.websocket.server.PathParam;
import java.util.List;

/**
 * 栏目管理
 */
@Controller
@RequestMapping("/back/category/")
public class CategoryController {

    @Autowired
    private CategoryInfoService categoryInfoService;

    //查询;
    @RequestMapping("list")
    public String list(Model model,CategoryInfo categoryInfo) {
        List<CategoryInfo> cateList = categoryInfoService.getCateList(categoryInfo);
        model.addAttribute("cateList",cateList);
        return "back/category/category_list";
    }

    //添加;
    @RequestMapping("add")
    public String add(CategoryInfo categoryInfo,Model model) {
        try {
            boolean add = categoryInfoService.add(categoryInfo);
            if (add) {
                model.addAttribute("message", "添加栏目信息成功");
            }
        }catch (Exception e) {
                model.addAttribute("message", "添加栏目信息失败!");
            e.printStackTrace();
        }
        return "back/category/category_info";
    }

    //修改用户信息；
    @RequestMapping("get/{id}")
    public String loadUpdate(Model model,@PathVariable("id")Integer categoryId) {
        CategoryInfo categoryInfo = categoryInfoService.getCategoryInfo(categoryId);
        if (categoryInfo != null ) {
            model.addAttribute("categoryinfo",categoryInfo);
        }
        return "back/category/category_update";
    }

    //修改
    @RequestMapping("update")
    public String update(CategoryInfo categoryInfo,Model model) {
        boolean update = categoryInfoService.update(categoryInfo);
        if (update) {
            model.addAttribute("message","更新成功");
        }else {
            model.addAttribute("message","更新失败!");
        }
        model.addAttribute("categoryinfo",categoryInfo);
        return "back/category/category_update";
    }

    //删除栏目、文章信息;
    @RequestMapping("delete/{id}")
    public String delete(Model model,@PathVariable("id")Integer categoryId) {
        try {
            categoryInfoService.delete(categoryId);
            model.addAttribute("message","删除成功");
        }catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message","删除失败！");
        }
        return "back/category/category_info";
    }
}
