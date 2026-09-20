package org.fuseleaf.kineticminecart.extension.config;

import org.jspecify.annotations.NonNull;

import net.minecraft.network.chat.Component;

public class ConfigEnum {

    public static enum ApplicaionMode {
        Radius("option.kinetic-minecart.radius"),
        Collide("option.kinetic-minecart.collide");

        private final @NonNull String key;

        ApplicaionMode(@NonNull String key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return Component.translatable(key).getString();
        }
    }

    public static enum DamageMode {
        TieredDamage("option.kinetic-minecart.tiereddamage"),
        DirectlyKill("option.kinetic-minecart.directlykill");

        private final @NonNull String key;

        DamageMode(@NonNull String key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return Component.translatable(key).getString();
        }
    }
}
