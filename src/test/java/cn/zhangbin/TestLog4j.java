package cn.zhangbin;

import org.apache.log4j.*;
import org.apache.log4j.jdbc.JDBCAppender;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.Test;

import java.io.InputStream;
import java.io.PrintWriter;

public class TestLog4j {

    private static final Logger LOGGER = Logger.getLogger(TestLog4j.class);

    @Test
    public void testLog4j(){

        Logger rootLogger = Logger.getRootLogger();
        // 构建一个consoleAppender
        ConsoleAppender consoleAppender = new ConsoleAppender();
        // consoleAppender需要一个输出流
        consoleAppender.setWriter(new PrintWriter(System.out));
//        Layout layout = new Layout() {
//            @Override
//            public String format(LoggingEvent loggingEvent) {
//                return loggingEvent.getThreadName() + " --- "+loggingEvent.getMessage() + "\r\n";
//            }
//
//            @Override
//            public boolean ignoresThrowable() {
//                return false;
//            }
//
//            @Override
//            public void activateOptions() {
//
//            }
//        };

//        Layout layout = new SimpleLayout();

//        Layout layout = new PatternLayout(PatternLayout.TTCC_CONVERSION_PATTERN);
        Layout layout = new PatternLayout("%d %l %m%n ");

        JDBCAppender jdbcAppender = new JDBCAppender();
        jdbcAppender.setDriver("com.mysql.jdbc.Driver");
        jdbcAppender.setURL("jdbc:mysql://localhost:3306/ydl?characterEncoding=utf8&useSSL=false&serverTimezone=UTC");
        jdbcAppender.setUser("root");
        jdbcAppender.setPassword("q1127965");
        jdbcAppender.setSql("INSERT INTO log(project_name,create_date,level,category,file_name,thread_name,line,all_category,message) values('zhangbin','%d{yyyy-MM-ddHH:mm:ss}','%p','%c','%F','%t','%L','%l','%m')");

        consoleAppender.setLayout(layout);
        rootLogger.addAppender(consoleAppender);
        rootLogger.addAppender(jdbcAppender);

        // 日志记录输出
        LOGGER.info("hello log4j");
        // 日志级别
        LOGGER.fatal("fatal"); // 严重错误，一般会造成系统崩溃和终止运行
        LOGGER.error("error"); // 错误信息，但不会影响系统运行
        LOGGER.warn("warn"); // 警告信息，可能会发生问题
        LOGGER.info("info"); // 程序运行信息，数据库的连接、网络、IO操作等
        LOGGER.debug("debug"); // 调试信息，一般在开发阶段使用，记录程序的变量、参数等
        LOGGER.trace("trace"); // 追踪信息，记录程序的所有流程信息
    }

    @Test
    public void log4jFileAppender(){

        // 日志记录输出
        LOGGER.info("hello log4j");
        // 日志级别
        LOGGER.fatal("fatal"); // 严重错误，一般会造成系统崩溃和终止运行
        LOGGER.error("error"); // 错误信息，但不会影响系统运行
        LOGGER.warn("warn"); // 警告信息，可能会发生问题
        LOGGER.info("info"); // 程序运行信息，数据库的连接、网络、IO操作等
        LOGGER.debug("debug"); // 调试信息，一般在开发阶段使用，记录程序的变量、参数等
        LOGGER.trace("trace"); // 追踪信息，记录程序的所有流程信息
    }

}
