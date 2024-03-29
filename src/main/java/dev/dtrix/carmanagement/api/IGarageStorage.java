package dev.dtrix.carmanagement.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

import java.util.List;

/**
 * Interface for vehicle management. Can be implemented for whatever method of storage.
 * @author DiabolicaTrix
 */
public interface IGarageStorage {

    /**
     * Returns the list of available vehicles a player currently has in storage.
     * A vehicle needs not to be present as an entity in the world in order to be considered available.
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

    /**
     * Used to insert a new vehicle in storage. This is typically used when buying a new vehicle.
     * @param player The owner of the vehicle.
     * @param vehicle The vehicle to be added.
     * @return the id of the inserted vehicle, -1 if an error occured.
     */
    int insert(EntityPlayer player, StoredVehicle vehicle);

    /**
     * Used to find a valid spawn position for a vehicle
     * @param player the player spawning the vehicle.
     * @return a position
     */
    BlockPos getSpawnPosition(EntityPlayer player);

}
