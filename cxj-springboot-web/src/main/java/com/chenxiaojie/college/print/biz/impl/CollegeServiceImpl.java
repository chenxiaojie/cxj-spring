package com.chenxiaojie.college.print.biz.impl;

import com.chenxiaojie.college.print.biz.CollegeService;
import com.chenxiaojie.college.print.biz.model.CollegeModel;
import com.chenxiaojie.college.print.biz.transfer.CollegeTransfer;
import com.chenxiaojie.college.print.biz.util.ExecutorUtils;
import com.chenxiaojie.college.print.biz.util.Lists2;
import com.chenxiaojie.college.print.biz.util.TransactionUtils;
import com.chenxiaojie.college.print.dao.api.CollegeDAO;
import com.chenxiaojie.college.print.dao.entity.CollegeEntity;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/**
 * Created by chenxiaojie on 16/9/16.
 */
@Slf4j
@Service
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    private CollegeDAO collegeDAO;

    private final static ExecutorService es = ExecutorUtils.createExecutorService(10, "CollegeService");

    @Override
    @Transactional
    public CollegeModel add(CollegeModel collegeModel) {
//        TransactionSynchronizationManager.clear();
        CollegeEntity college = CollegeTransfer.ModelToEntity.apply(collegeModel);
        collegeDAO.insert(college);

        List<Callable<CollegeEntity>> callableList = Lists.newArrayList();
        for (int i = 0; i < 5; i++) {
            final int finalI = i;
            callableList.add(new Callable<CollegeEntity>() {
                @Override
                public CollegeEntity call() throws Exception {
                    if (finalI > 3) {
                        throw new RuntimeException("测试异常,请忽略");
                    }
                    CollegeEntity collegeEntity = new CollegeEntity(finalI, college.getCollegeName() + "_" + finalI, college.getCollegeAddress() + "_" + finalI, new Date());
                    collegeDAO.insert(collegeEntity);
                    return collegeEntity;
                }
            });
        }

        try {
            List<CollegeEntity> list = TransactionUtils.runWithThreadPool(es, "CollegeService", callableList);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

//        int i = 1 / 0;

        collegeModel.setId(college.getId());
        return collegeModel;
    }

    @Override
    public CollegeModel queryById(int id) {
        return CollegeTransfer.EntityToModel.apply(collegeDAO.selectById(id));
    }

    @Override
    public List<CollegeModel> queryByPage(int offset, int limit) {
        return Lists2.transform(collegeDAO.selectByPage(offset, limit), CollegeTransfer.EntityToModel);
    }

    @Override
    public int countByPage() {
        return collegeDAO.countByPage();
    }

    @Override
    public boolean modify(CollegeModel collegeModel) {
        CollegeEntity college = CollegeTransfer.ModelToEntity.apply(collegeModel);
        return collegeDAO.update(college) > 0;
    }

    @Override
    public boolean removeById(int id) {
        return collegeDAO.deleteById(id) > 0;
    }
}
