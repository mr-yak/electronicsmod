package net.yak.electronicsmod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static CreativeModeTab ELECTRONICS_TAB = new CreativeModeTab("electronics_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.SILICON.get());
        }
    };
}
