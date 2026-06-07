package com.arthurlebgddu41.zipmod.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;

public class ZipUtils {
    public static CompoundTag compressItems(ItemStack[] items) {
        CompoundTag tag = new CompoundTag();
        ListTag listTag = new ListTag();

        for (int i = 0; i < items.length; i++) {
            if (!items[i].isEmpty()) {
                CompoundTag itemTag = new CompoundTag();
                itemTag.putByte("Slot", (byte) i);
                items[i].save(itemTag);
                listTag.add(itemTag);
            }
        }

        tag.put("Items", listTag);
        tag.putInt("Count", listTag.size());
        return tag;
    }

    public static ItemStack[] decompressItems(CompoundTag tag) {
        ListTag listTag = tag.getList("Items", Tag.TAG_COMPOUND);
        ItemStack[] items = new ItemStack[listTag.size()];

        for (int i = 0; i < listTag.size(); i++) {
            CompoundTag itemTag = listTag.getCompound(i);
            items[i] = ItemStack.of(itemTag);
        }

        return items;
    }
}
