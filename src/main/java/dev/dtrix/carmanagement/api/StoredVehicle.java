package dev.dtrix.carmanagement.api;

import fr.dynamx.common.contentpack.DynamXObjectLoaders;
import fr.dynamx.common.contentpack.ModularVehicleInfo;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.UUID;

/**
 * This class is used to represent a vehicle that is currently in storage.
 * The information contained should be enough to create the Entity in the world and to store the entity back in storage.
 * @author DiabolicaTrix
 */
public class StoredVehicle implements INBTSerializable<NBTTagCompound> {

    private int id;
    private String name;
    private int metadata;
    private UUID owner;

    /**
     * This variable is used to store the vehicle information,
     * it is stored to prevent a call to the registry every time the information are required.
     * The value is lazy-loaded only if required.
     */
    private ModularVehicleInfo<?> vehicleInfo;

    public StoredVehicle() {}

    public StoredVehicle(int id, String name, int metadata, UUID owner) {
        this.id = id;
        this.name = name;
        this.metadata = metadata;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMetadata() {
        return metadata;
    }

    public void setMetadata(int metadata) {
        this.metadata = metadata;
    }

    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public ModularVehicleInfo<?> getVehicleInfo() {
        if(vehicleInfo == null) {
            vehicleInfo = DynamXObjectLoaders.WHEELED_VEHICLES.findInfo(name);
        }
        return vehicleInfo;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("id", this.id);
        nbt.setInteger("metadata", this.metadata);
        nbt.setString("name", this.name);
        nbt.setUniqueId("owner", this.owner);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        if(nbt.hasKey("id")) {
            this.id = nbt.getInteger("id");
            this.metadata = nbt.getInteger("metadata");
            this.name = nbt.getString("name");
            this.owner = nbt.getUniqueId("owner");
        }
    }

}
