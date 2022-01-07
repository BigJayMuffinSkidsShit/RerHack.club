package club.shrimphack.swag.features.modules.movements;

import club.shrimphack.swag.features.setting.Setting;
import club.shrimphack.swag.features.modules.Module;
import java.util.Iterator;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;

public class Step extends Module
{
    public Setting<Mode> mode;

    public Step() {
        super("Step", "Allows you to step up blocks", Category.MOVEMENT, true, false, false);
        this.mode = (Setting<Mode>)this.register(new Setting("Mode", Mode.Wp2));
    }

    @Override
    public void onUpdate() {
        if (this.mode.getValue() == Mode.Vanilla) {
            Step.mc.player.stepHeight = 2.0f;
        }
        else {
            Step.mc.player.stepHeight = 0.5f;
        }
        if (this.mode.getValue() == Mode.Vanilla) {
            return;
        }
        if (!Step.mc.player.collidedHorizontally) {
            return;
        }
        if (!Step.mc.player.onGround || Step.mc.player.isOnLadder() || Step.mc.player.isInWater() || Step.mc.player.isInLava() || Step.mc.player.movementInput.jump || Step.mc.player.noClip) {
            return;
        }
        if (Step.mc.player.moveForward == 0.0f && Step.mc.player.moveStrafing == 0.0f) {
            return;
        }
        final double n = this.get_n_normal();
        if (n < 0.0 || n > 2.0) {
            return;
        }
        if (n == 2.0) {
            if (this.mode.getValue() == Mode.Wp2) {
                Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + 0.42, Step.mc.player.posZ, Step.mc.player.onGround));
                Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + 0.78, Step.mc.player.posZ, Step.mc.player.onGround));
                Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + 0.63, Step.mc.player.posZ, Step.mc.player.onGround));
                Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + 0.51, Step.mc.player.posZ, Step.mc.player.onGround));
                Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + 0.9, Step.mc.player.posZ, Step.mc.player.onGround));
                Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + 1.21, Step.mc.player.posZ, Step.mc.player.onGround));
                Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + 1.45, Step.mc.player.posZ, Step.mc.player.onGround));
                Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + 1.43, Step.mc.player.posZ, Step.mc.player.onGround));
            }
            else if (this.mode.getValue() == Mode.Phobos) {
                Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + 0.425, Step.mc.player.posZ, Step.mc.player.onGround));
                Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + 0.821, Step.mc.player.posZ, Step.mc.player.onGround));
                Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + 0.699, Step.mc.player.posZ, Step.mc.player.onGround));
                Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + 0.599, Step.mc.player.posZ, Step.mc.player.onGround));
                Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + 1.022, Step.mc.player.posZ, Step.mc.player.onGround));
                Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + 1.372, Step.mc.player.posZ, Step.mc.player.onGround));
                Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + 1.652, Step.mc.player.posZ, Step.mc.player.onGround));
                Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + 1.869, Step.mc.player.posZ, Step.mc.player.onGround));
            }
            Step.mc.player.setPosition(Step.mc.player.posX, Step.mc.player.posY + 2.0, Step.mc.player.posZ);
        }
        if (n == 1.5) {
            Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + 0.41999998688698, Step.mc.player.posZ, Step.mc.player.onGround));
            Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + 0.7531999805212, Step.mc.player.posZ, Step.mc.player.onGround));
            Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + 1.00133597911214, Step.mc.player.posZ, Step.mc.player.onGround));
            Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + 1.16610926093821, Step.mc.player.posZ, Step.mc.player.onGround));
            Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + 1.24918707874468, Step.mc.player.posZ, Step.mc.player.onGround));
            Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + 1.1707870772188, Step.mc.player.posZ, Step.mc.player.onGround));
            Step.mc.player.setPosition(Step.mc.player.posX, Step.mc.player.posY + 1.0, Step.mc.player.posZ);
        }
        if (n == 1.0) {
            Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + 0.41999998688698, Step.mc.player.posZ, Step.mc.player.onGround));
            Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + 0.7531999805212, Step.mc.player.posZ, Step.mc.player.onGround));
            Step.mc.player.setPosition(Step.mc.player.posX, Step.mc.player.posY + 1.0, Step.mc.player.posZ);
        }
    }

    public double get_n_normal() {
        Step.mc.player.stepHeight = 0.5f;
        double max_y = -1.0;
        final AxisAlignedBB grow = Step.mc.player.getEntityBoundingBox().offset(0.0, 0.05, 0.0).grow(0.05);
        if (!Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, grow.offset(0.0, 2.0, 0.0)).isEmpty()) {
            return 100.0;
        }
        for (final AxisAlignedBB aabb : Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, grow)) {
            if (aabb.maxY > max_y) {
                max_y = aabb.maxY;
            }
        }
        return max_y - Step.mc.player.posY;
    }

    @Override
    public void onDisable() {
        Step.mc.player.stepHeight = 0.5f;
    }

    public enum Mode
    {
        Wp2,
        Phobos,
        Vanilla;
    }
}

