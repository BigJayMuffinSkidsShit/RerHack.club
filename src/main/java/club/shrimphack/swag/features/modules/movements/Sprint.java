package club.shrimphack.swag.features.modules.movements;

import club.shrimphack.swag.features.modules.Module;

public class Sprint extends Module {
    public Sprint() {
        super("Sprint", "sprints", Category.MOVEMENT, true, false, false);
    }

    public void onUpdate() {
        mc.player.setSprinting(true);
    }

    public void onDisable() {
        mc.player.setSprinting(false);
    }
}
