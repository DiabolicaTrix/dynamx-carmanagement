package dev.dtrix.carmanagement.keys;

import fr.dynamx.api.entities.modules.IPhysicsModule;
import fr.dynamx.common.entities.BaseVehicleEntity;
import fr.dynamx.common.physics.entities.AbstractEntityPhysicsHandler;
import net.minecraft.nbt.NBTTagCompound;

public class CarManagementModule implements IPhysicsModule<AbstractEntityPhysicsHandler<?, ?>> {

    private BaseVehicleEntity<?> vehicleEntity;
    private boolean locked;

    public CarManagementModule(BaseVehicleEntity<?> vehicleEntity) {
        this.vehicleEntity = vehicleEntity;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        tag.setBoolean("locked", this.locked);
        IPhysicsModule.super.writeToNBT(tag);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        IPhysicsModule.super.readFromNBT(tag);
        this.locked = tag.getBoolean("locked");
    }
}
