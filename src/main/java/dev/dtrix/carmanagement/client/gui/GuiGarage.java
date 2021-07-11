package dev.dtrix.carmanagement.client.gui;

import dev.dtrix.carmanagement.CarManagementAddon;
import dev.dtrix.carmanagement.api.StoredVehicle;
import dev.dtrix.carmanagement.mod.CarManagementMod;
import dev.dtrix.carmanagement.mod.packets.PacketRetrieveVehicle;
import fr.dynamx.common.contentpack.DynamXObjectLoaders;
import fr.dynamx.common.contentpack.ModularVehicleInfo;
import fr.dynamx.utils.client.DynamXRenderUtils;
import net.minecraft.client.renderer.GlStateManager;
import net.voxelindustry.brokkgui.data.RectAlignment;
import net.voxelindustry.brokkgui.data.RectBox;
import net.voxelindustry.brokkgui.element.GuiLabel;
import net.voxelindustry.brokkgui.element.GuiListCell;
import net.voxelindustry.brokkgui.element.GuiListView;
import net.voxelindustry.brokkgui.element.input.GuiButton;
import net.voxelindustry.brokkgui.gui.BrokkGuiScreen;
import net.voxelindustry.brokkgui.panel.GuiAbsolutePane;
import net.voxelindustry.brokkgui.panel.GuiRelativePane;
import net.voxelindustry.brokkgui.shape.Rectangle;

import java.util.List;

public class GuiGarage extends BrokkGuiScreen {

    private ModularVehicleInfo<?> selectedInfo;
    private StoredVehicle selected;

    private GuiLabel mass;
    private GuiLabel speed;
    private GuiLabel variant;

    public final GuiListView<StoredVehicle> listView;
    public final GuiButton retrieveButton;

    public GuiGarage(List<StoredVehicle> entities) {
        super(0.5f, 0.5f, 300, 200);

        this.addStylesheet("/assets/" + CarManagementAddon.MODID + "/css/garage.css");

        final GuiRelativePane mainPanel = new GuiRelativePane();
        this.setMainPanel(mainPanel);

        GuiAbsolutePane body = new GuiAbsolutePane();
        body.setSizeRatio(1, 1);
        mainPanel.addChild(body);

        body.setID("body");

        Rectangle header = new Rectangle();
        header.setSize(300, 11);
        header.setID("header");
        body.addChild(header, 0, 0);

        GuiLabel title = new GuiLabel("Garage");
        title.setSize(300, 11);
        title.setTextAlignment(RectAlignment.MIDDLE_CENTER);
        title.addStyleClass("label");
        body.addChild(title, 0, 0);

        setupInformationPanel(body);

        listView = new GuiListView<>();
        listView.setID("list");
        listView.setSize(140, 180);
        listView.setCellSize(140, 15);
        listView.setCellFactory(vehicle -> {
            GuiListCell<StoredVehicle> cell = new GuiListCell<>(listView, vehicle);

            ModularVehicleInfo<?> info = DynamXObjectLoaders.WHEELED_VEHICLES.findInfo(vehicle.getName());

            GuiAbsolutePane container = new GuiAbsolutePane();
            container.setSizeRatio(1, 1);
            container.addStyleClass("element");

            GuiLabel vehicleLabel = new GuiLabel(info.getTranslatedName(null, vehicle.getMetadata()));
            vehicleLabel.setSizeRatio(1, 1);
            vehicleLabel.setTextAlignment(RectAlignment.LEFT_CENTER);
            vehicleLabel.setTextPadding(RectBox.build().left(5.0f).create());
            container.addChild(vehicleLabel, 0, 0);

            cell.setGraphic(container);
            return cell;
        });
        listView.setElements(entities);
        listView.setOnClickEvent(event -> {
            if(listView.getElements().size() == 0)
                return;
            //selectElement(listView.getSelectedCellIndex()-2);
            this.selected = entities.get(listView.getSelectedCellIndex()-2);
            ModularVehicleInfo<?> info = DynamXObjectLoaders.WHEELED_VEHICLES.findInfo(this.selected.getName());
            updateInformationPanel(info);
            this.selectedInfo = info;
        });
        body.addChild(listView, 5, 16);

        retrieveButton = new GuiButton("Retrieve");
        retrieveButton.addStyleClass("button");
        retrieveButton.setSize(145, 20);
        retrieveButton.setOnClickEvent(event -> {
            if(this.selected != null) {
                CarManagementMod.NETWORK.sendToServer(new PacketRetrieveVehicle(this.selected));
            }
        });
        body.addChild(retrieveButton, 150, 200 - 25);
    }

    @Override
    public void renderLast(int mouseX, int mouseY) {
        super.renderLast(mouseX, mouseY);
        if(this.selectedInfo != null) {
            GlStateManager.pushMatrix();

            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            GlStateManager.translate(this.getxPos() + 150 + 70,  this.getyPos() + 60, 50);
            GlStateManager.scale(15, 15, 15);
            GlStateManager.rotate(180, 0, 0, 1);
            GlStateManager.rotate(90, 0, 1, 0);

            DynamXRenderUtils.renderCar(this.selectedInfo, (byte) this.selected.getMetadata());

            GlStateManager.popMatrix();
        }
    }

    public void setupInformationPanel(GuiAbsolutePane body) {
        mass = new GuiLabel();
        body.addChild(mass, 150, 80);

        speed = new GuiLabel();
        body.addChild(speed, 150, 90);

        variant = new GuiLabel();
        body.addChild(variant, 150, 100);
    }

    public void updateInformationPanel(ModularVehicleInfo<?> info) {
        mass.setText("Mass: " + info.getEmptyMass());
        speed.setText("Max speed: " + info.getVehicleMaxSpeed());
        variant.setText("Variant: " + info.getTextures().get((byte) this.selected.getMetadata()).getName());
    }

}
