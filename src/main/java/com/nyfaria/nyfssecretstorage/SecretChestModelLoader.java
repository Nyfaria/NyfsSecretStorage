package com.nyfaria.nyfssecretstorage;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.resources.IResourceManager;
import net.minecraftforge.client.model.IModelLoader;

public class SecretChestModelLoader implements IModelLoader<SecretChestModelGeometry> {

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {

    }

    @Override
    public SecretChestModelGeometry read(JsonDeserializationContext deserializationContext, JsonObject modelContents) {
        return new SecretChestModelGeometry();
    }
}
