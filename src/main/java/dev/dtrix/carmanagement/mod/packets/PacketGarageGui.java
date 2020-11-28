package dev.dtrix.carmanagement.mod.packets;

import dev.dtrix.carmanagement.client.gui.GuiGarage;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.server.FMLServerHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PacketGarageGui implements IMessage {

    public List<Integer> entitiesIdList = new ArrayList<>();

    public PacketGarageGui() {}

    public PacketGarageGui(List<Integer> entitiesIdList) {
        this.entitiesIdList = entitiesIdList;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        final int size = buf.readInt();
        for(int i = 0; i < size; i++) {
            this.entitiesIdList.add(buf.readInt());
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.entitiesIdList.size());
        this.entitiesIdList.forEach(buf::writeInt);
    }

    public static class Handler implements IMessageHandler<PacketGarageGui, IMessage> {

        @Override
        public IMessage onMessage(PacketGarageGui message, MessageContext ctx) {
            final EntityPlayer player = Minecraft.getMinecraft().player;
            Minecraft.getMinecraft().addScheduledTask(() -> {
                List<Entity> entities = message.entitiesIdList.stream().map(player.world::getEntityByID).collect(Collectors.toList());
                Minecraft.getMinecraft().displayGuiScreen(new GuiGarage(entities));
            });
            return null;
        }

    }
}
