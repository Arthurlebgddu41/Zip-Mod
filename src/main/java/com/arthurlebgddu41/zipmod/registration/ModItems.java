package com.arthurlebgddu41.zipmod.registration;

import com.arthurlebgddu41.zipmod.ZipMod;
import com.arthurlebgddu41.zipmod.item.CompressedZipItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ZipMod.MOD_ID);

    public static final RegistryObject<Item> COMPRESSOR = ITEMS.register("compressor",
            () -> new BlockItem(ModBlocks.COMPRESSOR_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<Item> DECOMPRESSOR = ITEMS.register("decompressor",
            () -> new BlockItem(ModBlocks.DECOMPRESSOR_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<Item> COMPRESSED_ZIP = ITEMS.register("compressed_zip",
            () -> new CompressedZipItem(new Item.Properties().stacksTo(1)));
}
