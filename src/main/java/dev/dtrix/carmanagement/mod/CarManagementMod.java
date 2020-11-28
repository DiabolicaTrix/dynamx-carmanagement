package dev.dtrix.carmanagement.mod;

import dev.dtrix.carmanagement.CarManagementAddon;
import dev.dtrix.carmanagement.mod.item.Items;
import dev.dtrix.carmanagement.mod.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;

@Mod(modid = CarManagementAddon.MODID, name = "DynamX Car Management Addon", version = "0.0.1")
public class CarManagementMod {

    @Mod.Instance
    public static CarManagementMod instance;

    @SidedProxy(serverSide = "dev.dtrix.carmanagement.mod.proxy.CommonProxy", clientSide = "dev.dtrix.carmanagement.mod.proxy.ClientProxy")
    public static CommonProxy proxy;

    public static final CreativeTabs TAB = new CreativeTabs("carmanagement_tab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.KEY);
        }
    };

}
