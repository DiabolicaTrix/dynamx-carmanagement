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

}
