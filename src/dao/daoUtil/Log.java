package dao.daoUtil;

import java.time.LocalDateTime;

public class Log {

    public static void info(String className, String targetName, String message){

        System.out.printf("%s [INFO]-----%s----%s----%s", LocalDateTime.now(),className,targetName,message);
    }

    public static void error(String className, String targetName, String message){

        System.out.printf("%s [ERROR]-----%s----%s----%s", LocalDateTime.now(),className,targetName,message);
    }
}
