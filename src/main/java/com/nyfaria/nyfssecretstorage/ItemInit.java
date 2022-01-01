package com.nyfaria.nyfssecretstorage;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NyfsSecretStorage.MOD_ID);
    public static final RegistryObject<BlockItem> SECRET_CHEST = ITEMS.register("secret_chest", () -> new BlockItem(BlockInit.SECRET_CHEST.get(), new Item.Properties().tab(ItemGroup.TAB_DECORATIONS)));

}
