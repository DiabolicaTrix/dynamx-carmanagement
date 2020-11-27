package dev.dtrix.carmanagement;

import fr.dynamx.api.contentpack.DynamXAddon;
import net.minecraftforge.fml.common.Mod;

import java.util.logging.Logger;

@DynamXAddon(modid = CarManagementAddon.MODID, name = "Car Management Addon")
public class CarManagementAddon {

    public static final String MODID = "carmanagement";

    private static final Logger LOGGER = Logger.getLogger("Car Management");

    @DynamXAddon.AddonEventSubscriber
    public static void init()
    {
        LOGGER.info("Initializing Car Management Addon.");

    }

}
