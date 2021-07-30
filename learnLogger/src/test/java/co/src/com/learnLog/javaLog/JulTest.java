package co.src.com.learnLog.javaLog;

import javafx.scene.shape.Path;
import org.junit.Test;

import java.io.IOException;
import java.util.logging.*;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/3/14 15:06
 * @Modified By：
 *  JUL : Java Utils Log
 */
public class JulTest {

    private static final Logger logger = Logger.getLogger("src.com.learnLog.JulTest");

    @Test
    public void testJul_1(){
//        Logger logger = Logger.getLogger("src.com.learnLog.JulTest");
        //日志记录输出
        logger.info("hello");
        logger.severe("servere");
        logger.warning("warning");
        logger.info("info");
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");
    }

    //自定义Logger级别 1. 控制台输出 2. 保存为文件
    @Test
    public void testJul_2() throws IOException {
//        Logger logger = Logger.getLogger("src.com.learnLog.JulTest");
        //关闭系统默认配置
        logger.setUseParentHandlers(false);

        //使用控制台输出
        ConsoleHandler consoleHandler = new ConsoleHandler();

        //关联
        Formatter formatter = new SimpleFormatter();
        consoleHandler.setFormatter(formatter);
        logger.addHandler(consoleHandler);
        logger.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);

        //导出为file
        FileHandler fileHandler = new FileHandler("/localLog.log");
        fileHandler.setFormatter(formatter);
        logger.addHandler(fileHandler);
        //输出
        logger.severe("servere");
        logger.warning("warning");
        logger.info("info");
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");
    }

    /**
     * Logger的父子关系
     *  |___ 顶级 java.util.logging.LogManager$RootLogger@3b7951
     *      |___ Logger2 co   父级
     *          |___Logger1 src.com.learnLog   子集
     *
     */
    @Test
    public void testLogger_Parent(){
        Logger logger1 = Logger.getLogger("co.src.com.learnLog");
        Logger logger2 = Logger.getLogger("co");

        System.out.println(logger1.getParent() == logger2); //true

        logger2.setUseParentHandlers(false);
        //使用控制台输出
        ConsoleHandler consoleHandler = new ConsoleHandler();

        //关联
        Formatter formatter = new SimpleFormatter();
        consoleHandler.setFormatter(formatter);
        logger2.addHandler(consoleHandler);
        logger2.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);
        logger2.addHandler(consoleHandler);
        //输出
        logger1.severe("servere");
        logger1.warning("warning");
        logger1.info("info");
        logger1.config("config");
        logger1.fine("fine");
        logger1.finer("finer");
        logger1.finest("finest");
    }
}
