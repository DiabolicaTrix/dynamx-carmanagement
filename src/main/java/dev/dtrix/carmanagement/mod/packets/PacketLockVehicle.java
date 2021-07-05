package dev.dtrix.carmanagement.mod.packets;

import dev.dtrix.carmanagement.keys.CarManagementModule;
import dev.dtrix.carmanagement.mod.item.ItemKey;
import fr.dynamx.addons.basics.common.KeyUtils;
import fr.dynamx.common.entities.BaseVehicleEntity;
import fr.dynamx.common.entities.vehicles.CarEntity;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.List;

public class PacketLockVehicle implements IMessage {

    public boolean lock;

    public PacketLockVehicle() {}

    public PacketLockVehicle(boolean lock) {
        this.lock = lock;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.lock = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(this.lock);
    }

    public static class Handler implements IMessageHandler<PacketLockVehicle, IMessage> {

        @Override
        public IMessage onMessage(PacketLockVehicle message, MessageContext ctx) {
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(() -> {
                if(ctx.getServerHandler().player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemKey) {
                    List<BaseVehicleEntity> entities = ctx.getServerHandler().player.world.getEntities(BaseVehicleEntity.class, car -> car.getDistance(ctx.getServerHandler().player) <= 16.0D
                            && KeyUtils.hasLinkedVehicle(ctx.getServerHandler().player.getHeldItem(EnumHand.MAIN_HAND)) && car.getUniqueID().compareTo(KeyUtils.getLinkedVehicle(ctx.getServerHandler().player.getHeldItem(EnumHand.MAIN_HAND))) == 0);
                    entities.forEach(car -> {
                        CarManagementModule module = (CarManagementModule) car.getModuleByType(CarManagementModule.class);
                        module.setLocked(message.lock);
                        if(message.lock) {
                            ctx.getServerHandler().player.sendMessage(new TextComponentTranslation("carmanagement.lock"));
                        } else {
                            ctx.getServerHandler().player.sendMessage(new TextComponentTranslation("carmanagement.unlock"));
                        }
                    });
                }
            });
            return null;
        }

    }
}
