package com.feng.interview.jdkDynamicProxy;

public class Main {
    public static void main(String[] args) {
        SmsService smsService = (SmsService)JdkProxyFactory.getProxy(new SmsServiceImpl());
        smsService.send("java");
    }
}
