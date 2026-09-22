package org.fuseleaf.kineticminecart.util;

public class LogUtil {

    private LogUtil() {}

    private static final String HEAD = "[Kinetic Minecart] ";

    public static void print(Object object) {
        System.out.println(HEAD + object);
    }
}
