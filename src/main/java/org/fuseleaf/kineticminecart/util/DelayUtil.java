package org.fuseleaf.kineticminecart.util;

public class DelayUtil {

    private DelayUtil() {}

    private static int delayTicks = -1;

    private static Runnable task = null;

    public static void register() {
        if (delayTicks > 0) {
            delayTicks--;

            if (delayTicks == 0 && task != null) {
                task.run();
                task = null;
            }
        }
    }

    public static void schedule(int ticks, Runnable runnable) {
        delayTicks = ticks;
        task = runnable;
    }
}
