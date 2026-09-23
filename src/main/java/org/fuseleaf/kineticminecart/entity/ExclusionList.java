package org.fuseleaf.kineticminecart.entity;

import java.util.List;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;

public class ExclusionList {

    private ExclusionList() {}

    public static String normalizeId(String id) {
        if (id == null) {
            return id;
        }

        Identifier rl = Identifier.tryParse(id);

        if (rl == null) {
            return id;
        }

        if (rl.getNamespace().isEmpty()) {
            rl = Identifier.fromNamespaceAndPath("minecraft", rl.getPath());
        }

        return rl.toString();   // minecraft:xxx
    }

    public static Component buildMessage(List<String> inputList) {

        if (inputList.isEmpty()) {
            return Component.translatable("toast.kinetic-minecart.ExclusionList.desc.empty");
        }

        MutableComponent sb = Component.empty();

        boolean detected = false;
        for (String id : inputList) {
            if (id == null) {
                return Component.empty();
            }

            Identifier rl = Identifier.tryParse(id);

            if (rl == null) {
                return Component.empty();
            }

            EntityType<?> type = BuiltInRegistries.ENTITY_TYPE.getValue(ResourceKey.create(Registries.ENTITY_TYPE, rl));

            if (type != null) {
                if (!detected) {
                    sb.append(Component.translatable("toast.kinetic-minecart.ExclusionList.desc.detected"));
                    detected = true;
                }
            } else {
                sb.append(Component.translatable("toast.kinetic-minecart.ExclusionList.desc.unkonwn")).append("'").append(id).append("'");
            }
        }

        return sb;
    }
}
