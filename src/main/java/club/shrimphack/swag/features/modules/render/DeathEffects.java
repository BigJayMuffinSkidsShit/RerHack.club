package club.shrimphack.swag.features.modules.render;

import club.shrimphack.swag.features.modules.Module;
import club.shrimphack.swag.features.setting.Setting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DeathEffects extends Module {
    private final Setting<Boolean> lightning = this.register(new Setting("Lightning", false));
    private final Setting<Boolean> totempop = this.register(new Setting("TotemPop", false));
    private final Setting<Boolean> dragonBreath = this.register(new Setting("DragonBreath", false));
    private final Setting<Integer> timeactive = this.register(new Setting("TimeActive", 50, 25, 50));
    public DeathEffects() {
        super("DeathEffects", "Shows death effects and shit.", Module.Category.RENDER, false, false, false);
    }
    @SubscribeEvent
    public void onDeath(final LivingDeathEvent event) {
        if (this.lightning.getDefaultValue()) {
            if (event.getEntity() == this.mc.player) {
                return;
            }
            this.mc.world.addEntityToWorld(-999, (Entity) new EntityLightningBolt((World) this.mc.world, event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, true));
        }
        if (this.totempop.getValue()) {
            this.totempop(event.getEntity());
        }
        if (this.dragonBreath.getValue()) {
            this.mc.effectRenderer.emitParticleAtEntity(event.getEntity(), EnumParticleTypes.DRAGON_BREATH);
        }
    }
    public void totempop(final Entity entity) {
        this.mc.effectRenderer.emitParticleAtEntity(entity, EnumParticleTypes.TOTEM, (int) this.timeactive.getValue());
        this.mc.world.playSound(entity.posX, entity.posY, entity.posZ, SoundEvents.ITEM_TOTEM_USE, entity.getSoundCategory(), 1.0f, 1.0f, false);
    }
}