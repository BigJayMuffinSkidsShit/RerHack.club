package club.shrimphack.swag.features.modules.render;

import club.shrimphack.swag.event.events.Render3DEvent;
import club.shrimphack.swag.features.modules.Module;
import club.shrimphack.swag.features.modules.client.ClickGui;
import club.shrimphack.swag.features.setting.Setting;
import club.shrimphack.swag.util.*;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

import java.awt.*;

public class HoleESP extends Module {
    public Setting<Boolean> renderOwn = register(new Setting<Boolean>("RenderOwn", true));
    public Setting<Boolean> fov = register(new Setting<Boolean>("InFov", true));
    public Setting<Boolean> rainbow = register(new Setting<Boolean>("Rainbow", false));
    private final Setting<Integer> range = register(new Setting<Integer>("RangeX", 5, 0, 10));
    private final Setting<Integer> rangeY = register(new Setting<Integer>("RangeY", 5, 0, 10));
    public Setting<Boolean> box = register(new Setting<Boolean>("Box", true));
    public Setting<Boolean> gradientBox = register(new Setting<Object>("Gradient", Boolean.valueOf(false), v -> box.getValue(true)));
    public Setting<Boolean> invertGradientBox = register(new Setting<Object>("ReverseGradient", Boolean.valueOf(false), v -> gradientBox.getValue(true)));
    public Setting<Boolean> outline = register(new Setting<Boolean>("Outline", true));
    public Setting<Boolean> gradientOutline = register(new Setting<Object>("GradientOutline", Boolean.valueOf(false), v -> outline.getValue(true)));
    public Setting<Boolean> invertGradientOutline = register(new Setting<Object>("ReverseOutline", Boolean.valueOf(false), v -> gradientOutline.getValue(true)));
    public Setting<Double> height = register(new Setting<Double>("Height", 0.0, -2.0, 2.0));
    private Setting<Integer> red = register(new Setting<Integer>("Red", 0, 0, 255));
    private Setting<Integer> green = register(new Setting<Integer>("Green", 255, 0, 255));
    private Setting<Integer> blue = register(new Setting<Integer>("Blue", 0, 0, 255));
    private Setting<Integer> alpha = register(new Setting<Integer>("Alpha", 255, 0, 255));
    private Setting<Integer> boxAlpha = register(new Setting<Object>("BoxAlpha", Integer.valueOf(125), Integer.valueOf(0), Integer.valueOf(255), v -> box.getValue(true)));
    private Setting<Float> lineWidth = register(new Setting<Object>("LineWidth", Float.valueOf(1.0f), Float.valueOf(0.1f), Float.valueOf(5.0f), v -> outline.getValue(true)));
    public Setting<Boolean> safeColor = register(new Setting<Boolean>("BedrockColor", false));
    private Setting<Integer> safeRed = register(new Setting<Object>("BedrockRed", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), v -> safeColor.getValue(true)));
    private Setting<Integer> safeGreen = register(new Setting<Object>("BedrockGreen", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), v -> safeColor.getValue(true)));
    private Setting<Integer> safeBlue = register(new Setting<Object>("BedrockBlue", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), v -> safeColor.getValue(true)));
    private Setting<Integer> safeAlpha = register(new Setting<Object>("BedrockAlpha", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), v -> safeColor.getValue(true)));
    public Setting<Boolean> customOutline = register(new Setting<Object>("CustomLine", Boolean.valueOf(false), v -> outline.getValue(true)));
    private Setting<Integer> cRed = register(new Setting<Object>("OL-Red", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), v -> customOutline.getValue(true) != false && outline.getValue(true) != false));
    private Setting<Integer> cGreen = register(new Setting<Object>("OL-Green", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), v -> customOutline.getValue(true) != false && outline.getValue(true) != false));
    private Setting<Integer> cBlue = register(new Setting<Object>("OL-Blue", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), v -> customOutline.getValue(true) != false && outline.getValue(true) != false));
    private Setting<Integer> cAlpha = register(new Setting<Object>("OL-Alpha", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), v -> customOutline.getValue(true) != false && outline.getValue(true) != false));
    private Setting<Integer> safecRed = register(new Setting<Object>("OL-SafeRed", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), v -> customOutline.getValue(true) != false && outline.getValue(true) != false && safeColor.getValue(true) != false));
    private Setting<Integer> safecGreen = register(new Setting<Object>("OL-SafeGreen", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), v -> customOutline.getValue(true) != false && outline.getValue(true) != false && safeColor.getValue(true) != false));
    private Setting<Integer> safecBlue = register(new Setting<Object>("OL-SafeBlue", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), v -> customOutline.getValue(true) != false && outline.getValue(true) != false && safeColor.getValue(true) != false));
    private Setting<Integer> safecAlpha = register(new Setting<Object>("OL-SafeAlpha", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), v -> customOutline.getValue(true) != false && outline.getValue(true) != false && safeColor.getValue(true) != false));
    private static HoleESP INSTANCE = new HoleESP();
    private int currentAlpha = 0;

    public HoleESP() {
        super("HoleESP", "Shows safe spots.", Module.Category.RENDER, false, false, false);
        setInstance();
    }

    private void setInstance() {
        INSTANCE = this;
    }

    public static HoleESP getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HoleESP();
        }
        return INSTANCE;
    }

    @Override
    public void onRender3D(Render3DEvent event) {
        assert (HoleESP.mc.getRenderViewEntity() != null);
        Vec3i playerPos = new Vec3i(HoleESP.mc.getRenderViewEntity().posX, HoleESP.mc.getRenderViewEntity().posY, HoleESP.mc.getRenderViewEntity().posZ);
        for (int x = playerPos.getX() - range.getValue(true); x < playerPos.getX() + range.getValue(true); ++x) {
            for (int z = playerPos.getZ() - range.getValue(true); z < playerPos.getZ() + range.getValue(true); ++z) {
                for (int y = playerPos.getY() + rangeY.getValue(true); y > playerPos.getY() - rangeY.getValue(true); --y) {
                    BlockPos pos = new BlockPos(x, y, z);
                    if (!HoleESP.mc.world.getBlockState(pos).getBlock().equals(Blocks.AIR) || !HoleESP.mc.world.getBlockState(pos.add(0, 1, 0)).getBlock().equals(Blocks.AIR) || !HoleESP.mc.world.getBlockState(pos.add(0, 2, 0)).getBlock().equals(Blocks.AIR) || pos.equals(new BlockPos(HoleESP.mc.player.posX, HoleESP.mc.player.posY, HoleESP.mc.player.posZ)) && !renderOwn.getValue(true).booleanValue() || !BlockUtil.isPosInFov(pos).booleanValue() && fov.getValue(true).booleanValue())
                        continue;
                    if (HoleESP.mc.world.getBlockState(pos.north()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(pos.east()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(pos.west()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(pos.south()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(pos.down()).getBlock() == Blocks.BEDROCK) {
                        RenderUtil.drawBoxESP(pos, rainbow.getValue(true) ? ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue(true)) : new Color(safeRed.getValue(true), safeGreen.getValue(true), safeBlue.getValue(true), safeAlpha.getValue(true)), customOutline.getValue(true), new Color(safecRed.getValue(true), safecGreen.getValue(true), safecBlue.getValue(true), safecAlpha.getValue(true)), lineWidth.getValue(true).floatValue(), outline.getValue(true), box.getValue(true), boxAlpha.getValue(true), true, height.getValue(true), gradientBox.getValue(true), gradientOutline.getValue(true), invertGradientBox.getValue(true), invertGradientOutline.getValue(true), currentAlpha);
                        continue;
                    }
                    if (!BlockUtil.isBlockUnSafe(HoleESP.mc.world.getBlockState(pos.down()).getBlock()) || !BlockUtil.isBlockUnSafe(HoleESP.mc.world.getBlockState(pos.east()).getBlock()) || !BlockUtil.isBlockUnSafe(HoleESP.mc.world.getBlockState(pos.west()).getBlock()) || !BlockUtil.isBlockUnSafe(HoleESP.mc.world.getBlockState(pos.south()).getBlock()) || !BlockUtil.isBlockUnSafe(HoleESP.mc.world.getBlockState(pos.north()).getBlock()))
                        continue;
                    RenderUtil.drawBoxESP(pos, rainbow.getValue(true) ? ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue(true)) : new Color(red.getValue(true), green.getValue(true), blue.getValue(true), alpha.getValue(true)), customOutline.getValue(true), new Color(cRed.getValue(true), cGreen.getValue(true), cBlue.getValue(true), cAlpha.getValue(true)), lineWidth.getValue(true).floatValue(), outline.getValue(true), box.getValue(true), boxAlpha.getValue(true), true, height.getValue(true), gradientBox.getValue(true), gradientOutline.getValue(true), invertGradientBox.getValue(true), invertGradientOutline.getValue(true), currentAlpha);
                }
            }
        }
    }
}
