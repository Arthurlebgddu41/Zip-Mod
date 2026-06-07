package com.arthurlebgddu41.zipmod.registration;

import com.arthurlebgddu41.zipmod.ZipMod;
import com.arthurlebgddu41.zipmod.block.CompressorBlock;
import com.arthurlebgddu41.zipmod.block.DecompressorBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ZipMod.MOD_ID);

    public static final RegistryObject<Block> COMPRESSOR_BLOCK = BLOCKS.register("compressor",
            () -> new CompressorBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(4.0f, 10.0f)
                    .requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> DECOMPRESSOR_BLOCK = BLOCKS.register("decompressor",
            () -> new DecompressorBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(4.0f, 10.0f)
                    .requiresCorrectToolForDrops()));
}
