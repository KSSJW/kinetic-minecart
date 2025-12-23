package com.kssjw.kineticminecart.util;

import java.util.List;

import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ExclusionListUtil {

    private ExclusionListUtil() {}
    
    // 列表归一化方法
    public static String normalizeId(String id) {
    Identifier rl = Identifier.tryParse(id);

        if (rl == null) return id; // 无效 ID 原样返回

        if (rl.getNamespace().isEmpty()) {
            rl = Identifier.of("minecraft", rl.getPath());
        }
        return rl.toString(); // 保存成 "minecraft:xxx"
    }

    // 消息内容构建方法
    public static Text buildMessage(List<String> inputList) {
        
        // 如果列表为空
        if (inputList.isEmpty()) {
            return Text.translatable("toast.kinetic-minecart.ExclusionList.desc.empty");
        }

        MutableText sb = Text.empty();

        boolean detected = false;
        for (String id : inputList) {
            Identifier rl = Identifier.tryParse(id);
            EntityType<?> type = Registries.ENTITY_TYPE.getOptional(RegistryKey.of(RegistryKeys.ENTITY_TYPE, rl)).map(RegistryEntry::value).orElse(null);

            if (type != null) {
                if (detected == false) {
                    sb.append(Text.translatable("toast.kinetic-minecart.ExclusionList.desc.detected"));
                    detected = true;    // 避免重复输出
                }
            } else {

                // 匹配失败 → 显示无法识别
                sb.append(Text.translatable("toast.kinetic-minecart.ExclusionList.desc.unkonwn")).append("'").append(id).append("'");
            } 
        }

        detected = false;   // 重置检测状态
        return sb;
    }
}