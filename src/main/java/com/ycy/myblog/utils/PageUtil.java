package com.ycy.myblog.utils;

/**
 * 分页工具类
 */
public class PageUtil {
    //计算总页数;allRow -- 总记录数,pagesize -- 每页显示记录数;
    public static int countTotalPage(int allRow,int pagsize) {
        return allRow % pagsize == 0 ? allRow / pagsize : allRow % pagsize + 1;
    }

    //计算当前页数
    public static int countCurrentPage(Integer currentPage) {
        if (currentPage == null) {
            currentPage=1;
        }
            return currentPage == 0 ? 1 : currentPage;
    }

    //计算起始记录数;
    public static int startPage(int pageSize , int currentPage) {
        return pageSize * (currentPage - 1);
    }
}
