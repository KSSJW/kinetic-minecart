package com.kssjw.kineticminecart.util;

import net.minecraft.block.BlockState;
import net.minecraft.block.RailBlock;
import net.minecraft.block.enums.RailShape;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.state.property.Properties;

public class RailUtil {

    private RailUtil() {}

    public static boolean isCurved(AbstractMinecartEntity minecart) {
        BlockState state = minecart.getEntityWorld().getBlockState(minecart.getBlockPos());
        
        if (state.getBlock() instanceof RailBlock) {

            RailShape shape = state.get(Properties.RAIL_SHAPE);
            if (shape == RailShape.NORTH_EAST
            || shape == RailShape.NORTH_WEST
            || shape == RailShape.SOUTH_EAST
            || shape == RailShape.SOUTH_WEST
            ) return true;
        }

        return false;   // Fallback
    }
}