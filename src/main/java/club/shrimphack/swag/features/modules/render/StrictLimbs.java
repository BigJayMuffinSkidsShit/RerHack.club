package club.shrimphack.swag.features.modules.render;

import club.shrimphack.swag.features.modules.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class StrictLimbs extends Module {
    public StrictLimbs() {
        super("StrictLimbs", "Doesn't render player animations.", Module.Category.RENDER, false, false, false);
    }
    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        if(nullCheck()) return;
        for(Entity entity : mc.world.loadedEntityList) {
            if(!(entity instanceof EntityCreature)) return;
            ((EntityCreature) entity).prevLimbSwingAmount = 0;
            ((EntityCreature) entity).limbSwingAmount = 0;
            ((EntityCreature) entity).limbSwing = 0;
        }
    }
}
