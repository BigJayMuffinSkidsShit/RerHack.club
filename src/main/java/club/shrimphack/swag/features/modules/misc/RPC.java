package club.shrimphack.swag.features.modules.misc;

import club.shrimphack.swag.features.modules.Module;
import club.shrimphack.swag.features.setting.Setting;
import club.shrimphack.swag.DiscordPresence;

public class RPC extends Module {
    public RPC() {
        super("DiscordRPC", "shows what ur playing on discord", Category.MISC, true, false, false);
    }
    public RPC INSTANCE = this;
    public Setting<Boolean> ip = this.register(new Setting("IP",false));

    public RPC getINSTANCE() {
        return INSTANCE;
    }

    public void setINSTANCE(RPC INSTANCE) {
        this.INSTANCE = INSTANCE;
    }

    public void onEnable() {
        DiscordPresence.start();
    }

    public void onDisable() {
        DiscordPresence.stop();
    }
}
