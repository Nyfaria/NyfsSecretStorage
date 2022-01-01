package com.nyfaria.nyfssecretstorage;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;

import javax.annotation.Nullable;

public class SecretChestColor implements IBlockColor {

    @Override
    public int getColor(BlockState state, @Nullable IBlockDisplayReader world, @Nullable BlockPos pos, int tint) {
        if (world != null) {
            TileEntity te = world.getBlockEntity(pos);
            if (te instanceof SecretChestTile) {
                SecretChestTile fancy = (SecretChestTile) te;
                BlockState mimic = fancy.getMimic();
                if (mimic != null) {
                    return Minecraft.getInstance().getBlockColors().getColor(mimic, world, pos, tint);
                }
            }
        }
        return -1;
    }
}
