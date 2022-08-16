package com.chenxiaojie.spring.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 * Desc: Hello World
 * ------------------------------------
 * Author: chenxiaojie
 * Date: 2019-07-08
 * Time: 20:55
 */
@Repository
public class IIntImpl implements IInt {

    private final static Logger log = LoggerFactory.getLogger(IIntImpl.class);

    @Cacheable(value = "IIntImpl.get(int i)", key = "'get_key_' + #i")
    @Override
    public int get(int i) {
        log.info("public int get(int i), i={}", i);
        return i + 100;
    }

    @CachePut(value = "IIntImpl.get(int i)", key = "'get_key_' + #i")
    @Override
    public int put(int i, int j) {
        log.info("public int put(int i, int j), i={}, j={}", i, j);
        return j;
    }

    @CacheEvict(value = "IIntImpl.get(int i)", key = "'get_key_' + #i")
    @Override
    public int evict(int i) {
        return i + 10000;
    }

    @Cacheable(value = "IIntImpl.get2(int i)", key = "'get2_key_' + #i")
    @Override
    public int get2(int i) {
        log.info("public int get2(int i), i={}", i);
        return i + 1000;
    }
}
