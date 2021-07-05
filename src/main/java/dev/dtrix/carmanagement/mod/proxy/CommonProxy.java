package dev.dtrix.carmanagement.mod.proxy;

import dev.dtrix.carmanagement.CarManagementAddon;
import dev.dtrix.carmanagement.mod.CarManagementMod;
import dev.dtrix.carmanagement.mod.packets.PacketGarageGui;
import dev.dtrix.carmanagement.mod.packets.PacketLockVehicle;
import dev.dtrix.carmanagement.mod.packets.PacketRetrieveVehicle;
import fr.dynamx.api.network.sync.SynchronizedVariablesRegistry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy {

    private static int ID = 0;

    public void preInit() {}

    public void init() {
        CarManagementMod.NETWORK.registerMessage(PacketGarageGui.Handler.class, PacketGarageGui.class, ID++, Side.CLIENT);
        CarManagementMod.NETWORK.registerMessage(PacketRetrieveVehicle.Handler.class, PacketRetrieveVehicle.class, ID++, Side.SERVER);
        CarManagementMod.NETWORK.registerMessage(PacketLockVehicle.Handler.class, PacketLockVehicle.class, ID++, Side.SERVER);
    }

}
