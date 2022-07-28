package net.yak.electronicsmod.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yak.electronicsmod.block.custom.led;
import net.yak.electronicsmod.electronics;
import net.yak.electronicsmod.item.ModCreativeModeTab;
import net.yak.electronicsmod.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, electronics.MOD_ID);

    public static final RegistryObject<Block> SILICON_BLOCK = registerBlock("silicon_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
                    .strength(9f)), ModCreativeModeTab.ELECTRONICS_TAB);


    public static final RegistryObject<Block> SILICON_ORE = registerBlock("silicon_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(5f)
                    .requiresCorrectToolForDrops()), ModCreativeModeTab.ELECTRONICS_TAB);

    public static final RegistryObject<Block> DEEPSLATE_SILICON_ORE = registerBlock("deepslate_silicon_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(7f)
                    .requiresCorrectToolForDrops()), ModCreativeModeTab.ELECTRONICS_TAB);


    public static final RegistryObject<Block> LED = registerBlock("led",
            () -> new led(BlockBehaviour.Properties.of(Material.BUILDABLE_GLASS)
                    .strength(3f)), ModCreativeModeTab.ELECTRONICS_TAB);





    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }
    public static void register (IEventBus eventBus){
        BLOCKS.register(eventBus);
    }

}
