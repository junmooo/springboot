package com.junmooo.springbootdemo.service.demo;

import java.util.concurrent.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import com.junmooo.springbootdemo.entity.demo.HelloEntity;


@Service
public class HelloService {

    Logger logger = LoggerFactory.getLogger(HelloService.class);

    final String start = "start say hello";
    final String end = "end say hello";
    /**
     * @Async标注的方法，称之为异步方法；这些方法将在执行的时候，
     * 将会在独立的线程中被执行，调用者无需等待它的完成，即可继续其他的操作。
     */
    @Async("myThreadPool") // 使用异步方法
    public void sayHello() {
        logger.info(start);
        logger.info(Thread.currentThread().getName());
        logger.info("hello1");
        logger.info(end);
    }

    @Async("threadPoolTaskExecutor") // 使用异步方法
    public void sayHello2() {
        logger.info(start);
        logger.info(Thread.currentThread().getName());
        logger.info("hello2");
        logger.info(end);
    }
//    @Async // 使用异步方法
//    public void sayHello3() {
//        logger.info(start);
//        logger.info(Thread.currentThread().getName());
//        logger.info("hello2");
//        logger.info(end);
//    }

    @Async
    public Future<HelloEntity> getHelloString() {
        logger.info("start getHelloString");
        HelloEntity helloEntity = new HelloEntity();
        helloEntity.setHelloStr("Say hello to little wang");
        logger.info(Thread.currentThread().getName());

        logger.info("end getHelloString");
        return new AsyncResult<>(helloEntity);
    }
}
