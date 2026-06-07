package com.arthurlebgddu41.zipmod.registration;

import com.arthurlebgddu41.zipmod.ZipMod;
import com.arthurlebgddu41.zipmod.screen.CompressorMenu;
import com.arthurlebgddu41.zipmod.screen.DecompressorMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, ZipMod.MOD_ID);

    public static final RegistryObject<MenuType<CompressorMenu>> COMPRESSOR = MENUS.register("compressor",
            () -> new MenuType<>(CompressorMenu::new));

    public static final RegistryObject<MenuType<DecompressorMenu>> DECOMPRESSOR = MENUS.register("decompressor",
            () -> new MenuType<>(DecompressorMenu::new));
}
