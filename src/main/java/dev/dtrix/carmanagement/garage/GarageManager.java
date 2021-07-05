package dev.dtrix.carmanagement.garage;

import dev.dtrix.carmanagement.CarManagementAddon;
import dev.dtrix.carmanagement.keys.CarManagementModule;
import dev.dtrix.carmanagement.mod.CarManagementMod;
import dev.dtrix.carmanagement.mod.item.ItemKey;
import dev.dtrix.carmanagement.mod.packets.PacketGarageGui;
import fr.dynamx.addons.basics.common.KeyUtils;
import fr.dynamx.common.entities.BaseVehicleEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import org.slf4j.event.Level;

import java.util.*;
import java.util.stream.Collectors;

public class GarageManager {

    private static IGarageStorage storage;

    public static void init(IGarageStorage storage) {
        CarManagementAddon.getLogger().debug("A storage manager has been registered.");
        GarageManager.storage = storage;
    }

    public static IGarageStorage getStorage() {
        if(storage == null) {
            CarManagementAddon.getLogger().error("No storage manager has been registered, defaulting to DummyStorage.class");
            storage = new DummyStorage();
        }
        return storage;
    }

    public static void openGarageGui(EntityPlayer player) {
        CarManagementMod.NETWORK.sendTo(new PacketGarageGui(getStorage().list(player)), (EntityPlayerMP) player);
    }

    public static void storeNearestVehicle(EntityPlayer player, BlockPos position) {
        List<BaseVehicleEntity> vehicles = player.world.getEntities(BaseVehicleEntity.class, vehicle -> {
            CarManagementModule module = (CarManagementModule) vehicle.getModuleByType(CarManagementModule.class);
            return vehicle.getDistanceSq(position) <= 16.0D && module.getStoredVehicle().getOwner().compareTo(player.getPersistentID()) == 0;
        }).stream().sorted(Comparator.comparingDouble(vehicle -> vehicle.getDistanceSq(position))).collect(Collectors.toList());
        if(vehicles.size() > 0) {
            BaseVehicleEntity<?> entity = vehicles.get(0);
            CarManagementModule module = (CarManagementModule) entity.getModuleByType(CarManagementModule.class);
            if(getStorage().store(player, module.getStoredVehicle())) {
                player.world.removeEntity(entity);
                for(int i = 0; i < player.inventory.getSizeInventory(); i++) {
                    ItemStack stack = player.inventory.getStackInSlot(i);
                    if(stack.getItem() instanceof ItemKey
                            && KeyUtils.hasLinkedVehicle(stack)
                            && KeyUtils.getLinkedVehicle(stack).compareTo(vehicles.get(0).getUniqueID()) == 0) {
                        player.inventory.removeStackFromSlot(i);
                    }
                }
                player.sendMessage(new TextComponentTranslation("carmanagement.store.success", vehicles.get(0).getDisplayName()));
                return;
            }
        }
        player.sendMessage(new TextComponentTranslation("carmanagement.store.error"));
    }

}
