package com.gc.college.print.biz.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by chenxiaojie on 2016/12/17.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageModel<T> {
    /**
     * 数据
     */
    private List<T> list;
    /**
     * 当前页码
     */
    private int pageIndex;
    /**
     * 每页多少条数据
     */
    private int pageSize;
    /**
     * 共多少条数据
     */
    private int totalCount;
}
