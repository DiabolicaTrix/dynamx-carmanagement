package dev.dtrix.carmanagement.debug;

import dev.dtrix.carmanagement.api.GarageManager;
import dev.dtrix.carmanagement.api.IGarageStorage;
import dev.dtrix.carmanagement.garage.StoredVehicle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class DebugStorage implements IGarageStorage {

    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
        if(event.getEntity() instanceof EntityPlayer) {
            GarageManager.init(new DebugStorage());
        }
    }

    @Override
    public List<StoredVehicle> list(EntityPlayer player) {
        List<StoredVehicle> vehicles = new ArrayList<>();
        vehicles.add(new StoredVehicle(-1, "DartcherPack.vehicle_trophy_truck", 0, player.getPersistentID()));
        return vehicles;
    }

    @Override
    public boolean store(EntityPlayer player, StoredVehicle vehicle) {
        return true;
    }

    @Override
    public boolean retrieve(EntityPlayer player, StoredVehicle vehicle) {
        return true;
    }

    @Override
    public int insert(EntityPlayer player, StoredVehicle vehicle) {
        return 0;
    }
}
