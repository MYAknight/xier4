package util;

import org.apache.log4j.Logger;

/**
 * @author kevin
 * @version 1.0
 * @description     将日志的各种级别输出到控制台与文件
 * @createDate 2018/12/29
 */
public class Log4jDemo {

    public static Logger logger = Logger.getLogger(Log4jDemo.class);

    public static void main(String[] args) {
        //获取Logger对象的实例
        Logger logger = Logger.getLogger(Log4jDemo.class);
        logger.debug("这是debug");
        logger.info("这是info");
        logger.warn("这是warn");
        logger.error("这是error");
        logger.fatal("这是fatal");
    }
}