package dev.dtrix.carmanagement.garage;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * This class is used to represent a vehicle that is currently in storage.
 * The information contained should be enough to create the Entity in the world and to store the entity back in storage.
 * @author DiabolicaTrix
 */
public class StoredVehicle implements INBTSerializable<NBTTagCompound> {

    private int id;
    private String name;
    private int metadata;

    public StoredVehicle() {}

    public StoredVehicle(int id, String name, int metadata) {
        this.id = id;
        this.name = name;
        this.metadata = metadata;
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

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("id", this.id);
        nbt.setInteger("metadata", this.metadata);
        nbt.setString("name", this.name);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        if(nbt.hasKey("id")) {
            this.id = nbt.getInteger("id");
            this.metadata = nbt.getInteger("metadata");
            this.name = nbt.getString("name");
        }
    }

}
