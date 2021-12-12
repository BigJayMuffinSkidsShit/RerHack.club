package club.shrimphack.swag;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;

public class DiscordPresence {
    private static final DiscordRPC rpc;
    public static DiscordRichPresence presence;
    private static Thread thread;
    private static int index;
    private static club.shrimphack.swag.features.modules.misc.RPC RPC;

    static {
        index = 1;
        rpc = DiscordRPC.INSTANCE;
        presence = new DiscordRichPresence();
    }

    public static void start() {
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        rpc.Discord_Initialize("918978892440666132", handlers, true, "");
        DiscordPresence.presence.startTimestamp = System.currentTimeMillis() / 1000L;
        DiscordPresence.presence.details = Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu ? "In the main menu." : "Playing " + (Minecraft.getMinecraft().getCurrentServerData() != null ? (RPC.INSTANCE.ip.getValue().booleanValue() ? "on " + Minecraft.getMinecraft().getCurrentServerData().serverIP + "." : " multiplayer.") : " singleplayer.");
        DiscordPresence.presence.state = "bigjmuffin is hot";
        DiscordPresence.presence.largeImageKey = "rpclogo";
        DiscordPresence.presence.largeImageText = "https://discord.gg/QHJ76ynraT";
        rpc.Discord_UpdatePresence(presence);
        thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                rpc.Discord_RunCallbacks();
                DiscordPresence.presence.details = Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu ? "In the main menu." : "Playing " + (Minecraft.getMinecraft().getCurrentServerData() != null ? (RPC.INSTANCE.ip.getValue().booleanValue() ? "on " + Minecraft.getMinecraft().getCurrentServerData().serverIP + "." : " multiplayer.") : " singleplayer.");
                rpc.Discord_UpdatePresence(presence);
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException interruptedException) {
                }
            }
        }, "RPC-Callback-Handler");
        thread.start();
    }

    public static void stop() {
        if (thread != null && !thread.isInterrupted()) {
            thread.interrupt();
        }
        rpc.Discord_Shutdown();
    }

}
