package club.shrimphack.swag;

import club.shrimphack.swag.features.gui.OyVeyGui;
import club.shrimphack.swag.manager.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

@Mod(modid = "shrimphack", name = "RerHack.club", version = "0.3")
public class RerHack {
    public static final String MODID = "shrimphack";
    public static final String MODNAME = "RerHack.club";
    public static final String MODVER = "0.3";
    public static final Logger LOGGER = LogManager.getLogger("RerHack.club");
    public static CommandManager commandManager;
    public static FriendManager friendManager;
    public static ModuleManager moduleManager;
    public static PacketManager packetManager;
    public static ColorManager colorManager;
    public static HoleManager holeManager;
    public static InventoryManager inventoryManager;
    public static PotionManager potionManager;
    public static RotationManager rotationManager;
    public static PositionManager positionManager;
    public static SpeedManager speedManager;
    public static ReloadManager reloadManager;
    public static FileManager fileManager;
    public static ConfigManager configManager;
    public static ServerManager serverManager;
    public static EventManager eventManager;
    public static TextManager textManager;
    public static OyVeyGui gui;
    public static DiscordPresence discordPresence;
    @Mod.Instance
    public static RerHack INSTANCE;
    private static boolean unloaded;

    static {
        unloaded = false;
    }

    public static void load() {
        LOGGER.info("\n\nLoading RerHack.club");
        unloaded = false;
        if (reloadManager != null) {
            reloadManager.unload();
            reloadManager = null;
        }
        textManager = new TextManager();
        commandManager = new CommandManager();
        friendManager = new FriendManager();
        moduleManager = new ModuleManager();
        rotationManager = new RotationManager();
        packetManager = new PacketManager();
        eventManager = new EventManager();
        speedManager = new SpeedManager();
        potionManager = new PotionManager();
        inventoryManager = new InventoryManager();
        serverManager = new ServerManager();
        fileManager = new FileManager();
        colorManager = new ColorManager();
        positionManager = new PositionManager();
        configManager = new ConfigManager();
        holeManager = new HoleManager();
        discordPresence = new DiscordPresence();
        gui = new OyVeyGui();
        LOGGER.info("Managers loaded.");
        moduleManager.init();
        LOGGER.info("Modules loaded.");
        configManager.init();
        eventManager.init();
        LOGGER.info("EventManager loaded.");
        textManager.init(true);
        moduleManager.onLoad();
        LOGGER.info("RerHack.club successfully loaded!\n");
    }

    public static void unload(boolean unload) {
        LOGGER.info("\n\nUnloading RerHack.club");
        if (unload) {
            reloadManager = new ReloadManager();
            reloadManager.init(commandManager != null ? commandManager.getPrefix() : ".");
        }
        RerHack.onUnload();
        eventManager = null;
        friendManager = null;
        speedManager = null;
        holeManager = null;
        positionManager = null;
        rotationManager = null;
        configManager = null;
        commandManager = null;
        colorManager = null;
        serverManager = null;
        fileManager = null;
        potionManager = null;
        inventoryManager = null;
        moduleManager = null;
        textManager = null;
        LOGGER.info("RerHack.club unloaded!\n");
    }

    public static void reload() {
        RerHack.unload(false);
        RerHack.load();
    }

    public static void onUnload() {
        if (!unloaded) {
            eventManager.onUnload();
            moduleManager.onUnload();
            configManager.saveConfig(RerHack.configManager.config.replaceFirst("oyvey/", ""));
            moduleManager.onUnloadPost();
            unloaded = true;
        }
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");LOGGER.info("a");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Display.setTitle("RerHack.club v0.3");
        RerHack.load();
        DiscordPresence.start();
    }
}

