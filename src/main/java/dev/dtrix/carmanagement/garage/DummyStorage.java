package dev.dtrix.carmanagement.garage;

import fr.dynamx.common.entities.BaseVehicleEntity;
import fr.dynamx.common.entities.vehicles.CarEntity;
import net.minecraft.entity.player.EntityPlayer;
import scala.actors.threadpool.Arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy implementation of the IGarageStorage interface.
 * This does absolutely nothing.
 * It is used as a default implementation when no other storage implementation has been registered.
 * @author DiabolicaTrix
 */
public class DummyStorage implements IGarageStorage{

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
    public boolean insert(EntityPlayer player, StoredVehicle vehicle) { return false; }
}
