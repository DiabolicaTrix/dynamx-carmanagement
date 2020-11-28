package dev.dtrix.carmanagement.client.gui;

import dev.dtrix.carmanagement.CarManagementAddon;
import dev.dtrix.carmanagement.mod.CarManagementMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.GuiScrollingList;

import java.util.List;

public class GuiGarage extends GuiScreen {

    public static final ResourceLocation TEXTURE = new ResourceLocation("textures/gui/demo_background.png");
    public static final int WIDTH = 248;
    public static final int HEIGHT = 166;

    private CarList activeCars;
    private List<Entity> entityList;

    public GuiGarage(List<Entity> entityList) {
        this.setGuiSize(WIDTH, HEIGHT);
        this.entityList = entityList;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.activeCars = new GuiGarage.CarList(this.mc);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int guiX = (width - WIDTH) / 2;
        int guiY = (height - HEIGHT) / 2;

        this.drawDefaultBackground();

        this.mc.getTextureManager().bindTexture(TEXTURE);
        this.drawTexturedModalRect(guiX, guiY, 0, 0, WIDTH, HEIGHT);

        GuiHelper.renderCar(guiX, guiY, 5, this.entityList.get(0));
        //this.activeCars.drawScreen(mouseX, mouseY, partialTicks);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    class CarList extends GuiScrollingList
    {
        public CarList(Minecraft client) {
            super(client, 120, GuiGarage.this.height, (GuiGarage.this.height - HEIGHT) / 2 + 12, (GuiGarage.this.height - HEIGHT) / 2 + HEIGHT - 20 - 10 - 2, (GuiGarage.this.width - WIDTH) / 2 + 10, 28, GuiGarage.this.width, GuiGarage.this.height);
            this.selectedIndex = 0;
        }

        @Override
        protected int getSize() {
            return 0;
        }

        @Override
        protected void elementClicked(int index, boolean doubleClick) {

        }

        @Override
        protected boolean isSelected(int index) {
            return false;
        }

        @Override
        protected void drawBackground() {
        }

        @Override
        protected int getContentHeight()
        {
            return (this.getSize()) * this.slotHeight + 1;
        }

        @Override
        protected void drawSlot(int slotIdx, int entryRight, int slotTop, int slotBuffer, Tessellator tess) {
            //GuiCarnet.this.drawString(GuiCarnet.this.fontRenderer, I18n.format("lyra.fine." + this.fineList.get(slotIdx).toLowerCase()), this.left + 3, slotTop + 2, 16777215);
            //GuiCarnet.this.drawString(GuiCarnet.this.fontRenderer,  I18n.format("lyra.fine.price",fineMap.get(this.fineList.get(slotIdx))), this.left + 3, slotTop + 2 + GuiCarnet.this.fontRenderer.FONT_HEIGHT + 2, 16777215);
        }
    }

}
