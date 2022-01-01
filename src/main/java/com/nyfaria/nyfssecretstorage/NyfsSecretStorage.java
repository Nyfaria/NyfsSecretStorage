package com.nyfaria.nyfssecretstorage;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.text.html.parser.Entity;

@Mod(NyfsSecretStorage.MOD_ID)
public class NyfsSecretStorage
{
    public static final String MOD_ID = "nyfssecretstorage";

    private static final Logger LOGGER = LogManager.getLogger();

    public NyfsSecretStorage() {

        MinecraftForge.EVENT_BUS.register(this);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BlockInit.BLOCKS.register(bus);
        ItemInit.ITEMS.register(bus);
        EntityInit.TILES.register(bus);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(EventLoader::init);
        PlayerEntity player;

    }

}
