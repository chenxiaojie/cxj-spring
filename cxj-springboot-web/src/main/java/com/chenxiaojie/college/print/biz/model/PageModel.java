package com.chenxiaojie.college.print.biz.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "分类返回实体")
public class PageModel<T> {
    /**
     * 数据
     */
    @ApiModelProperty(value = "数据list", position = 1)
    private List<T> list;
    /**
     * 当前页码
     */
    @ApiModelProperty(value = "当前页码", position = 2)
    private int pageIndex;
    /**
     * 每页多少条数据
     */
    @ApiModelProperty(value = "每页多少条数据", position = 3)
    private int pageSize;
    /**
     * 共多少条数据
     */
    @ApiModelProperty(value = "共多少条数据", position = 4)
    private int totalCount;
}
