package org.example.service;


import org.springframework.stereotype.Component;

@Component
public class SimpleLogService {

    public static void logInfo(String str){
        System.out.println(str);
    }

    public static void logWarn(String str){
        System.err.println(str);
    }
}
