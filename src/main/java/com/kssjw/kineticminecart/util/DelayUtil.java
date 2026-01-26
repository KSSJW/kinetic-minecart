package com.kssjw.kineticminecart.util;

public class DelayUtil {

    private DelayUtil() {}

    private static int delayTicks = -1;
    private static Runnable task = null;

    // 在 ServerTickEvents.END_CLIENT_TICK 注册调用
    public static void tick() {
        if (delayTicks > 0) {
            delayTicks--;
            if (delayTicks == 0 && task != null) {
                task.run();
                task = null; // 清理，避免重复执行
            }
        }
    }

    // 安排延时任务
    public static void schedule(int ticks, Runnable runnable) {
        delayTicks = ticks;
        task = runnable;
    }
}