package com.ycy.myblog.utils;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 分页管理
 */
@Data
@Setter
@Getter
public class BaseBean implements Serializable {
    //起始页
    private Integer start;
    //长度；
    private Integer length;
}
