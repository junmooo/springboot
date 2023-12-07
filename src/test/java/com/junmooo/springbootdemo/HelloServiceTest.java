package com.junmooo.springbootdemo;

import java.util.concurrent.Future;
import com.junmooo.springbootdemo.entity.demo.HelloEntity;
import com.junmooo.springbootdemo.service.demo.HelloService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootdemoApplication.class)
public class HelloServiceTest {

    Logger logger = LoggerFactory.getLogger(HelloServiceTest.class);
    @Autowired
    HelloService helloService;

//    @Test
//    public void testSayHello() throws Exception {
//        helloService.sayHello();
//        helloService.sayHello2();
//        helloService.sayHello3();
//        helloService.getHelloString();
//    }
//    @Test
//    public void testGetHelloString() throws Exception {
//        Future<HelloEntity> helloString = helloService.getHelloString();
//        HelloEntity helloEntity = helloString.get();
//        logger.info(31 + helloEntity.getHelloStr());
//    }
}