package com.feng.interview.javabase.jdkDynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DebugInvocationHandler implements InvocationHandler {
    private final Object target;

    public DebugInvocationHandler(Object object) {
        this.target = object;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before method:" + method.getName());
        Object result = method.invoke(target, args);
        System.out.println("after method:" + method.getName());
        return result;
    }
}
