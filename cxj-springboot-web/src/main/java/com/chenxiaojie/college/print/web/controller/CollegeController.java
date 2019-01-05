package com.chenxiaojie.college.print.web.controller;

import com.chenxiaojie.college.print.biz.CollegeService;
import com.chenxiaojie.college.print.biz.model.CollegeModel;
import com.chenxiaojie.college.print.biz.model.PageModel;
import com.chenxiaojie.college.print.biz.model.ServiceResult;
import com.chenxiaojie.college.print.web.aop.annotation.PermissionController;
import com.chenxiaojie.college.print.web.aop.iface.Customable;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by chenxiaojie on 16/9/16.
 */
@Slf4j
@RestController
@RequestMapping("/college")
public class CollegeController implements Customable {

    @Autowired
    private CollegeService collegeService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ServiceResult query(@PathVariable(value = "id") int id) {
        return ServiceResult.success(collegeService.queryById(id));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @PermissionController
    public ServiceResult list(@RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        if (pageSize > 20) {
            pageSize = 20;
        } else if (pageSize < 0) {
            pageSize = 10;
        }

        if (pageIndex < 1) {
            pageIndex = 1;
        }

        int offset = (pageIndex - 1) * pageSize;

        return ServiceResult.success(PageModel.
                <CollegeModel>builder()
                .list(collegeService.queryByPage(offset, pageSize))
                .pageIndex(pageIndex)
                .totalCount(collegeService.countByPage())
                .pageSize(pageSize)
                .build());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ServiceResult add(@RequestBody CollegeModel collegeModel) {

        if (StringUtils.isEmpty(collegeModel.getCollegeName())) {
            return ServiceResult.fail("collegeName cannot be empty!");
        }

        if (StringUtils.isEmpty(collegeModel.getCollegeAddress())) {
            return ServiceResult.fail("collegeAddress cannot be empty!");
        }

        return ServiceResult.success(collegeService.add(collegeModel));
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ServiceResult modify(@PathVariable(value = "id") int id,
                                @RequestBody CollegeModel collegeModel) {

        if (id == 0) {
            return ServiceResult.fail("id cannot be zero!");
        }

        collegeModel.setId(id);

        if (collegeService.modify(collegeModel)) {
            return ServiceResult.success();
        }
        return ServiceResult.success("没有任何变更");

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ServiceResult delete(@PathVariable(value = "id") int id) {

        if (collegeService.removeById(id)) {
            return ServiceResult.success();
        }

        return ServiceResult.fail("not found id:" + id);
    }

    @Override
    public void custom() {

    }


    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public ServiceResult test1(@RequestParam("aaa") List<Integer> aaa) {
        System.out.println("test1");
        return ServiceResult.success(aaa);
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public ServiceResult test2(@RequestParam("aaa") List<String> aaa) {
        System.out.println("test2");
        return ServiceResult.success(aaa);
    }

    @RequestMapping(value = "/test3/{aaa}", method = RequestMethod.GET)
    public ServiceResult test3(@PathVariable("aaa") List<Integer> aaa) {
        System.out.println("test3");
        return ServiceResult.success(aaa);
    }

    @RequestMapping(value = "/test4/{aaa}", method = RequestMethod.GET)
    public ServiceResult test4(@PathVariable("aaa") List<String> aaa) {
        System.out.println("test4");
        return ServiceResult.success(aaa);
    }

    @RequestMapping(value = "/test5", method = RequestMethod.GET)
    public ServiceResult test5(@RequestParam("aaa") String[] aaa) {
        System.out.println("test5");
        return ServiceResult.success(aaa);
    }

    @RequestMapping(value = "/test6", method = RequestMethod.GET)
    public ServiceResult test6(@RequestParam("aaa") Integer[] aaa) {
        System.out.println("test6");
        return ServiceResult.success(aaa);
    }
}