package club.shrimphack.swag.features.gui;

import club.shrimphack.swag.features.Feature;
import club.shrimphack.swag.RerHack;
import club.shrimphack.swag.features.gui.components.Component;
import club.shrimphack.swag.features.gui.components.items.Item;
import club.shrimphack.swag.features.gui.components.items.buttons.ModuleButton;
import club.shrimphack.swag.features.gui.particle.ParticleSystem;
import club.shrimphack.swag.features.gui.particle.Snow;
import club.shrimphack.swag.features.modules.Module;
import club.shrimphack.swag.features.modules.client.ClickGui;
import club.shrimphack.swag.features.modules.client.Colors;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class OyVeyGui
        extends GuiScreen {
    private static OyVeyGui oyveyGui;
    private static OyVeyGui INSTANCE;

    static {
        INSTANCE = new OyVeyGui();
    }

    private final ArrayList<Component> components = new ArrayList();
    private final ParticleSystem particleSystem;
    private ArrayList<Snow> _snowList;

    public OyVeyGui() {
        this.particleSystem = new ParticleSystem(100);
        this._snowList = new ArrayList<Snow>();
        this.setInstance();
        this.load();
    }

    public static OyVeyGui getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OyVeyGui();
        }
        return INSTANCE;
    }

    public static OyVeyGui getClickGui() {
        return OyVeyGui.getInstance();
    }

    private void setInstance() {
        INSTANCE = this;
    }

    private void load() {
        final Random random = new Random();
        for (int i = 0; i < 100; ++i) {
            for (int y = 0; y < 3; ++y) {
                final Snow snow = new Snow(25 * i, y * -50, random.nextInt(3) + 1, random.nextInt(2) + 1);
                this._snowList.add(snow);
            }
        }
        int x = -84;
        for (final Module.Category category : RerHack.moduleManager.getCategories()) {
            this.components.add(new Component(category.getName(), x += 90, 4, true) {

                @Override
                public void setupItems() {
                    counter1 = new int[]{1};
                    RerHack.moduleManager.getModulesByCategory(category).forEach(module -> {
                        if (!module.hidden) {
                            this.addButton(new ModuleButton(module));
                        }
                    });
                }
            });
        }
        this.components.forEach(components -> components.getItems().sort(Comparator.comparing(Feature::getName)));
    }

    public void updateModule(Module module) {
        for (Component component : this.components) {
            for (Item item : component.getItems()) {
                if (!(item instanceof ModuleButton)) continue;
                ModuleButton button = (ModuleButton) item;
                Module mod = button.getModule();
                if (module == null || !module.equals(mod)) continue;
                button.initSettings();
            }
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.checkMouseWheel();
        this.drawDefaultBackground();
        this.components.forEach(components -> components.drawScreen(mouseX, mouseY, partialTicks));
        ScaledResolution sr = new ScaledResolution(mc);
        if (ClickGui.getInstance().gradient.getValue(true)) {
            this.drawGradientRect(0, 0, sr.getScaledWidth(), sr.getScaledHeight(), 0, ((boolean)ClickGui.getInstance().rainbow.getValue(true)) ? Colors.INSTANCE.getCurrentColor().getRGB() : new Color(ClickGui.getInstance().red.getValue(true), ClickGui.getInstance().green.getValue(true), ClickGui.getInstance().blue.getValue(true), ClickGui.getInstance().alpha.getValue(true) / 2).getRGB());
        }
        if (!this._snowList.isEmpty() && ClickGui.getInstance().snow.getValue(true)) {
            this._snowList.forEach(snow -> snow.Update(sr));
        }

        if (ClickGui.getInstance().particles.getValue(true)) {
            this.particleSystem.tick(10);
            this.particleSystem.render();
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int clickedButton) {
        this.components.forEach(components -> components.mouseClicked(mouseX, mouseY, clickedButton));
    }

    public void mouseReleased(int mouseX, int mouseY, int releaseButton) {
        this.components.forEach(components -> components.mouseReleased(mouseX, mouseY, releaseButton));
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

    public final ArrayList<Component> getComponents() {
        return this.components;
    }

    public void checkMouseWheel() {
        int dWheel = Mouse.getDWheel();
        if (dWheel < 0) {
            this.components.forEach(component -> component.setY(component.getY() - 10));
        } else if (dWheel > 0) {
            this.components.forEach(component -> component.setY(component.getY() + 10));
        }
    }

    public int getTextOffset() {
        return -6;
    }

    public Component getComponentByName(String name) {
        for (Component component : this.components) {
            if (!component.getName().equalsIgnoreCase(name)) continue;
            return component;
        }
        return null;
    }

    public void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        this.components.forEach(component -> component.onKeyTyped(typedChar, keyCode));
    }
}

