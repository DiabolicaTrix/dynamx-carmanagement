package dev.dtrix.carmanagement.garage;

import dev.dtrix.carmanagement.CarManagementAddon;
import dev.dtrix.carmanagement.mod.CarManagementMod;
import dev.dtrix.carmanagement.mod.packets.PacketGarageGui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import org.slf4j.event.Level;

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

}
