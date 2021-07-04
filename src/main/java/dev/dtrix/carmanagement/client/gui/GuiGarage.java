package dev.dtrix.carmanagement.client.gui;

import com.google.common.collect.Lists;
import com.jme3.math.Vector3f;
import dev.dtrix.carmanagement.CarManagementAddon;
import dev.dtrix.carmanagement.garage.StoredVehicle;
import dev.dtrix.carmanagement.mod.CarManagementMod;
import fr.aym.acsguis.component.layout.GuiScaler;
import fr.aym.acsguis.component.panel.GuiFrame;
import fr.aym.acsguis.component.panel.GuiPanel;
import fr.dynamx.common.contentpack.DynamXObjectLoaders;
import fr.dynamx.common.contentpack.loader.ModularVehicleInfoBuilder;
import fr.dynamx.common.entities.vehicles.CarEntity;
import fr.dynamx.common.handlers.DynamXGuiHandler;
import fr.dynamx.utils.client.DynamXRenderUtils;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.voxelindustry.brokkgui.element.GuiListView;
import net.voxelindustry.brokkgui.gui.BrokkGuiScreen;
import net.voxelindustry.brokkgui.panel.GuiAbsolutePane;
import net.voxelindustry.brokkgui.panel.GuiRelativePane;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GuiGarage extends BrokkGuiScreen {

    private Entity selectedEntity;
    private long frame;

    public GuiGarage(List<StoredVehicle> entities) {
        super(0.5f, 0.5f, 300, 200);

        this.addStylesheet("/assets/" + CarManagementAddon.MODID + "/css/garage.css");

        final GuiRelativePane mainPanel = new GuiRelativePane();
        this.setMainPanel(mainPanel);

        GuiAbsolutePane body = new GuiAbsolutePane();
        body.setSizeRatio(1, 1);
        mainPanel.addChild(body);

        body.setID("body");

        GuiListView<StoredVehicle> listView = new GuiListView<>();
        listView.setID("list");
        listView.setSize(150, 190);
        listView.setCellSize(140, 30);
        listView.setElements(entities);
        listView.setOnClickEvent(event -> {
            /*this.selectedEntity = entities.get(listView.getSelectedCellIndex()-2);
            System.out.println(listView.getSelectedCellIndex() - 2);
            System.out.println((((CarEntity<?>) this.selectedEntity).getInfoName()));*/
        });

        body.addChild(listView, 5, 5);
    }

    @Override
    public void renderLast(int mouseX, int mouseY) {
        super.renderLast(mouseX, mouseY);
        if(this.selectedEntity instanceof CarEntity) {
            GlStateManager.pushMatrix();

            GlStateManager.enableRescaleNormal();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            GlStateManager.translate(200, 100, 0);
            GlStateManager.scale(50, 50, 50);
            GlStateManager.rotate(180, 0, 0, 1);
            GlStateManager.rotate(90, 0, 1, 0);

            //DynamXRenderUtils.renderCar(DynamXObjectLoaders.WHEELED_VEHICLES.findInfo(((CarEntity) selectedEntity).getInfoName()), (byte) frame);

            GlStateManager.disableRescaleNormal();
            GlStateManager.disableLighting();
            GlStateManager.popMatrix();

            frame++;
        }
    }
}
