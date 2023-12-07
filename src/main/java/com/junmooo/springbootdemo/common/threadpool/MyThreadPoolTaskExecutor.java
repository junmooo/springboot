package com.junmooo.springbootdemo.common.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;


/**
 * <p>
 * 自定义线程池
 * </p>
 *
 * @author qingbguo(qingbguo @ paypal.com)
 * @version 1.0.0
 * @copyright Copyright(c) 2011-2020, Gopay All Rights Reserved.
 * @date 2023/12/6 12:52
 */
public class MyThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {
    final ThreadLocal<Logger> myLogger =
            ThreadLocal.withInitial(() -> LoggerFactory.getLogger(MyThreadPoolTaskExecutor.class));

    @Override
    public void execute(Runnable task) {
        logThreadPoolStatus();
        super.execute(task);
    }

    @Override
    public void execute(Runnable task, long startTimeout) {
        logThreadPoolStatus();
        super.execute(task, startTimeout);
    }

    @Override
    public Future<?> submit(Runnable task) {
        logThreadPoolStatus();
        return super.submit(task);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        logThreadPoolStatus();
        return super.submit(task);
    }

    @Override
    public ListenableFuture<?> submitListenable(Runnable task) {
        logThreadPoolStatus();
        return super.submitListenable(task);
    }

    @Override
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        logThreadPoolStatus();
        return super.submitListenable(task);
    }

    /**
     * 在线程池运行的时候输出线程池的基本信息
     */
    private void logThreadPoolStatus() {
        myLogger.get().info("核心线程数:{}, 最大线程数:{}, 当前线程数: {}, 活跃的线程数: {}",
                getCorePoolSize(), getMaxPoolSize(), getPoolSize(), getActiveCount());
    }
}
