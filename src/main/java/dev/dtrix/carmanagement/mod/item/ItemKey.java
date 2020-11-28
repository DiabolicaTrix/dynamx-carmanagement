package dev.dtrix.carmanagement.mod.item;

import dev.dtrix.carmanagement.CarManagementAddon;
import dev.dtrix.carmanagement.mod.CarManagementMod;
import net.minecraft.item.Item;

public class ItemKey extends Item {

    public ItemKey() {
        setCreativeTab(CarManagementMod.TAB);
        setMaxStackSize(1);
        setNoRepair();
        setRegistryName(CarManagementAddon.MODID, "key");
        setTranslationKey("key");
    }

}
