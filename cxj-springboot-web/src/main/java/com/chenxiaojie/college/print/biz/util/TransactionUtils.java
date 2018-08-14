package com.chenxiaojie.college.print.biz.util;

import com.google.common.collect.Lists;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Created by chenxiaojie on 16/8/19.
 */
public class TransactionUtils {

    private static final Logger logger = LoggerFactory.getLogger(TransactionUtils.class);

    public static <T> List<T> runWithThreadPool(final ExecutorService es, final String poolThreadNamePrefix, final List<Callable<T>> callableList) throws Exception {

        final Map<Object, Object> context = TransactionSynchronizationManager.getResourceMap();
        final Map<Integer, Exception> hasError = new ConcurrentHashMap<Integer, Exception>(1);
        List<Future<T>> futures = Lists.newArrayListWithExpectedSize(callableList.size());

        for (final Callable<T> callable : callableList) {
            futures.add(es.submit(new Callable<T>() {
                @Override
                public T call() throws Exception {
                    boolean isPoolThread = Thread.currentThread().getName().startsWith(poolThreadNamePrefix);
                    try {
                        if (isPoolThread && MapUtils.isNotEmpty(context)) {
                            //把事务交给resourceMap持有的线程来处置
                            for (Map.Entry<Object, Object> objectObjectEntry : context.entrySet()) {
                                TransactionSynchronizationManager.bindResource(objectObjectEntry.getKey(), objectObjectEntry.getValue());
                            }
                        } else {
                            logger.warn("isPoolThread: " + isPoolThread + ", TransactionSynchronizationManager context size:" + (context != null ? context.size() : 0));
                        }

                        return callable.call();
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                        hasError.put(1, e);
                        throw e;
                    } finally {

                        if (isPoolThread && MapUtils.isNotEmpty(context)) {
                            //清理上下文
                            for (Object key : context.keySet()) {
                                TransactionSynchronizationManager.unbindResourceIfPossible(key);
                            }
                        }
                    }
                }
            }));
        }
        List<T> result = Lists.newArrayListWithExpectedSize(callableList.size());
        for (Future<T> future : futures) {
            result.add(future.get());
        }
        if (hasError.size() != 0) {
            throw hasError.get(0);
        }
        return result;
    }
}