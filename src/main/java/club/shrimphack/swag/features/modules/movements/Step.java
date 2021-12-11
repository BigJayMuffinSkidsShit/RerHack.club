package club.shrimphack.swag.features.modules.movements;

import club.shrimphack.swag.features.modules.Module;
import club.shrimphack.swag.features.setting.Setting;

public class Step extends Module {
    public Step() {
        super("Step", "makes u sprint", Category.MOVEMENT, true, false, false);
    }

    public Setting<Float> height = this.register(new Setting<Float>("Height",2.0f,0.1f,2.5f));

    public void onUpdate() {
        mc.player.stepHeight = this.height.getValue();
    }

    public void onDisable() {
        mc.player.stepHeight = 0.5f;
    }
}
