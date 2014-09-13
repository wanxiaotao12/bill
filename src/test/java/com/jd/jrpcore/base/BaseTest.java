package com.jd.jrpcore.base;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public abstract class BaseTest {
    public final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    protected static ClassPathXmlApplicationContext appContext;


    @BeforeClass
    public static  void setUp() throws Exception {
        try {
            long start = System.currentTimeMillis();
            System.out.println("正在加载配置文件...");

            appContext =  new ClassPathXmlApplicationContext(new String[]{"spring-config.xml"});


            System.out.println("配置文件加载完毕,耗时：" + (System.currentTimeMillis() - start));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    protected boolean setProtected() {
        return false;
    }

    @Before
    public void autoSetBean() {
        appContext.getAutowireCapableBeanFactory().autowireBeanProperties(this, DefaultListableBeanFactory.AUTOWIRE_BY_NAME, false);
    }

    @AfterClass
    public static  void tearDown() throws Exception {
    }
}
