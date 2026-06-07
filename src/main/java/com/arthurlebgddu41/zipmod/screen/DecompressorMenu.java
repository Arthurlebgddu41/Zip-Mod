package com.arthurlebgddu41.zipmod.screen;

import com.arthurlebgddu41.zipmod.block.entity.DecompressorBlockEntity;
import com.arthurlebgddu41.zipmod.registration.ModMenus;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class DecompressorMenu extends AbstractContainerMenu {
    private final DecompressorBlockEntity blockEntity;
    public static final int SLOT_INPUT = 0;
    public static final int SLOTS_OUTPUT_START = 1;
    public static final int SLOTS_OUTPUT_END = 10;

    public DecompressorMenu(int id, Inventory playerInventory, DecompressorBlockEntity blockEntity) {
        super(ModMenus.DECOMPRESSOR.get(), id);
        this.blockEntity = blockEntity;

        // Input slot
        this.addSlot(new Slot(blockEntity, SLOT_INPUT, 80, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return !stack.isEmpty() && stack.hasTag();
            }
        });

        // Output slots
        int x = 35;
        int y = 60;
        for (int i = 1; i < 10; i++) {
            if (i % 3 == 1) {
                x = 35;
                y += 18;
            }
            this.addSlot(new Slot(blockEntity, i, x, y) {
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return false;
                }
            });
            x += 18;
        }

        // Player inventory
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 139 + i * 18));
            }
        }

        // Player hotbar
        for (int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 197));
        }
    }

    public DecompressorBlockEntity getBlockEntity() {
        return blockEntity;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index < 10) {
                if (!this.moveItemStackTo(itemstack1, 10, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, 10, false)) {
                return ItemStack.EMPTY;
            }
            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return itemstack;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}
