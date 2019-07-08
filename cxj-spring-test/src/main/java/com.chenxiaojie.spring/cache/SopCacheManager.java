package com.chenxiaojie.spring.cache;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleValueWrapper;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SopCacheManager implements CacheManager {

    private final static Logger log = LoggerFactory.getLogger(SopCacheManager.class);

    private final ConcurrentMap<String, SopCache> cacheMap = Maps.newConcurrentMap();

    public Cache getCache(String name) {
        log.info("public Cache getCache(String name), name={}", name);
        SopCache cache = cacheMap.get(name);
        if (null == cache) {
            cache = new SopCache(name);
            SopCache previous = cacheMap.putIfAbsent(name, cache);
            if (null != previous) {
                return previous;
            }
        }
        return cache;
    }

    @Override
    public Collection<String> getCacheNames() {
        log.info("public Collection<String> getCacheNames()");
        return cacheMap.keySet();
    }

    private static class SopCache implements Cache {

        private String name;

        private Map<Object, Integer> cache = new ConcurrentHashMap<>();

        private SopCache(String name) {
            log.info("private SopCache(String name), name={}", name);
            this.name = name;
        }

        @Override
        public String getName() {
            log.info("public String getName()");
            return name;
        }

        @Override
        public Object getNativeCache() {
            log.info("public Object getNativeCache()");
            return cache;
        }

        @Override
        public ValueWrapper get(Object key) {
            log.info("public ValueWrapper get(Object key), key={}", key);
            Integer integer = cache.get(key);
            if (integer == null) {
                return null;
            }
            return new SimpleValueWrapper(integer);
        }

        @Override
        public <T> T get(Object key, Class<T> type) {
            log.info("public <T> T get(Object key, Class<T> type), key={}, type={}", key, type.getName());
            ValueWrapper wrapper = get(key);
            return (T) wrapper.get();
        }

        @Override
        public <T> T get(Object key, Callable<T> valueLoader) {
            log.info("public <T> T get(Object key, Callable<T> valueLoader), key={}, valueLoader={}", key, valueLoader);
            ValueWrapper wrapper = get(key);
            return (T) wrapper.get();
        }

        @Override
        public void put(Object key, Object value) {
            log.info("public void put(Object key, Object value), key={}, value={}", key, value);
            cache.put(key, Integer.valueOf(value.toString()));
        }

        @Override
        public ValueWrapper putIfAbsent(Object key, Object value) {
            log.info("public ValueWrapper putIfAbsent(Object key, Object value), key={}, value={}", key, value);
            ValueWrapper valueWrapper = get(key);
            if (valueWrapper == null) {
                put(key, value);
                return null;
            } else {
                return valueWrapper;
            }
        }

        @Override
        public void evict(Object key) {
            log.info("public void evict(Object key), key={}", key);
            cache.remove(key);
        }

        @Override
        public void clear() {
            log.info("public void clear()");
        }

    }
}
