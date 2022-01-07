package club.shrimphack.swag.features.modules.client;

import club.shrimphack.swag.event.events.Render2DEvent;
import club.shrimphack.swag.features.modules.Module;
import club.shrimphack.swag.features.setting.Setting;
import club.shrimphack.swag.util.ColorUtil;
import club.shrimphack.swag.util.RenderUtil;

public class CSGOWatermark extends Module
{
    private int color1;
    private int rainbow1;
    private int rainbow2;
    private int outline;
    private int block;
    private final Setting<Boolean> l_rainbow;
    public Setting<Integer> l_red;
    public Setting<Integer> l_green;
    public Setting<Integer> l_blue;
    public Setting<Integer> l_red2;
    public Setting<Integer> l_green2;
    public Setting<Integer> l_blue2;
    public Setting<Integer> l_alpha;
    private final Setting<Boolean> t_rainbow;
    public Setting<Integer> t_red;
    public Setting<Integer> t_green;
    public Setting<Integer> t_blue;
    public Setting<Integer> t_alpha;
    public Setting<Integer> b_red;
    public Setting<Integer> b_green;
    public Setting<Integer> b_blue;
    public Setting<Integer> b_alpha;
    private final Setting<Boolean> o_rainbow;
    public Setting<Integer> o_red;
    public Setting<Integer> o_green;
    public Setting<Integer> o_blue;
    public Setting<Integer> o_alpha;
    public Setting<Integer> compactX;
    public Setting<Integer> compactY;
    public Setting<Integer> fineX;
    public Setting<Integer> fineY;
    public Setting<String> custom;

    public CSGOWatermark() {
        super("CSGOWatermark", "Weiner hi this is csgo cheat now!!!!", Category.CLIENT, true, false, false);
        this.l_rainbow = (Setting<Boolean>)this.register(new Setting("LineRainbow", true));
        this.l_red = (Setting<Integer>)this.register(new Setting("LineRedLeft", 20, 0, 255));
        this.l_green = (Setting<Integer>)this.register(new Setting("LineGreenLeft", 20, 0, 255));
        this.l_blue = (Setting<Integer>)this.register(new Setting("LineBlueLeft", 20, 0, 255));
        this.l_red2 = (Setting<Integer>)this.register(new Setting("LineRedRight", 20, 0, 255));
        this.l_green2 = (Setting<Integer>)this.register(new Setting("LineGreenRight", 20, 0, 255));
        this.l_blue2 = (Setting<Integer>)this.register(new Setting("LineBlueRight", 20, 0, 255));
        this.l_alpha = (Setting<Integer>)this.register(new Setting("Alpha", 20, 0, 255));
        this.t_rainbow = (Setting<Boolean>)this.register(new Setting("TextRainbow", true));
        this.t_red = (Setting<Integer>)this.register(new Setting("TextRed", 255, 0, 255));
        this.t_green = (Setting<Integer>)this.register(new Setting("TextGreen", 255, 0, 255));
        this.t_blue = (Setting<Integer>)this.register(new Setting("TextBlue", 255, 0, 255));
        this.t_alpha = (Setting<Integer>)this.register(new Setting("TextAlpha", 255, 0, 255));
        this.b_red = (Setting<Integer>)this.register(new Setting("BlockRed", 77, 0, 255));
        this.b_green = (Setting<Integer>)this.register(new Setting("BlockGreen", 77, 0, 255));
        this.b_blue = (Setting<Integer>)this.register(new Setting("BlockBlue", 77, 0, 255));
        this.b_alpha = (Setting<Integer>)this.register(new Setting("BlockAlpha", 255, 0, 255));
        this.o_rainbow = (Setting<Boolean>)this.register(new Setting("OutlineRainbow", true));
        this.o_red = (Setting<Integer>)this.register(new Setting("OutlineRed", 122, 0, 255));
        this.o_green = (Setting<Integer>)this.register(new Setting("OutlineGreen", 122, 0, 255));
        this.o_blue = (Setting<Integer>)this.register(new Setting("OutlineBlue", 122, 0, 255));
        this.o_alpha = (Setting<Integer>)this.register(new Setting("OutlineAlpha", 255, 0, 255));
        this.compactX = (Setting<Integer>)this.register(new Setting("CSGOWatermarkX", 3, 0, 1080));
        this.compactY = (Setting<Integer>)this.register(new Setting("CSGOWatermarkY", 3, 0, 530));
        this.fineX = (Setting<Integer>)this.register(new Setting("CSGOWatermarkXFine", 3, 0, 10));
        this.fineY = (Setting<Integer>)this.register(new Setting("CSGOWatermarkYFine", 3, 0, 10));
        this.custom = (Setting<String>)this.register(new Setting("Custom Name", "RerHack.club"));
    }

    @Override
    public void onRender2D(final Render2DEvent event) {
        if (this.t_rainbow.getValue()) {
            this.color1 = ColorUtil.rainbow(Colors.INSTANCE.rainbowSaturation.getValue()).getRGB();
        }
        else {
            this.color1 = ColorUtil.toRGBA(this.t_red.getValue(), this.t_blue.getValue(), this.t_green.getValue(), this.t_alpha.getValue());
        }
        if (this.l_rainbow.getValue()) {
            this.rainbow1 = ColorUtil.rainbow(Colors.INSTANCE.rainbowSaturation.getValue()).getRGB();
            this.rainbow2 = ColorUtil.rainbow(Colors.INSTANCE.rainbowSaturation.getValue()).getRGB();
        }
        else {
            this.rainbow1 = ColorUtil.toRGBA(this.l_red.getValue(), this.l_green.getValue(), this.l_blue.getValue(), this.l_alpha.getValue());
            this.rainbow2 = ColorUtil.toRGBA(this.l_red2.getValue(), this.l_green2.getValue(), this.l_blue2.getValue(), this.l_alpha.getValue());
        }
        if (this.o_rainbow.getValue()) {
            this.outline = ColorUtil.rainbow(Colors.INSTANCE.rainbowSaturation.getValue()).getRGB();
        }
        else {
            this.outline = ColorUtil.toRGBA(this.o_red.getValue(), this.o_blue.getValue(), this.o_green.getValue(), this.o_alpha.getValue());
        }
        this.block = ColorUtil.toRGBA(this.b_red.getValue(), this.b_blue.getValue(), this.b_green.getValue(), this.b_alpha.getValue());
        final String watermark = this.custom.getValue();
        final String name = CSGOWatermark.mc.player.getName() + "";
        RenderUtil.drawRectangleCorrectly(this.compactX.getValue() - 1 + this.fineX.getValue(), this.compactY.getValue() + this.fineY.getValue(), this.renderer.getStringWidth(watermark) + this.renderer.getStringWidth(name) + 18, 16, this.outline);
        RenderUtil.drawRectangleCorrectly(this.compactX.getValue() + this.fineX.getValue(), this.compactY.getValue() + this.fineY.getValue(), this.renderer.getStringWidth(watermark) + this.renderer.getStringWidth(name) + 16, 15, this.block);
        RenderUtil.drawGradientSideways(this.compactX.getValue() + this.fineX.getValue(), 0.0 + this.compactY.getValue() + this.fineY.getValue(), this.renderer.getStringWidth(watermark) + this.renderer.getStringWidth(name) + 16 + this.compactX.getValue(), 1.5 + this.compactY.getValue(), this.rainbow1, this.rainbow2);
        this.renderer.drawString(" " + watermark + " | " + name, (float)(this.compactX.getValue() + this.fineX.getValue()), (float)(this.compactY.getValue() + this.fineY.getValue() + 3), this.color1, true);
    }
}

