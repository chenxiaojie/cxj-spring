package com.chenxiaojie.spring.dependencies;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Repository;

/**
 * Created by chenxiaojie on 2016/11/26.
 */
@Repository
public class C implements SmartLifecycle {
    public C() {
        System.out.println("C()");
    }

    @Override
    public boolean isAutoStartup() {
        System.out.println("C isAutoStartup");
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        System.out.println("C stop Runnable");
    }

    @Override
    public void start() {
        System.out.println("C build");
    }

    @Override
    public void stop() {
        System.out.println("C stop");
    }

    @Override
    public boolean isRunning() {
        System.out.println("C isRunning");
        return false;
    }

    @Override
    public int getPhase() {
        System.out.println("C getPhase");
        return 0;
    }
}
