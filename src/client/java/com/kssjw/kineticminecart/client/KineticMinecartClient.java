package com.kssjw.kineticminecart.client;

import com.kssjw.kineticminecart.client.holder.ClientHolder;
import com.kssjw.kineticminecart.client.util.LevelUtil;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler.ConfigScreenFactory;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@Mod.EventBusSubscriber(modid = "kineticminecart", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)   
public class KineticMinecartClient {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        ClientHolder.init();
        ModLoadingContext.get().registerExtensionPoint(
            ConfigScreenFactory.class,
            () -> new ConfigScreenFactory(
                (mc, parent) -> {
                    return LevelUtil.judge(parent);
                }
            )
        );
    }
}