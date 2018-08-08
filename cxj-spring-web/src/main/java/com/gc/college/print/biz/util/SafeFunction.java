package com.gc.college.print.biz.util;

import com.google.common.base.Function;

public abstract class SafeFunction<F, T> implements Function<F, T> {

    @Override
    public final T apply(F input) {
        if (null == input) {
            return null;
        }
        return safeApply(input);
    }

    public abstract T safeApply(F input);
}