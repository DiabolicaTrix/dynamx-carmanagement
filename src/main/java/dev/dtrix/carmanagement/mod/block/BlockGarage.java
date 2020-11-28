package dev.dtrix.carmanagement.mod.block;

import dev.dtrix.carmanagement.CarManagementAddon;
import dev.dtrix.carmanagement.mod.CarManagementMod;
import dev.dtrix.carmanagement.mod.packets.PacketGarageGui;
import fr.dynamx.common.entities.vehicles.CarEntity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.stream.Collectors;

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
            List<Entity> entities = worldIn.getEntities(CarEntity.class, entity -> entity.getDistance(playerIn) <= 16.0D);
            CarManagementMod.NETWORK.sendTo(new PacketGarageGui(entities.stream().map(Entity::getEntityId).collect(Collectors.toList())), (EntityPlayerMP) playerIn);
            return true;
        }
        return false;
    }
}
