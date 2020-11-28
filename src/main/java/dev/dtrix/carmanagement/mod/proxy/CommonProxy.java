package dev.dtrix.carmanagement.mod.proxy;

import dev.dtrix.carmanagement.mod.CarManagementMod;
import dev.dtrix.carmanagement.mod.packets.PacketGarageGui;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy {

    private static int ID = 0;

    public void preInit() {}

    public void init() {
        CarManagementMod.NETWORK.registerMessage(PacketGarageGui.Handler.class, PacketGarageGui.class, ID++, Side.CLIENT);
    }

}
