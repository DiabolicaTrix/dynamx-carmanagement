package dev.dtrix.carmanagement.garage;

import fr.dynamx.common.entities.BaseVehicleEntity;
import net.minecraft.entity.player.EntityPlayer;

import java.util.List;

/**
 * Interface for vehicle management. Can be implemented for whatever method of storage.
 * @author DiabolicaTrix
 */
public interface IGarageStorage {

    /**
     * Returns a list of the player's stored vehicles.
     * @param player owner of the vehicles to be fetched from storage.
     * @return a list containing the stored vehicles.
     */
    List<StoredVehicle> list(EntityPlayer player);

    /**
     * Used to store a currently active vehicle in a player's garage.
     * NB: An active vehicle is a vehicle that is currently in the game as an Entity.
     * @param player The player storing the vehicle.
     * @param vehicle Instance of the active vehicle to be stored.
     * @return a state boolean. True if successful, false otherwise.
     */
    boolean store(EntityPlayer player, StoredVehicle vehicle);

    /**
     * Used to retrieve a vehicle from a player's garage.
     * @param player The player retrieving the vehicle.
     * @param vehicle The vehicle to be retrieved. The stored vehicle instance should
     *                generally come from the previously fetched list of vehicles.
     * @return a state boolean. True if successful, false otherwise.
     */
    boolean retrieve(EntityPlayer player, StoredVehicle vehicle);

}
