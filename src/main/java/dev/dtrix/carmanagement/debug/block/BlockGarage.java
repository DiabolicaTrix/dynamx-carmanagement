package dev.dtrix.carmanagement.debug.block;

import dev.dtrix.carmanagement.CarManagementAddon;
import dev.dtrix.carmanagement.garage.GarageHelper;
import dev.dtrix.carmanagement.mod.CarManagementMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockGarage extends Block {

    public BlockGarage() {
        super(Material.IRON);
        setCreativeTab(CarManagementMod.TAB);
        setRegistryName(CarManagementAddon.MODID, "garage");
        setTranslationKey("garage");
    }

    @Override
    /**
     * Checks in a configurable radius if there is a DynamX vehicle.
     * Sends a list of every owned vehicle in the given radius, or an empty list of none are found.
     * (Ownership of a vehicle can be determined in the config. It can be one of the 2 following cases:
     *  * Actual owner of the vehicle (buyer, renter, etc.)
     *  * Anyone with the vehicle keys
     *  )
     *  Opens the garage containing the vehicles currently stored in the garage and the vehicles that can be stored based on the previous criteria.
     */
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!worldIn.isRemote && hand == EnumHand.MAIN_HAND) {
            if(playerIn.isSneaking()) {
                GarageHelper.storeNearestVehicle(playerIn, pos);
                return true;
            }
            GarageHelper.openGarageGui(playerIn);
            return true;
        }
        return false;
    }
}
