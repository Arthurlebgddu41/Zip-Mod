package com.arthurlebgddu41.zipmod.block.entity;

import com.arthurlebgddu41.zipmod.registration.ModBlockEntities;
import com.arthurlebgddu41.zipmod.registration.ModItems;
import com.arthurlebgddu41.zipmod.screen.CompressorMenu;
import com.arthurlebgddu41.zipmod.util.ZipUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CompressorBlockEntity extends BaseContainerBlockEntity {
    private ItemStack[] items = new ItemStack[4];

    public CompressorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.COMPRESSOR.get(), pos, state);
        initializeItems();
    }

    public void initializeItems() {
        for (int i = 0; i < items.length; i++) {
            items[i] = ItemStack.EMPTY;
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
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
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        ListTag listTag = tag.getList("Items", Tag.TAG_COMPOUND);
        for (int i = 0; i < listTag.size(); i++) {
            CompoundTag itemTag = listTag.getCompound(i);
            byte slot = itemTag.getByte("Slot");
            if (slot >= 0 && slot < items.length) {
                items[slot] = ItemStack.of(itemTag);
            }
        }
    }

    public void compress() {
        ItemStack[] itemsToCompress = new ItemStack[3];
        for (int i = 0; i < 3; i++) {
            itemsToCompress[i] = items[i].copy();
        }

        ItemStack compressedZip = new ItemStack(ModItems.COMPRESSED_ZIP.get());
        CompoundTag tag = new CompoundTag();
        tag.put("CompressedItems", ZipUtils.compressItems(itemsToCompress));
        compressedZip.setTag(tag);

        items[3] = compressedZip;
        items[0] = ItemStack.EMPTY;
        items[1] = ItemStack.EMPTY;
        items[2] = ItemStack.EMPTY;
        this.setChanged();
    }

    public ItemStack getItem(int index) {
        return index >= 0 && index < items.length ? items[index] : ItemStack.EMPTY;
    }

    public void setItem(int index, ItemStack stack) {
        if (index >= 0 && index < items.length) {
            items[index] = stack;
            this.setChanged();
        }
    }

    @Override
    public int getContainerSize() {
        return 4;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack item : items) {
            if (!item.isEmpty()) return false;
        }
        return true;
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        ItemStack stack = getItem(index);
        setItem(index, ItemStack.EMPTY);
        return stack;
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        ItemStack stack = getItem(index);
        if (!stack.isEmpty()) {
            ItemStack removed = stack.split(count);
            if (stack.isEmpty()) {
                setItem(index, ItemStack.EMPTY);
            }
            return removed;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public void setChanged() {
        super.setChanged();
        if (level != null) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    @Override
    protected Component getDefaultName() {
        return Component.literal("Compressor");
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new CompressorMenu(id, inventory, this);
    }
}
