package dev.dtrix.carmanagement.mod.packets;

import com.jme3.math.Vector3f;
import dev.dtrix.carmanagement.api.GarageManager;
import dev.dtrix.carmanagement.api.StoredVehicle;
import dev.dtrix.carmanagement.mod.item.Items;
import fr.dynamx.addons.basics.common.KeyUtils;
import fr.dynamx.common.entities.vehicles.CarEntity;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.*;

public class PacketRetrieveVehicle implements IMessage {

    //WORKAROUND
    public static final Map<UUID, StoredVehicle> vehicleMap = new HashMap<>();

    public StoredVehicle vehicle;

    public PacketRetrieveVehicle() {}

    public PacketRetrieveVehicle(StoredVehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        vehicle = new StoredVehicle();
        vehicle.deserializeNBT(ByteBufUtils.readTag(buf));
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, vehicle.serializeNBT());
    }

    public static class Handler implements IMessageHandler<PacketRetrieveVehicle, IMessage> {

        @Override
        public IMessage onMessage(PacketRetrieveVehicle message, MessageContext ctx) {
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(() -> {
                if(GarageManager.getStorage().retrieve(ctx.getServerHandler().player, message.vehicle)) {
                    BlockPos spawnPosition = GarageManager.getStorage().getSpawnPosition(ctx.getServerHandler().player);
                    CarEntity vehicle = new CarEntity(message.vehicle.getName(), ctx.getServerHandler().player.world, new Vector3f(spawnPosition.getX(), spawnPosition.getY(), spawnPosition.getZ()), 0, message.vehicle.getMetadata());
                    ctx.getServerHandler().player.world.spawnEntity(vehicle);

                    ItemStack key = new ItemStack(Items.KEY);
                    KeyUtils.setLinkedVehicle(key, vehicle.getUniqueID());
                    ctx.getServerHandler().player.addItemStackToInventory(key);

                    vehicleMap.put(vehicle.getUniqueID(), message.vehicle);
                }
            });
            return null;
        }

    }
}
