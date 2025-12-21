package com.kssjw.kineticminecart.util;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;

import java.util.List;

public class ExclusionListUtil {

    private ExclusionListUtil() {}
    
    // 列表归一化方法
    public static String normalizeId(String id) {
    ResourceLocation rl = ResourceLocation.tryParse(id);

        if (rl == null) return id; // 无效 ID 原样返回

        if (rl.getNamespace().isEmpty()) {
            rl = ResourceLocation.fromNamespaceAndPath("minecraft", rl.getPath());
        }
        return rl.toString(); // 保存成 "minecraft:xxx"
    }

    // 消息内容构建方法
    public static Component buildMessage(List<String> inputList) {
        
        // 如果列表为空
        if (inputList.isEmpty()) {
            return Component.translatable("toast.kinetic-minecart.ExclusionList.desc.empty");
        }

        MutableComponent sb = Component.empty();

        boolean detected = false;
        for (String id : inputList) {
            ResourceLocation rl = ResourceLocation.tryParse(id);
            EntityType<?> type = BuiltInRegistries.ENTITY_TYPE.getOptional(rl).orElse(null);

            if (type != null) {
                if (detected == false) {
                    sb.append(Component.translatable("toast.kinetic-minecart.ExclusionList.desc.detected"));
                    detected = true;    // 避免重复输出
                }
            } else {

                // 匹配失败 → 显示无法识别
                sb.append(Component.translatable("toast.kinetic-minecart.ExclusionList.desc.unkonwn")).append("'").append(id).append("'");
            } 
        }

        detected = false;   // 重置检测状态
        return sb;
    }
}