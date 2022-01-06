package club.shrimphack.swag.features.modules.misc;

import club.shrimphack.swag.RerHack;
import club.shrimphack.swag.features.command.Command;
import club.shrimphack.swag.features.gui.OyVeyGui;
import club.shrimphack.swag.features.modules.Module;
import club.shrimphack.swag.features.setting.Bind;
import club.shrimphack.swag.features.setting.Setting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class MiddleClickFriend extends Module {

    private final Setting<Boolean> middleClick;
    private final Setting<Boolean> keyboard;
    private final Setting<Bind> key;
    private boolean clicked;

    public MiddleClickFriend() {
        super("MiddleClickFriend", "Middle click to add friends", Category.MISC, true, false, false);
        this.middleClick = (Setting<Boolean>)this.register(new Setting("MiddleClick", true));
        this.keyboard = (Setting<Boolean>)this.register(new Setting("Keyboard", false));
        this.key = (Setting<Bind>)this.register(new Setting("KeyBind", new Bind(-1), v -> this.keyboard.getValue()));
        this.clicked = false;
    }


    @Override
    public void onUpdate() {
        if (Mouse.isButtonDown(2)) {
            if (!this.clicked && this.middleClick.getValue() && MiddleClickFriend.mc.currentScreen == null) {
                this.onClick();
            }
            this.clicked = true;
        }
        else {
            this.clicked = false;
        }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onKeyInput(final InputEvent.KeyInputEvent event) {
        if (this.keyboard.getValue() && Keyboard.getEventKeyState() && !(MiddleClickFriend.mc.currentScreen instanceof OyVeyGui) && this.key.getValue().getKey() == Keyboard.getEventKey()) {
            this.onClick();
        }
    }

    private void onClick() {
        final RayTraceResult result = MiddleClickFriend.mc.objectMouseOver;
        if (result != null && result.typeOfHit == RayTraceResult.Type.ENTITY) {
            final Entity entity = result.entityHit;
            if (entity instanceof EntityPlayer) {
                if (RerHack.friendManager.isFriend(entity.getName())) {
                    RerHack.friendManager.removeFriend(entity.getName());
                    Command.sendMessage("§c" + entity.getName() + "§r" + " unfriended.");
                }
                else {
                    RerHack.friendManager.addFriend(entity.getName());
                    Command.sendMessage("§b" + entity.getName() + "§r" + " friended.");
                }
            }
        }
        this.clicked = true;
    }
}
