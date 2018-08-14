package com.chenxiaojie.college.print.biz.util;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by chenxiaojie on 2016/12/17.
 */
public class Lists2 {

    public static <F, T> List<T> transform(List<F> fromList, Function<? super F, ? extends T> function) {
        if (CollectionUtils.isEmpty(fromList)) {
            return Collections.emptyList();
        }
        return Lists.transform(fromList, function);
    }

    public static <F, T> List<T> transformOnTime(List<F> fromList, Function<? super F, ? extends T> function) {
        if (CollectionUtils.isEmpty(fromList)) {
            return Collections.emptyList();
        }
        return Lists.newArrayList(Lists.transform(fromList, function));
    }

    public static <T, F> T reduce(Collection<F> source, Reducer<F, T> trans, T init) {
        if (CollectionUtils.isEmpty(source)) {
            return init;
        }
        T pre = init;
        for (F f : source) {
            pre = trans.apply(pre, f);
        }
        return pre;
    }

    public static <KEY, VALUE, ORI> Map<KEY, VALUE> trans2Map(List<ORI> source, Function<ORI, KEY> keyGen, Function<ORI, VALUE> valGen) {
        Map<KEY, VALUE> res = Maps.newHashMap();
        if (CollectionUtils.isEmpty(source)) {
            return res;
        }
        for (ORI t : source) {
            res.put(keyGen.apply(t), valGen.apply(t));
        }
        return res;
    }


    public static <KEY, ORI> Map<KEY, ORI> trans2Map(List<ORI> source, Function<ORI, KEY> keyGen) {
        Map<KEY, ORI> res = Maps.newHashMap();
        if (CollectionUtils.isEmpty(source)) {
            return res;
        }
        for (ORI t : source) {
            res.put(keyGen.apply(t), t);
        }
        return res;
    }

    public static <E> Optional<E> fetchFirst(List<E> source, Predicate<E> predicate) {
        for (E e : source) {
            if (predicate.apply(e)) {
                return Optional.of(e);
            }
        }
        return Optional.absent();
    }
}
