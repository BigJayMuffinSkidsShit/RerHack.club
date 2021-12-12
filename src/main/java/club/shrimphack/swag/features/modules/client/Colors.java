package club.shrimphack.swag.features.modules.client;

import club.shrimphack.swag.features.setting.Setting;
import club.shrimphack.swag.RerHack;
import club.shrimphack.swag.features.modules.Module;
import club.shrimphack.swag.util.ColorUtil;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Colors extends Module
{
    public static Colors INSTANCE;
    public Setting<Boolean> rainbow;
    public Setting<Integer> rainbowSpeed;
    public Setting<Integer> rainbowSaturation;
    public Setting<Integer> rainbowBrightness;
    public Setting<Integer> red;
    public Setting<Integer> green;
    public Setting<Integer> blue;
    public Setting<Integer> alpha;
    public float hue;
    public Map<Integer, Integer> colorHeightMap;

    public Colors() {
        super("Colors", "Universal colors.", Category.CLIENT, true, false, true);
        this.rainbow = (Setting<Boolean>)this.register(new Setting("Rainbow", false, "Rainbow colors."));
        this.rainbowSpeed = (Setting<Integer>)this.register(new Setting("Speed", 20, 0, 100, v -> this.rainbow.getValue()));
        this.rainbowSaturation = (Setting<Integer>)this.register(new Setting("Saturation", 255, 0, 255, v -> this.rainbow.getValue()));
        this.rainbowBrightness = (Setting<Integer>)this.register(new Setting("Brightness", 255, 0, 255, v -> this.rainbow.getValue()));
        this.red = (Setting<Integer>)this.register(new Setting("Red", 0, 0, 255, v -> !this.rainbow.getValue()));
        this.green = (Setting<Integer>)this.register(new Setting("Green", 20, 0, 255, v -> !this.rainbow.getValue()));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", 255, 0, 255, v -> !this.rainbow.getValue()));
        this.alpha = (Setting<Integer>)this.register(new Setting("Alpha", 255, 0, 255, v -> !this.rainbow.getValue()));
        this.colorHeightMap = new HashMap<>();
        Colors.INSTANCE = this;
    }

    @Override
    public void onTick() {
        final int colorSpeed = 101 - this.rainbowSpeed.getValue();
        final float hue = System.currentTimeMillis() % (360 * colorSpeed) / (360.0f * colorSpeed);
        this.hue = hue;
        float tempHue = hue;
        for (int i = 0; i <= 510; ++i) {
            this.colorHeightMap.put(i, Color.HSBtoRGB(tempHue, this.rainbowSaturation.getValue() / 255.0f, this.rainbowBrightness.getValue() / 255.0f));
            tempHue += 0.0013071896f;
        }
        if (ClickGui.getInstance().rainbow.getValue()) {
            RerHack.colorManager.setColor(Colors.INSTANCE.getCurrentColor().getRed(), Colors.INSTANCE.getCurrentColor().getGreen(), Colors.INSTANCE.getCurrentColor().getBlue(), ClickGui.getInstance().hoverAlpha.getValue());
        }
    }

    public int getCurrentColorHex() {
        if (this.rainbow.getValue()) {
            return Color.HSBtoRGB(this.hue, this.rainbowSaturation.getValue() / 255.0f, this.rainbowBrightness.getValue() / 255.0f);
        }
        return ColorUtil.toARGB(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue());
    }

    public Color getCurrentColor() {
        if (this.rainbow.getValue()) {
            return Color.getHSBColor(this.hue, this.rainbowSaturation.getValue() / 255.0f, this.rainbowBrightness.getValue() / 255.0f);
        }
        return new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue());
    }
}
