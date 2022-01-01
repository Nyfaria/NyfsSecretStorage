package com.nyfaria.nyfssecretstorage;

import mcp.mobius.waila.Waila;
import mcp.mobius.waila.WailaClient;
import mcp.mobius.waila.api.*;
import mcp.mobius.waila.api.event.WailaRenderEvent;
import mcp.mobius.waila.api.event.WailaTooltipEvent;
import mcp.mobius.waila.api.impl.DataAccessor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.forgespi.language.IModInfo;
import snownee.jade.addon.vanilla.HarvestToolProvider;
import net.minecraft.client.resources.I18n;

import java.util.List;
import java.util.stream.Collectors;

@WailaPlugin(NyfsSecretStorage.MOD_ID)
public class Compat implements IWailaPlugin, IComponentProvider, IBlockDecorator {

    public static final Compat INSTANCE = new Compat();

    @Override
    public void register(IRegistrar iRegistrar) {
        iRegistrar.registerStackProvider(this, SecretChest.class);
        iRegistrar.registerComponentProvider(this,TooltipPosition.HEAD, SecretChest.class);
        iRegistrar.registerComponentProvider(this,TooltipPosition.TAIL, SecretChest.class);

    }

    @Override
    public ItemStack getStack(IDataAccessor accessor, IPluginConfig config) {
        SecretChestTile te = (SecretChestTile)Minecraft.getInstance().level.getBlockEntity(accessor.getPosition());
        if(te.getMimic() != null) {
            return new ItemStack(te.getMimic().getBlock(), 1);
        }
        return accessor.getStack();
    }

    @Override
    public void appendHead(List<ITextComponent> tooltip, IDataAccessor accessor, IPluginConfig config) {
        SecretChestTile te = (SecretChestTile) Minecraft.getInstance().level.getBlockEntity(accessor.getPosition());
        if (te.getMimic() != null) {
            tooltip.clear();
            Block b = te.getMimic().getBlock();

            tooltip.add(b.getName());

            ((ITaggableList<ResourceLocation, ITextComponent>) tooltip).setTag(OBJECT_NAME_TAG, new StringTextComponent(String.format(Waila.CONFIG.get().getFormatting().getBlockName(), I18n.get(accessor.getBlockState().getHarvestTool().getName()))));
        }
    }

    @Override
    public void appendTail(List<ITextComponent> tooltip, IDataAccessor accessor, IPluginConfig config) {

        SecretChestTile te = (SecretChestTile)Minecraft.getInstance().level.getBlockEntity(accessor.getPosition());
        if(te.getMimic() != null) {
            tooltip.clear();
            Block b = te.getMimic().getBlock();

            IModInfo iModInfo = ModList.get().getMods().stream().filter(meep -> meep.getModId().equals(b.getRegistryName().getNamespace())).collect(Collectors.toList()).get(0);
            tooltip.add(new TranslationTextComponent(iModInfo.getDisplayName()).withStyle(TextFormatting.ITALIC).withStyle(TextFormatting.BLUE));
        }
    }

    @Override
    public void decorateBlock(ItemStack itemStack, IDataAccessor iDataAccessor, IPluginConfig iPluginConfig) {

    }
}
