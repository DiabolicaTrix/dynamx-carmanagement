package dev.dtrix.carmanagement.mod.block;

import dev.dtrix.carmanagement.mod.block.BlockGarage;
import net.minecraft.block.Block;
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

}
