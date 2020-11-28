package dev.dtrix.carmanagement.mod.block;

import dev.dtrix.carmanagement.CarManagementAddon;
import dev.dtrix.carmanagement.mod.CarManagementMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockGarage extends Block {

    public BlockGarage() {
        super(Material.IRON);
        setCreativeTab(CarManagementMod.TAB);
        setRegistryName(CarManagementAddon.MODID, "garage");
        setTranslationKey("garage");
    }



}
