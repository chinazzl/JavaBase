package co.src.com.learnLog.LearnLog4J;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.LogLog;
import org.junit.Test;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/3/15 16:48
 * @Modified By：
 */
public class TestLog4j {
    //使用properties进行开启RootLog和Appender
    @Test
    public void test1(){
        //开启Log4j中自己的debugger
//        LogLog.setInternalDebugging(true);
        Logger logger = Logger.getLogger(TestLog4j.class);
//        LogLog.debug("ehe");
        logger.info("hello");
        logger.error("error");
        logger.debug("debug");
    }

    @Test
    public void test2(){
    }
}
