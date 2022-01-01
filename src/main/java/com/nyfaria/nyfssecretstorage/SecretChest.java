package com.nyfaria.nyfssecretstorage;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.piglin.PiglinTasks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.function.Supplier;

public class SecretChest extends Block {

    private static final VoxelShape SHAPE = VoxelShapes.box(.2, .2, .2, .8, .8, .8);

    public SecretChest() {
        super(Properties.of(Material.METAL)
                        .sound(SoundType.METAL)
                        .strength(2.0f));
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        TileEntity te = world.getBlockEntity(pos);
        if (te instanceof SecretChestTile) {
            BlockState mimic = ((SecretChestTile) te).getMimic();
            if (mimic != null) {
                return mimic.getLightValue(world, pos);
            }
        }
        return super.getLightValue(state, world, pos);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        TileEntity te = reader.getBlockEntity(pos);
        if (te instanceof SecretChestTile) {
            BlockState mimic = ((SecretChestTile) te).getMimic();
            if (mimic != null) {
                return mimic.getShape(reader, pos, context);
            }
        }
        return SHAPE;
    }


    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new SecretChestTile();
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult trace) {
        ItemStack item = player.getItemInHand(hand);
        if (!item.isEmpty() && item.getItem() instanceof BlockItem) {
            if (!world.isClientSide) {
                TileEntity te = world.getBlockEntity(pos);
                if (te instanceof SecretChestTile) {
                    BlockState mimicState = ((BlockItem) item.getItem()).getBlock().defaultBlockState();
                    ((SecretChestTile) te).setMimic(mimicState);
                }
            }
            return ActionResultType.SUCCESS;
        }
        return super.use(state, world, pos, player, hand, trace);
    }

}

