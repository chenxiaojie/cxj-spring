package com.gc.college.print.biz;

import com.gc.college.print.biz.model.CollegeModel;

import java.util.List;

/**
 * Created by chenxiaojie on 16/9/16.
 */
public interface CollegeService {

    CollegeModel queryById(int id);

    List<CollegeModel> queryByPage(int offset, int limit);

    int countByPage();

    CollegeModel add(CollegeModel collegeModel);

    boolean modify(CollegeModel collegeModel);

    boolean removeById(int id);
}
