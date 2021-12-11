package club.shrimphack.swag.features.modules.render;

import club.shrimphack.swag.event.events.PacketEvent;
import club.shrimphack.swag.features.modules.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class NoRender extends Module {
    public NoRender() {
        super("NoRender", "Shows safe spots.", Module.Category.RENDER, false, false, false);
    }

    @SubscribeEvent
    public void OnPacketRecive(PacketEvent.Receive event)
    {
        System.out.println("asd");
    }
}
