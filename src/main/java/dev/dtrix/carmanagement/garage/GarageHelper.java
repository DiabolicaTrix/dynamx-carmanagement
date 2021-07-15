package dev.dtrix.carmanagement.garage;

import dev.dtrix.carmanagement.api.GarageManager;
import dev.dtrix.carmanagement.api.StoredVehicle;
import dev.dtrix.carmanagement.keys.CarManagementModule;
import dev.dtrix.carmanagement.mod.CarManagementMod;
import dev.dtrix.carmanagement.mod.item.ItemKey;
import dev.dtrix.carmanagement.mod.packets.PacketGarageGui;
import fr.dynamx.addons.basics.common.KeyUtils;
import fr.dynamx.common.entities.BaseVehicleEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.ticket.AABBTicket;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GarageHelper {

    private static final int RADIUS = 16;

    private GarageHelper() {}

    public static void openGarageGui(EntityPlayer player) {
        CarManagementMod.NETWORK.sendTo(new PacketGarageGui(GarageManager.getStorage().list(player)), (EntityPlayerMP) player);
    }

    public static void storeNearestVehicle(EntityPlayer player, BlockPos position) {
        Optional<Entity> vehicleEntity = player.world.getLoadedEntityList().stream().filter(entity ->
             entity instanceof BaseVehicleEntity<?>
                && ((BaseVehicleEntity<?>) entity).getModuleByType(CarManagementModule.class).getStoredVehicle().getOwner().compareTo(player.getPersistentID()) == 0
                && entity.getDistanceSq(position) <= RADIUS
        ).min(Comparator.comparingDouble(entity -> entity.getDistanceSq(position)));

        if(vehicleEntity.isPresent()) {
            StoredVehicle vehicle = ((BaseVehicleEntity<?>) vehicleEntity.get()).getModuleByType(CarManagementModule.class).getStoredVehicle();
            if(GarageManager.getStorage().store(player, vehicle)) {
                player.world.removeEntity(vehicleEntity.get());
                for(int i = 0; i < player.inventory.getSizeInventory(); i++) {
                    ItemStack stack = player.inventory.getStackInSlot(i);
                    if(stack.getItem() instanceof ItemKey
                            && KeyUtils.hasLinkedVehicle(stack)
                            && KeyUtils.getLinkedVehicle(stack).compareTo(vehicleEntity.get().getUniqueID()) == 0) {
                        player.inventory.removeStackFromSlot(i);
                    }
                }
                player.sendMessage(new TextComponentTranslation("carmanagement.store.success", vehicle.getVehicleInfo().getTranslatedName(null, vehicle.getMetadata())));
                return;
            }
            player.sendMessage(new TextComponentTranslation("carmanagement.store.error"));
            return;
        }
        player.sendMessage(new TextComponentTranslation("carmanagement.store.novehicles"));
    }

}
