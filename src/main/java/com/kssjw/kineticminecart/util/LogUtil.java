package com.kssjw.kineticminecart.util;

// 笨猫猫额外写的日志系统
public class LogUtil {

    private LogUtil() {}
    
    private static final String HEAD = "[Kinetic Minecart] ";    // 最后有一个空格
    private static float tempLoggerSpeed;   // 缓存数据

    public static void print(Object object) {
        System.out.println(HEAD + String.valueOf(object));
    }

    public static void printSpeed(float loggerSpeed) {

        // 近似转换
        String stringSimpleLoggerSpeed = String.format("%.2f", loggerSpeed);
        float floatSimpleLoggerSpeed = Float.parseFloat(stringSimpleLoggerSpeed);

        // 过滤为速度为 0 的情况
        if (floatSimpleLoggerSpeed == 0) {
            tempLoggerSpeed = 0;
            return;
        }

        if (floatSimpleLoggerSpeed == tempLoggerSpeed) {
            return;
        } else {
            System.out.println(HEAD + "loggerSpeed: " + floatSimpleLoggerSpeed);
            tempLoggerSpeed = floatSimpleLoggerSpeed;  // 缓存速度
        }
    }

    public static void printDamage(float loggerDamage) {

        // 近似转换
        String stringSimpleLoggerDamage = String.format("%.2f", loggerDamage);
        float floatSimpleLoggerDamage = Float.parseFloat(stringSimpleLoggerDamage);

        System.out.println(HEAD + "loggerDamage: " + floatSimpleLoggerDamage);
    }
    
    public static void printKnockDistance(double loggerKnockDistance) {

        // 近似转换
        String stringSimpleLoggerKnockDistance = String.format("%.2f", loggerKnockDistance);
        double doubleSimpleLoggerKnockDistance = Double.parseDouble(stringSimpleLoggerKnockDistance);

        System.out.println(HEAD + "loggerKnockDistance: " + doubleSimpleLoggerKnockDistance);
    }
}