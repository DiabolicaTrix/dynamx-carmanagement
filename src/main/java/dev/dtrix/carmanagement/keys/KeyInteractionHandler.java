package dev.dtrix.carmanagement.keys;

import dev.dtrix.carmanagement.mod.packets.PacketRetrieveVehicle;
import fr.dynamx.api.events.PhysicsEntityEvent;
import fr.dynamx.api.events.VehicleEntityEvent;
import fr.dynamx.common.entities.BaseVehicleEntity;
import fr.dynamx.common.items.tools.ItemWrench;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.server.permission.PermissionAPI;

@Mod.EventBusSubscriber
public class KeyInteractionHandler {

    @SubscribeEvent
    public static void initVehicleModules(PhysicsEntityEvent.CreateEntityModulesEvent<BaseVehicleEntity> event)
    {
        BaseVehicleEntity<?> entity = event.getEntity();
        event.addModule(new CarManagementModule(entity));
        if(PacketRetrieveVehicle.vehicleMap.containsKey(entity.getUniqueID())) {
            entity.getModuleByType(CarManagementModule.class).setStoredVehicle(PacketRetrieveVehicle.vehicleMap.remove(entity.getUniqueID()));
        }
    }

    @SubscribeEvent
    public static void onVehicleInteract(VehicleEntityEvent.VehicleInteractEntityEvent event) {
        CarManagementModule module = event.getEntity().getModuleByType(CarManagementModule.class);
        if(module.isLocked())
            event.setCanceled(true);
    }

    /**
     * Disables manual spawning of vehicles. This forces the use of the garage system.
     */
    @SubscribeEvent
    public static void onPhysicsEntitySpawned(PhysicsEntityEvent.PhysicsEntitySpawnedEvent event) {
        if(event.getEntity() instanceof BaseVehicleEntity && event.getEntity().getModuleByType(CarManagementModule.class) == null) {
            event.setCanceled(true);
        }
    }

    /**
     * Disables the manual removal of vehicles by hitting them. This forces the use of the garage system.
     * TODO set inactive on destroy.
     */
    @SubscribeEvent
    public static void onAttacked(PhysicsEntityEvent.AttackedEvent event) {
        if(!event.player.world.isRemote && event.player.isSneaking() && PermissionAPI.hasPermission(event.player, "carmanagement.destroy")) {
            return;
        }
        event.setCanceled(true);
    }


}
