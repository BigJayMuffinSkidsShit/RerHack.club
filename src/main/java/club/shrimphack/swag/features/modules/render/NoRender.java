package club.shrimphack.swag.features.modules.render;

import club.shrimphack.swag.event.events.PacketEvent;
import club.shrimphack.swag.features.modules.Module;
import club.shrimphack.swag.features.setting.Setting;
import club.shrimphack.swag.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.*;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;

public class NoRender extends Module {

    private final Setting<Boolean> mob = register(new Setting<Boolean>("Mob", false));
    private final Setting<Boolean> particles = register(new Setting<Boolean>("Particles", false));
    private final Setting<Boolean> explosions = register(new Setting<Boolean>("Explosions", false));
    private final Setting<Boolean> xp = register(new Setting<Boolean>("Xp", false));


    public NoRender() {

        super("NoRender", "norender", Module.Category.RENDER, true, false, false);

    }


    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive event) {
        Packet packet = event.getPacket();
        if ((packet instanceof SPacketSpawnMob && mob.getValue()) ||
                (packet instanceof SPacketSpawnExperienceOrb && xp.getValue()) ||
                (packet instanceof SPacketExplosion && explosions.getValue()) ||
                (packet instanceof SPacketParticles && particles.getValue()))
        {
            event.setCanceled(true);
        }

    }

}
