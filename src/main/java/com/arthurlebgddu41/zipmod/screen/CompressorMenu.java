package com.arthurlebgddu41.zipmod.screen;

import com.arthurlebgddu41.zipmod.block.entity.CompressorBlockEntity;
import com.arthurlebgddu41.zipmod.registration.ModMenus;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class CompressorMenu extends AbstractContainerMenu {
    private final CompressorBlockEntity blockEntity;
    public static final int SLOT_INPUT_1 = 0;
    public static final int SLOT_INPUT_2 = 1;
    public static final int SLOT_INPUT_3 = 2;
    public static final int SLOT_OUTPUT = 3;

    public CompressorMenu(int id, Inventory playerInventory, CompressorBlockEntity blockEntity) {
        super(ModMenus.COMPRESSOR.get(), id);
        this.blockEntity = blockEntity;

        // Input slots
        this.addSlot(new Slot(blockEntity, SLOT_INPUT_1, 53, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return !stack.isEmpty();
            }
        });
        this.addSlot(new Slot(blockEntity, SLOT_INPUT_2, 71, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return !stack.isEmpty();
            }
        });
        this.addSlot(new Slot(blockEntity, SLOT_INPUT_3, 89, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return !stack.isEmpty();
            }
        });

        // Output slot
        this.addSlot(new Slot(blockEntity, SLOT_OUTPUT, 143, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });

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

    public CompressorBlockEntity getBlockEntity() {
        return blockEntity;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index < 4) {
                if (!this.moveItemStackTo(itemstack1, 4, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, 4, false)) {
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
