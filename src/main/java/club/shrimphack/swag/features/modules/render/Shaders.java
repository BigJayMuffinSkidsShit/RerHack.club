package club.shrimphack.swag.features.modules.render;

import club.shrimphack.swag.features.modules.Module;
import club.shrimphack.swag.features.setting.Setting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.renderer.OpenGlHelper;

public class Shaders extends Module
{
    private static final Shaders INSTANCE;
    public Setting<Mode> shader;

    public Shaders() {
        super("Shaders", "Cool.", Category.RENDER, false, false, false);
        this.shader = (Setting<Mode>)this.register(new Setting("Mode", Mode.green));
    }

    @Override
    public void onUpdate() {
        if (OpenGlHelper.shadersSupported && Shaders.mc.getRenderViewEntity() instanceof EntityPlayer) {
            if (Shaders.mc.entityRenderer.getShaderGroup() != null) {
                Shaders.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
            }
            try {
                Shaders.mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/" + this.shader.getValue() + ".json"));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (Shaders.mc.entityRenderer.getShaderGroup() != null && Shaders.mc.currentScreen == null) {
            Shaders.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
        }
    }

    @Override
    public String getDisplayInfo() {
        return this.shader.currentEnumName();
    }

    @Override
    public void onDisable() {
        if (Shaders.mc.entityRenderer.getShaderGroup() != null) {
            Shaders.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
        }
    }

    static {
        INSTANCE = new Shaders();
    }

    public enum Mode
    {
        notch,
        antialias,
        art,
        bits,
        blobs,
        blobs2,
        blur,
        bumpy,
        color_convolve,
        creeper,
        deconverge,
        desaturate,
        flip,
        fxaa,
        green,
        invert,
        ntsc,
        pencil,
        phosphor,
        sobel,
        spider,
        wobble;
    }
}