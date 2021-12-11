package club.shrimphack.swag.features.modules.movements;

import club.shrimphack.swag.features.modules.Module;
import club.shrimphack.swag.util.MathUtil;

public class Strafe extends Module {
    public Strafe() {
        super("Strafe", "run faster", Category.MOVEMENT, true, false, false);
    }

    int delay = 0;

    @Override
    public void onUpdate() {
        Strafe.mc.player.setSprinting(true);
        ++this.delay;
        Strafe.mc.player.motionY *= 0.985;
        if (Strafe.mc.player.onGround) {
            if (Strafe.mc.gameSettings.keyBindForward.isKeyDown() || Strafe.mc.gameSettings.keyBindBack.isKeyDown() || Strafe.mc.gameSettings.keyBindLeft.isKeyDown() || Strafe.mc.gameSettings.keyBindRight.isKeyDown()) {
                Strafe.mc.player.jump();
                double[] dir = MathUtil.directionSpeed(0.6);
                Strafe.mc.player.motionX = dir[0];
                Strafe.mc.player.motionZ = dir[1];
            }
        } else {
            double[] dir = MathUtil.directionSpeed(0.26);
            Strafe.mc.player.motionX = dir[0];
            Strafe.mc.player.motionZ = dir[1];
        }
    }
}
