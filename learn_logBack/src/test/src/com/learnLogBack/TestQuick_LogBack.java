package src.com.learnLogBack;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/3/22 13:00
 * @Modified By：
 */
public class TestQuick_LogBack {

    private static final Logger logger = LoggerFactory.getLogger(TestQuick_LogBack.class);

    @Test
    public void testQuick(){
        for (long i = 0; i < 5000; i++) {

            logger.info("info");
            logger.debug("debug");
            logger.warn("warn");
            logger.error("error"); //默认
            logger.trace("trace");
        }
    }
}
