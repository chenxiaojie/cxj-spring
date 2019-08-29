package com.chenxiaojie.college.print.biz.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by chenxiaojie on 16/9/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "大学实体")
public class CollegeModel {
    @ApiModelProperty(value = "大学id", position = 1)
    private int id;
    @ApiModelProperty(value = "大学名称", position = 2)
    private String collegeName;
    @ApiModelProperty(value = "大学地址", position = 3)
    private String collegeAddress;
    @ApiModelProperty(value = "更新时间", position = 4)
    private Date dt;
}
