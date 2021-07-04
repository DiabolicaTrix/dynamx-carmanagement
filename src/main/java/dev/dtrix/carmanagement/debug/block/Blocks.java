package dev.dtrix.carmanagement.debug.block;

import dev.dtrix.carmanagement.CarManagementAddon;
import dev.dtrix.carmanagement.debug.block.BlockGarage;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class Blocks {

    public static final Block GARAGE = new BlockGarage();

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(GARAGE);
    }

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(new ItemBlock(Blocks.GARAGE).setRegistryName(CarManagementAddon.MODID, "garage"));
    }



}
