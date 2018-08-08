package com.gc.college.print.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by chenxiaojie on 16/9/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollegeEntity {
    private int id;
    private String collegeName;
    private String collegeAddress;
}