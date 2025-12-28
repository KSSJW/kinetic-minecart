package com.kssjw.kineticminecart.client;

import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

import com.kssjw.kineticminecart.client.holder.ClientHolder;
import com.kssjw.kineticminecart.client.util.LevelUtil;
import net.neoforged.api.distmarker.Dist;

@Mod(value = "kineticminecart", dist = Dist.CLIENT)
public class KineticMinecartClient {

    public KineticMinecartClient() {
        ClientHolder.init();
        ModLoadingContext.get().registerExtensionPoint(
            IConfigScreenFactory.class,
            () -> (mod, parent) -> {
                return LevelUtil.judge(parent);
            }
        );
    }
}