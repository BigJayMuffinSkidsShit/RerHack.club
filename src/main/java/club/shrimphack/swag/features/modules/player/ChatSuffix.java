package club.shrimphack.swag.features.modules.player;

import club.shrimphack.swag.RerHack;
import club.shrimphack.swag.features.modules.Module;
import club.shrimphack.swag.features.setting.Setting;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;

public class ChatSuffix extends Module{

    private final Setting<Boolean> strict = register(new Setting <Boolean>("Strict", true));
    private final Setting<Boolean> ShowVersion = register(new Setting <Boolean>("Version", false));
    private final Setting<String> custom = register(new Setting <String>("Custom Name", "rerhack.club"));
    private final Setting<String> version = register(new Setting <String>("Custom Version", "v1.1.1.1.1.1.1.1"));

    public ChatSuffix(){
        super("ChatSuffix", "Modifies your chat to make cool :)", Category.PLAYER, true, false, false);
    }

    @SubscribeEvent
    public void chat(final ClientChatEvent event) {
        if(isEnabled()) {
            for (final String s : Arrays.asList("/", ".", "-", ",", ":", ";", "'", "\"", "+", "\\", "@")) {
                if (event.getMessage().startsWith(s)) return;
            }
            if (strict.getValue()) {
                event.setMessage(event.getMessage() + " : " + this.custom.getValue());
            }
            if (ShowVersion.getValue()) {
                event.setMessage(event.getMessage() + " " + "\u23D0" + toUnicode(" " + this.custom.getValue() + " v" + this.version.getValue()));
            }
            else  event.setMessage(event.getMessage() + " " + "\u23D0" + toUnicode(" " + this.custom.getValue()));
        }
    }


    public String toUnicode(String s) {
        return s.toLowerCase()
                .replace("a", "\u1d00")
                .replace("b", "\u0299")
                .replace("c", "\u1d04")
                .replace("d", "\u1d05")
                .replace("e", "\u1d07")
                .replace("f", "\ua730")
                .replace("g", "\u0262")
                .replace("h", "\u029c")
                .replace("i", "\u026a")
                .replace("j", "\u1d0a")
                .replace("k", "\u1d0b")
                .replace("l", "\u029f")
                .replace("m", "\u1d0d")
                .replace("n", "\u0274")
                .replace("o", "\u1d0f")
                .replace("p", "\u1d18")
                .replace("q", "\u01eb")
                .replace("r", "\u0280")
                .replace("s", "\ua731")
                .replace("t", "\u1d1b")
                .replace("u", "\u1d1c")
                .replace("v", "\u1d20")
                .replace("w", "\u1d21")
                .replace("x", "\u02e3")
                .replace("y", "\u028f")
                .replace("z", "\u1d22");
    }

    public void onEnable()  { super.onEnable();}

    public void onDisable() { super.onDisable(); }

}
