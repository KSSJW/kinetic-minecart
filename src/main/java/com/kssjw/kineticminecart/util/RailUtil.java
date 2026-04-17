package com.kssjw.kineticminecart.util;

import net.minecraft.world.entity.vehicle.minecart.AbstractMinecart;
import net.minecraft.world.level.block.RailBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.RailShape;

public class RailUtil {

    private RailUtil() {}

    public static boolean isCurved(AbstractMinecart minecart) {
        BlockState state = minecart.level().getBlockState(minecart.blockPosition());
        
        if (state.getBlock() instanceof RailBlock) {

            RailShape shape = state.getValue(BlockStateProperties.RAIL_SHAPE);
            return shape == RailShape.NORTH_EAST
                    || shape == RailShape.NORTH_WEST
                    || shape == RailShape.SOUTH_EAST
                    || shape == RailShape.SOUTH_WEST;
        }

        return false;   // Fallback
    }
}