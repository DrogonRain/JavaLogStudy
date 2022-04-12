package cn.zhangbin;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class TestJUL {

    // 获取logger对象
    public static final Logger LOGGER  = Logger.getLogger("mylog");

    @Test
    public void testJUL(){

        // 去掉默认配置
        LOGGER.setUseParentHandlers(false);
        // 难呢过不能添加我所需要的handlers
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter());
        handler.setLevel(Level.ALL);
        FileHandler fileHandler = null;

        try {
            /**
             * pattern: 文件路径
             * append: true - 追加
             *         false - 覆盖
             */
            fileHandler = new FileHandler("/Users"+ File.separator+"zhangbin"+ File.separator+"Desktop"+ File.separator+"idea"+ File.separator+"test.log",true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.ALL);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LOGGER.addHandler(handler);
        LOGGER.addHandler(fileHandler);

        LOGGER.severe("log Severe");
        LOGGER.warning("log Warning");
        LOGGER.info("log Info");
        LOGGER.config("log Config");
        LOGGER.fine("log Fine");
    }

    @Test
    public void test2(){

        LOGGER.setUseParentHandlers(false);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                Date date = new Date(record.getMillis());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
                String dateString = dateFormat.format(date);
                return dateString+" "+record.getSourceMethodName()+" "+record.getMessage()+"\r\n";
            }
        });
        consoleHandler.setLevel(Level.ALL);
        consoleHandler.setFilter(logRecord -> logRecord.getMessage().contains("see"));

        LOGGER.addHandler(consoleHandler);
        LOGGER.severe("see log Severe");
        LOGGER.warning("log Warning");
        LOGGER.info("log Info");
        LOGGER.config("log Config");
        LOGGER.fine("log Fine");
    }

    @Test
    public void testStringFormatter(){
        System.out.printf("%,d",66622222);
        System.out.println();
        Date date = new Date();
        String format = String.format("%tF %tr", date,date);
        System.out.println(format);
    }

    @Test
    public void readConfig() throws IOException {
        LogManager logManager = LogManager.getLogManager();
        // 获取流
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("logging.properties");
        logManager.readConfiguration(inputStream);
        Logger logger = Logger.getLogger(TestJUL.class.getName());
        logger.fine("fine");
        try{
            int i = 1/0;
        }catch (ArithmeticException e){
            logger.throwing(TestJUL.class.getName(),"readConfig",e);
        }

    }
}
