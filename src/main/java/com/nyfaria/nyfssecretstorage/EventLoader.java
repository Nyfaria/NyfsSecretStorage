package com.nyfaria.nyfssecretstorage;


import mcp.mobius.waila.api.ICommonAccessor;
import mcp.mobius.waila.api.event.WailaRenderEvent;
import mcp.mobius.waila.api.event.WailaTooltipEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = NyfsSecretStorage.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventLoader {

    public static void init(final FMLClientSetupEvent event) {

        event.enqueueWork(() -> {
            RenderTypeLookup.setRenderLayer(BlockInit.SECRET_CHEST.get(), (RenderType) -> true);
            Minecraft.getInstance().getBlockColors().register(new SecretChestColor(), BlockInit.SECRET_CHEST.get());
        });
    }


    @SubscribeEvent
    public static void onModelRegistryEvent(ModelRegistryEvent event) {
        ModelLoaderRegistry.registerLoader(new ResourceLocation(NyfsSecretStorage.MOD_ID, "secretchestloader"), new SecretChestModelLoader());
    }
    @SubscribeEvent
    public void jadeInit(WailaRenderEvent e){

    }

}
