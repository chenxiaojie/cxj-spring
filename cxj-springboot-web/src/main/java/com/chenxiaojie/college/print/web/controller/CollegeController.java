package com.chenxiaojie.college.print.web.controller;

import com.chenxiaojie.college.print.biz.CollegeService;
import com.chenxiaojie.college.print.biz.model.CollegeModel;
import com.chenxiaojie.college.print.biz.model.PageModel;
import com.chenxiaojie.college.print.biz.model.ServiceResult;
import com.chenxiaojie.college.print.web.aop.annotation.PermissionController;
import com.chenxiaojie.college.print.web.aop.iface.Customable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by chenxiaojie on 16/9/16.
 */
@Slf4j
@RestController
@RequestMapping("/college")
@Api(tags = {"大学信息服务"})
public class CollegeController implements Customable {

    @Autowired
    private CollegeService collegeService;

    @Value("${management.server.name}")
    private String config;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据大学id查询大学信息")
    @ApiImplicitParam(name = "id", value = "大学id", paramType = "path")
    public ServiceResult<CollegeModel> query(@PathVariable(value = "id") int id) {
        log.info("${management.server.name} : " + config);
        return ServiceResult.success(collegeService.queryById(id));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @PermissionController
    public ServiceResult<PageModel<CollegeModel>> list(@RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

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
    public ServiceResult<CollegeModel> add(@RequestBody CollegeModel collegeModel) {

        if (StringUtils.isEmpty(collegeModel.getCollegeName())) {
            return ServiceResult.fail("collegeName cannot be empty!");
        }

        if (StringUtils.isEmpty(collegeModel.getCollegeAddress())) {
            return ServiceResult.fail("collegeAddress cannot be empty!");
        }

        return ServiceResult.success(collegeService.add(collegeModel));
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ServiceResult<String> modify(@PathVariable(value = "id") int id,
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
    public ServiceResult<Void> delete(@PathVariable(value = "id") int id) {

        if (collegeService.removeById(id)) {
            return ServiceResult.success();
        }

        return ServiceResult.fail("not found id:" + id);
    }

    @Override
    public void custom() {

    }


    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public ServiceResult<List<Integer>> test1(@RequestParam("aaa") List<Integer> aaa) {
        System.out.println("test1");
        return ServiceResult.success(aaa);
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public ServiceResult<List<String>> test2(@RequestParam("aaa") List<String> aaa) {
        System.out.println("test2");
        return ServiceResult.success(aaa);
    }

    @RequestMapping(value = "/test3/{aaa}", method = RequestMethod.GET)
    public ServiceResult<List<Integer>> test3(@PathVariable("aaa") List<Integer> aaa) {
        System.out.println("test3");
        return ServiceResult.success(aaa);
    }

    @RequestMapping(value = "/test4/{aaa}", method = RequestMethod.GET)
    public ServiceResult<List<String>> test4(@PathVariable("aaa") List<String> aaa) {
        System.out.println("test4");
        return ServiceResult.success(aaa);
    }

    @RequestMapping(value = "/test5", method = RequestMethod.GET)
    public ServiceResult<String[]> test5(@RequestParam("aaa") String[] aaa) {
        System.out.println("test5");
        return ServiceResult.success(aaa);
    }

    @RequestMapping(value = "/test6", method = RequestMethod.GET)
    public ServiceResult<Integer[]> test6(@RequestParam("aaa") Integer[] aaa) {
        System.out.println("test6");
        return ServiceResult.success(aaa);
    }
}