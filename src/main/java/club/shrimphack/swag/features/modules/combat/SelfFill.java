package club.shrimphack.swag.features.modules.combat;

import club.shrimphack.swag.features.command.Command;
import club.shrimphack.swag.features.modules.Module;
import club.shrimphack.swag.features.setting.Setting;
import club.shrimphack.swag.manager.FriendManager;
import club.shrimphack.swag.util.BurrowUtil;
import club.shrimphack.swag.util.MovementUtils;
import net.minecraft.block.BlockObsidian;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public
class SelfFill extends Module {
    private static SelfFill INSTANCE;

    public SelfFill() {
        super("SelfFill", "burrows u into a block", Category.COMBAT, true,false, false);
    }
    private BlockPos originalPos;
    private int oldSlot;
    private FriendManager friendManager;

    public Setting<Float> offset = this.register(new Setting("Offset",2.0f,-5.0f,5.0f));
    public Setting<Boolean> rotate = this.register(new Setting("Rotate",false));
    public Setting<Boolean> smart = this.register(new Setting("Smart", true));
    public Setting<Float> smartDistance = this.register(new Setting("Distance",2.5f,1.0f,10.0f));

    public void onEnable() {
        super.onEnable();
        this.originalPos = new BlockPos(SelfFill.mc.player.posX, SelfFill.mc.player.posY, SelfFill.mc.player.posZ);
        if (SelfFill.mc.world.getBlockState(new BlockPos(SelfFill.mc.player.posX, SelfFill.mc.player.posY, SelfFill.mc.player.posZ)).getBlock().equals(Blocks.SPRUCE_FENCE) || this.intersectsWithEntity(this.originalPos)) {
            this.toggle();
            return;
        }
        this.oldSlot = SelfFill.mc.player.inventory.currentItem;
    }

    @SubscribeEvent
    public int onUpdate(TickEvent event) {
        if (this.smart.getValue() && MovementUtils.isInHole((Entity)SelfFill.mc.player)) {
            SelfFill.mc.world.loadedEntityList.stream().filter(e -> e instanceof EntityPlayer).filter(e -> e != SelfFill.mc.player).forEach(e -> {
                if ((friendManager.isFriend(getName()))) {
                    return;
                }
                else {
                    if (e.getDistance((Entity)SelfFill.mc.player) + 0.22f <= this.smartDistance.getValue()) {
                        this.doShit();
                    }
                    return;
                }
            });
        }
        else {
            this.doShit();
        }
        return 0;
    }

    public void doShit() {
        if (BurrowUtil.findHotbarBlock(BlockObsidian.class) == -1) {
            Command.sendMessage("No obsidian in ur hotbar!");
            this.toggle();
            return;
        }
        BurrowUtil.switchToSlot(BurrowUtil.findHotbarBlock(BlockObsidian.class));
        SelfFill.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(SelfFill.mc.player.posX, SelfFill.mc.player.posY + 0.41999998688698, SelfFill.mc.player.posZ, true));
        SelfFill.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(SelfFill.mc.player.posX, SelfFill.mc.player.posY + 0.7531999805211997, SelfFill.mc.player.posZ, true));
        SelfFill.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(SelfFill.mc.player.posX, SelfFill.mc.player.posY + 1.00133597911214, SelfFill.mc.player.posZ, true));
        SelfFill.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(SelfFill.mc.player.posX, SelfFill.mc.player.posY + 1.16610926093821, SelfFill.mc.player.posZ, true));
        BurrowUtil.placeBlock(this.originalPos, EnumHand.MAIN_HAND, this.rotate.getValue(), true, false);
        SelfFill.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(SelfFill.mc.player.posX, SelfFill.mc.player.posY + this.offset.getValue(), SelfFill.mc.player.posZ, false));
        BurrowUtil.switchToSlot(this.oldSlot);
        this.toggle();
    }

    private boolean intersectsWithEntity(final BlockPos pos) {
        for (final Entity entity : SelfFill.mc.world.loadedEntityList) {
            if (entity.equals((Object)SelfFill.mc.player)) {
                continue;
            }
            if (entity instanceof EntityItem) {
                continue;
            }
            if (new AxisAlignedBB(pos).intersects(entity.getEntityBoundingBox())) {
                return true;
            }
        }
        return false;
    }
}