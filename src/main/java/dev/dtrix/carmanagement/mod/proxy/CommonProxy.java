package dev.dtrix.carmanagement.mod.proxy;

import dev.dtrix.carmanagement.mod.CarManagementMod;
import dev.dtrix.carmanagement.mod.packets.PacketGarageGui;
import dev.dtrix.carmanagement.mod.packets.PacketLockVehicle;
import dev.dtrix.carmanagement.mod.packets.PacketRetrieveVehicle;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.server.permission.DefaultPermissionLevel;
import net.minecraftforge.server.permission.PermissionAPI;

public class CommonProxy {

    private static int ID = 0;

    public void preInit() {
    }

    public void init() {
        CarManagementMod.NETWORK.registerMessage(PacketGarageGui.Handler.class, PacketGarageGui.class, ID++, Side.CLIENT);
        CarManagementMod.NETWORK.registerMessage(PacketRetrieveVehicle.Handler.class, PacketRetrieveVehicle.class, ID++, Side.SERVER);
        CarManagementMod.NETWORK.registerMessage(PacketLockVehicle.Handler.class, PacketLockVehicle.class, ID++, Side.SERVER);

        PermissionAPI.registerNode("carmanagement.destroy", DefaultPermissionLevel.OP, "Allows player to destroy vehicles");
    }

}
