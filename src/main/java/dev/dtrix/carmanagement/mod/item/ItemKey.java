package dev.dtrix.carmanagement.mod.item;

import dev.dtrix.carmanagement.CarManagementAddon;
import dev.dtrix.carmanagement.mod.CarManagementMod;
import dev.dtrix.carmanagement.mod.packets.PacketLockVehicle;
import dev.dtrix.carmanagement.mod.proxy.CommonProxy;
import dev.dtrix.dtrixlib.client.interaction.IInteractionAction;
import dev.dtrix.dtrixlib.client.interaction.InteractionMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.lwjgl.input.Mouse;

import java.util.Arrays;
import java.util.List;

public class ItemKey extends Item {

    private static final List<IInteractionAction> KEY_INTERACTION = Arrays.asList(new IInteractionAction() {
        @Override
        public void execute(EntityPlayer entityPlayer) {
            CarManagementMod.NETWORK.sendToServer(new PacketLockVehicle(false));
        }

        @Override
        public String getName() {
            return "Unlock";
        }

        @Override
        public ResourceLocation getIcon() {
            return new ResourceLocation(CarManagementAddon.MODID, "test");
        }
    }, new IInteractionAction() {
        @Override
        public void execute(EntityPlayer entityPlayer) {
            CarManagementMod.NETWORK.sendToServer(new PacketLockVehicle(true));
        }

        @Override
        public String getName() {
            return "Lock";
        }

        @Override
        public ResourceLocation getIcon() {
            return new ResourceLocation(CarManagementAddon.MODID, "test");
        }
    });

    public ItemKey() {
        setCreativeTab(CarManagementMod.TAB);
        setMaxStackSize(1);
        setNoRepair();
        setRegistryName(CarManagementAddon.MODID, "key");
        setTranslationKey("key");
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return Integer.MAX_VALUE;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if(worldIn.isRemote) {
            Minecraft.getMinecraft().displayGuiScreen(new InteractionMenu(KEY_INTERACTION, () -> !Mouse.isButtonDown(1)));
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
