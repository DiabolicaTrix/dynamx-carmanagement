package dev.dtrix.carmanagement.mod;

import dev.dtrix.carmanagement.CarManagementAddon;
import dev.dtrix.carmanagement.mod.item.Items;
import dev.dtrix.carmanagement.mod.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

@Mod(modid = CarManagementAddon.MODID, name = "DynamX Car Management Addon", version = "0.0.1")
public class CarManagementMod {

    @Mod.Instance
    public static CarManagementMod instance;

    @SidedProxy(serverSide = "dev.dtrix.carmanagement.mod.proxy.CommonProxy", clientSide = "dev.dtrix.carmanagement.mod.proxy.ClientProxy")
    public static CommonProxy proxy;

    public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(CarManagementAddon.MODID);

    public static final CreativeTabs TAB = new CreativeTabs("carmanagement_tab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.KEY);
        }
    };

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }

}
