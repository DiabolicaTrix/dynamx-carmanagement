package dev.dtrix.carmanagement.garage;

import dev.dtrix.carmanagement.api.IGarageStorage;
import dev.dtrix.carmanagement.api.StoredVehicle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy implementation of the IGarageStorage interface.
 * This does absolutely nothing.
 * It is used as a default implementation when no other storage implementation has been registered.
 * @author DiabolicaTrix
 */
public class DummyStorage implements IGarageStorage {

    @Override
    public List<StoredVehicle> list(EntityPlayer player) {
        return new ArrayList<>();
    }

    @Override
    public boolean store(EntityPlayer player, StoredVehicle vehicle) {
        return false;
    }

    @Override
    public boolean retrieve(EntityPlayer player, StoredVehicle vehicle) {
        return false;
    }

    @Override
    public int insert(EntityPlayer player, StoredVehicle vehicle) { return -1; }

    @Override
    public BlockPos getSpawnPosition(EntityPlayer player) {
        return player.getPosition().up(3);
    }
}
