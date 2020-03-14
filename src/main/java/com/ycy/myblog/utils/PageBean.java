package com.ycy.myblog.utils;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 在视图、控制器、业务层之间进行数据传输的类，包含查询结果，分页信息；
 * @param <T>
 */

@Data
@Setter
@Getter
public class PageBean<T> implements Serializable {
    //返回记录集合;
    private List<T> list;

    //总记录数；
    private int allRow;

    //总页数;
    private int totalPage;

    //当前页数;
    private int currentPage;

    //每页记录数;
    private int pageSize = Const.PAGE_SIZE;
}
