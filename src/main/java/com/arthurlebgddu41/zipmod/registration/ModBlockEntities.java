package com.arthurlebgddu41.zipmod.registration;

import com.arthurlebgddu41.zipmod.ZipMod;
import com.arthurlebgddu41.zipmod.block.entity.CompressorBlockEntity;
import com.arthurlebgddu41.zipmod.block.entity.DecompressorBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ZipMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<CompressorBlockEntity>> COMPRESSOR = BLOCK_ENTITIES.register("compressor",
            () -> BlockEntityType.Builder.of(CompressorBlockEntity::new, ModBlocks.COMPRESSOR_BLOCK.get()).build(null));

    public static final RegistryObject<BlockEntityType<DecompressorBlockEntity>> DECOMPRESSOR = BLOCK_ENTITIES.register("decompressor",
            () -> BlockEntityType.Builder.of(DecompressorBlockEntity::new, ModBlocks.DECOMPRESSOR_BLOCK.get()).build(null));
}
