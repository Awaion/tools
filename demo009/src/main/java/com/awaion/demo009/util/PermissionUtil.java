package com.awaion.demo009.util;

import java.lang.reflect.Method;

public class PermissionUtil {
    /**
     * 根据方法的引用返回该方法的权限表达式
     *
     * @param method
     * @return
     */
    public static String buildExpression(Method method) {
        String className = method.getDeclaringClass().getName();
        String methodName = method.getName();
        return className + ":" + methodName;
    }
}
