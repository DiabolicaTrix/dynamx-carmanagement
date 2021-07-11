package dev.dtrix.carmanagement.mod.packets;

import dev.dtrix.carmanagement.client.gui.GuiGarage;
import dev.dtrix.carmanagement.api.StoredVehicle;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.voxelindustry.brokkgui.wrapper.impl.BrokkGuiManager;

import java.util.ArrayList;
import java.util.List;

public class PacketGarageGui implements IMessage {

    public List<StoredVehicle> vehicles = new ArrayList<>();

    public PacketGarageGui() {}

    public PacketGarageGui(List<StoredVehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.vehicles = new ArrayList<>();
        final int size = buf.readInt();
        for(int i = 0; i < size; i++) {
            StoredVehicle vehicle = new StoredVehicle();
            vehicle.deserializeNBT(ByteBufUtils.readTag(buf));
            this.vehicles.add(vehicle);
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.vehicles.size());
        this.vehicles.forEach(vehicle -> ByteBufUtils.writeTag(buf, vehicle.serializeNBT()));
    }

    public static class Handler implements IMessageHandler<PacketGarageGui, IMessage> {

        @Override
        public IMessage onMessage(PacketGarageGui message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> BrokkGuiManager.openBrokkGuiScreen(new GuiGarage(message.vehicles)));
            return null;
        }

    }
}
