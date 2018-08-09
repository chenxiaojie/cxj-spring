package com.gc.college.print.biz.model;

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
public class CollegeModel {
    private int id;
    private String collegeName;
    private String collegeAddress;
    private Date now = new Date();
}
