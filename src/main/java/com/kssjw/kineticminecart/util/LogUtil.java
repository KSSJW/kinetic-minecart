package com.kssjw.kineticminecart.util;

// 笨猫猫额外写的日志系统
public class LogUtil {

    private LogUtil() {}
    
    private static final String HEAD = "[Kinetic Minecart] ";    // 最后有一个空格

    public static void print(Object object) {
        System.out.println(HEAD + String.valueOf(object));
    }
}