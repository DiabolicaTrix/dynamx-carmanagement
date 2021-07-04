package dev.dtrix.carmanagement;

import fr.dynamx.api.contentpack.DynamXAddon;
import fr.dynamx.api.network.sync.SynchronizedVariablesRegistry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@DynamXAddon(modid = CarManagementAddon.MODID, name = "Car Management Addon", version = "1.0")
public class CarManagementAddon {

    public static final String MODID = "carmanagement";

    private static final Logger LOGGER = LogManager.getLogger(MODID);

    @DynamXAddon.AddonEventSubscriber
    public static void init()
    {
        LOGGER.info("Initializing Car Management Addon.");

        //SynchronizedVariablesRegistry.addSyncVar(new ResourceLocation(MODID, "key_uuid"), )
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}
