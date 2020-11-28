package dev.dtrix.carmanagement.mod.item;

import dev.dtrix.carmanagement.CarManagementAddon;
import dev.dtrix.carmanagement.mod.block.Blocks;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class Items {

    //ITEMS
    public static final Item KEY = new ItemKey();

    //BLOCKS
    public static final ItemBlock BLOCK_GARAGE = (ItemBlock) new ItemBlock(Blocks.GARAGE).setRegistryName(CarManagementAddon.MODID, "garage");

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        //ITEMS
        event.getRegistry().registerAll(KEY);

        //BLOCKS
        event.getRegistry().registerAll(BLOCK_GARAGE);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        //ITEMS
        registerModel(KEY);

        //BLOCKS
        registerModel(BLOCK_GARAGE);
    }

    @SideOnly(Side.CLIENT)
    private static void registerModel(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName().toString(), "inventory"));
    }

    @SideOnly(Side.CLIENT)
    private static void registerModel(Item item, int metadata, String name) {
        ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(CarManagementAddon.MODID + ":" + name, "inventory"));
    }

}
