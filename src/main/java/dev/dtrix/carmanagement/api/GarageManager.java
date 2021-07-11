package dev.dtrix.carmanagement.api;

import dev.dtrix.carmanagement.CarManagementAddon;
import dev.dtrix.carmanagement.garage.DummyStorage;

public class GarageManager {

    private static IGarageStorage storage;

    public static void init(IGarageStorage storage) {
        CarManagementAddon.getLogger().debug("A storage manager has been registered.");
        GarageManager.storage = storage;
    }

    public static IGarageStorage getStorage() {
        if(storage == null) {
            CarManagementAddon.getLogger().error("No storage manager has been registered, defaulting to DummyStorage.class");
            storage = new DummyStorage();
        }
        return storage;
    }

}
