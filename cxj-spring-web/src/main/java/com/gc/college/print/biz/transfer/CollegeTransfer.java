package com.gc.college.print.biz.transfer;

import com.gc.college.print.biz.model.CollegeModel;
import com.gc.college.print.biz.util.SafeFunction;
import com.gc.college.print.dao.entity.CollegeEntity;
import com.google.common.base.Function;
import org.springframework.cglib.beans.BeanCopier;

/**
 * Created by marco on 16/5/12.
 */
public class CollegeTransfer {

    private static final BeanCopier entityToModelCopier = BeanCopier.create(CollegeEntity.class, CollegeModel.class, false);
    private static final BeanCopier modelToEntityCopier = BeanCopier.create(CollegeModel.class, CollegeEntity.class, false);

    public static final Function<CollegeEntity, CollegeModel> EntityToModel = new SafeFunction<CollegeEntity, CollegeModel>() {
        @Override
        public CollegeModel safeApply(CollegeEntity input) {
            CollegeModel collegeModel = new CollegeModel();
            entityToModelCopier.copy(input, collegeModel, null);
            return collegeModel;
        }
    };

    public static final Function<CollegeModel, CollegeEntity> ModelToEntity = new SafeFunction<CollegeModel, CollegeEntity>() {
        @Override
        public CollegeEntity safeApply(CollegeModel input) {
            CollegeEntity college = new CollegeEntity();
            modelToEntityCopier.copy(input, college, null);
            return college;
        }
    };

}