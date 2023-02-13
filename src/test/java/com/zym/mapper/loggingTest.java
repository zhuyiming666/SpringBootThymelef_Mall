package com.zym.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@SpringBootTest
@RunWith(SpringRunner.class)
public class loggingTest {

    Logger logger= LoggerFactory.getLogger(getClass());

    @Test
    public void test1(){

        //日志的级别(由低到高) trace<debug<info<warn<error
        //springboot默认使用的时info级别的
       logger.trace("这是trace日志");
       logger.debug("这是debug日志");
       logger.info("这是info日志");
       logger.warn("这是warn日志");
       logger.error("这是error日志");
    }


 }
