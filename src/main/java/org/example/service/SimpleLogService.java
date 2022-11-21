package org.example.service;


import org.springframework.stereotype.Component;

@Component
public class SimpleLogService {

    public static void logInfo(String key,String message){

        System.out.printf("%s | INFO : %s\n",key,message);
    }

    public static void logWarn(String key,String message){

        System.err.printf("%s | WARN : %s\n",key,message);
    }

}
