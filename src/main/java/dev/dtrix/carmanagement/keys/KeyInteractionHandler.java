package dev.dtrix.carmanagement.keys;

import dev.dtrix.carmanagement.mod.item.ItemKey;
import fr.dynamx.api.events.VehicleEntityEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class KeyInteractionHandler {

    @SubscribeEvent
    public static void onVehicleInteract(VehicleEntityEvent.VehicleInteractEntityEvent event) {
        if(!(event.player.getHeldItemMainhand().getItem() instanceof ItemKey)) {
            event.setCanceled(true);
        }
        System.out.println("Entering vehicle.");
    }

    @SubscribeEvent
    public static void onVehicleEnter(VehicleEntityEvent.MountVehicleEntityEvent event) {

    }

}
