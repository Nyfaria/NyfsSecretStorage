package com.nyfaria.nyfssecretstorage;

import net.minecraft.entity.EntityType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityInit {


    public static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, NyfsSecretStorage.MOD_ID);
    public static final RegistryObject<TileEntityType<SecretChestTile>> SECRETCHEST_TILE = TILES.register("secret_chest", () -> TileEntityType.Builder.of(SecretChestTile::new, BlockInit.SECRET_CHEST.get()).build(null));

}
