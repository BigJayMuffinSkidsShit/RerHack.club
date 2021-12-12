package club.shrimphack.swag.features.modules.combat;

import club.shrimphack.swag.features.modules.Module;
import club.shrimphack.swag.features.setting.Setting;
import club.shrimphack.swag.util.TrapUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AutoTrap extends Module {
    private final Setting<Integer> blocksPerTick = this.register(new Setting("TimeActive", 1, 1, 10));
    private final Setting<Boolean> disable = this.register(new Setting("Disable", true));
    private boolean finished;
    private final List<Vec3d> positions = new ArrayList<>(Arrays.asList(
            new Vec3d(0, -1, -1),
            new Vec3d(1, -1, 0),
            new Vec3d(0, -1, 1),
            new Vec3d(-1, -1, 0),
            new Vec3d(0, 0, -1),
            new Vec3d(1, 0, 0),
            new Vec3d(0, 0, 1),
            new Vec3d(-1, 0, 0),
            new Vec3d(0, 1, -1),
            new Vec3d(1, 1, 0),
            new Vec3d(0, 1, 1),
            new Vec3d(-1, 1, 0),
            new Vec3d(0, 2, -1),
            new Vec3d(0, 2, 1),
            new Vec3d(0, 2, 0)));
    public AutoTrap() {
        super("AutoTrap", "traps ur enemies >:D", Category.COMBAT, false, false, false);
    }
    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event)
    {
        if (nullCheck()) return;

        if (finished && disable.getValue(true)) disable();

        int blocksPlaced = 0;

        for (Vec3d position : positions)
        {
            EntityPlayer closestPlayer = getClosestPlayer();
            if (closestPlayer != null)
            {
                BlockPos pos = new BlockPos(position.add(getClosestPlayer().getPositionVector()));

                if (mc.world.getBlockState(pos).getBlock().equals(Blocks.AIR))
                {
                    int oldSlot = mc.player.inventory.currentItem;
                    mc.player.inventory.currentItem = TrapUtil.getSlot(Blocks.OBSIDIAN);
                    TrapUtil.placeBlock(pos);
                    mc.player.inventory.currentItem = oldSlot;
                    blocksPlaced++;

                    if (blocksPlaced == blocksPerTick.getValue(true)) return;
                }
            }
        }
        if (blocksPlaced == 0) finished = true;
    }

    private EntityPlayer getClosestPlayer()
    {
        EntityPlayer closestPlayer = null;
        double range = 1000;
        for (EntityPlayer playerEntity : mc.world.playerEntities)
        {
            if (!playerEntity.equals(mc.player))
            {
                double distance = mc.player.getDistance(playerEntity);
                if (distance < range)
                {
                    closestPlayer = playerEntity;
                    range = distance;
                }
            }
        }
        return closestPlayer;
    }
}
