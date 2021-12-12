package club.shrimphack.swag.features.gui.components.items.buttons;

import club.shrimphack.swag.features.gui.OyVeyGui;
import club.shrimphack.swag.features.modules.client.ClickGui;
import club.shrimphack.swag.features.setting.Setting;
import club.shrimphack.swag.util.RenderUtil;
import club.shrimphack.swag.util.Util;
import com.mojang.realmsclient.gui.ChatFormatting;
import club.shrimphack.swag.RerHack;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;

public class UnlimitedSlider
        extends Button {
    public Setting setting;

    public UnlimitedSlider(Setting setting) {
        super(setting.getName());
        this.setting = setting;
        this.width = 15;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        RenderUtil.drawRect(this.x, this.y, this.x + (float) this.width + 7.4f, this.y + (float) this.height - 0.5f, !this.isHovering(mouseX, mouseY) ? RerHack.colorManager.getColorWithAlpha(RerHack.moduleManager.getModuleByClass(ClickGui.class).hoverAlpha.getValue(true)) : RerHack.colorManager.getColorWithAlpha(RerHack.moduleManager.getModuleByClass(ClickGui.class).alpha.getValue(true)));
        RerHack.textManager.drawStringWithShadow(" - " + this.setting.getName() + " " + ChatFormatting.GRAY + this.setting.getValue(true) + ChatFormatting.WHITE + " +", this.x + 2.3f, this.y - 1.7f - (float) OyVeyGui.getClickGui().getTextOffset(), this.getState() ? -1 : -5592406);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (this.isHovering(mouseX, mouseY)) {
            Util.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
            if (this.isRight(mouseX)) {
                if (this.setting.getValue(true) instanceof Double) {
                    this.setting.setValue((Double) this.setting.getValue(true) + 1.0);
                } else if (this.setting.getValue(true) instanceof Float) {
                    this.setting.setValue(Float.valueOf(((Float) this.setting.getValue(true)).floatValue() + 1.0f));
                } else if (this.setting.getValue(true) instanceof Integer) {
                    this.setting.setValue((Integer) this.setting.getValue(true) + 1);
                }
            } else if (this.setting.getValue(true) instanceof Double) {
                this.setting.setValue((Double) this.setting.getValue(true) - 1.0);
            } else if (this.setting.getValue(true) instanceof Float) {
                this.setting.setValue(Float.valueOf(((Float) this.setting.getValue(true)).floatValue() - 1.0f));
            } else if (this.setting.getValue(true) instanceof Integer) {
                this.setting.setValue((Integer) this.setting.getValue(true) - 1);
            }
        }
    }

    @Override
    public void update() {
        this.setHidden(!this.setting.isVisible());
    }

    @Override
    public int getHeight() {
        return 14;
    }

    @Override
    public void toggle() {
    }

    @Override
    public boolean getState() {
        return true;
    }

    public boolean isRight(int x) {
        return (float) x > this.x + ((float) this.width + 7.4f) / 2.0f;
    }
}

