package com.kssjw.kineticminecart;

import com.kssjw.kineticminecart.manager.HolderManager;
import com.kssjw.kineticminecart.util.LogUtil;

import net.neoforged.fml.common.Mod;

@Mod("kineticminecart")
public class KineticMinecart {

    public KineticMinecart() {
        HolderManager.init();
        LogUtil.print("Initialized.");
    }
}