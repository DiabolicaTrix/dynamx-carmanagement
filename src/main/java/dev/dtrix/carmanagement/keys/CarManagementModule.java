package dev.dtrix.carmanagement.keys;

import fr.dynamx.api.entities.modules.IPhysicsModule;
import fr.dynamx.common.entities.BaseVehicleEntity;
import fr.dynamx.common.physics.entities.AbstractEntityPhysicsHandler;
import net.minecraft.nbt.NBTTagCompound;

import java.util.UUID;

public class CarManagementModule implements IPhysicsModule<AbstractEntityPhysicsHandler<?, ?>> {

    private BaseVehicleEntity<?> vehicleEntity;
    private boolean locked;
    private UUID owner = UUID.randomUUID();

    public CarManagementModule(BaseVehicleEntity<?> vehicleEntity) {
        this.vehicleEntity = vehicleEntity;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        tag.setBoolean("locked", this.locked);
        tag.setUniqueId("owner", this.owner);
        IPhysicsModule.super.writeToNBT(tag);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        IPhysicsModule.super.readFromNBT(tag);
        this.locked = tag.getBoolean("locked");
        this.owner = tag.getUniqueId("owner");
    }
}
