package co.zmc.projectindigo;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "ProjectIndigo", name = "ProjectIndigo", version = "@MOD_VERSION@", acceptedMinecraftVersions = "[@MC_VERSION@]")
@NetworkMod(clientSideRequired = false, serverSideRequired = false, channels = { "projectindigo" }, packetHandler = PacketHandler.class)
public class Main {
    @Mod.Instance
    public static Main _instance;
    private String     _serverName;
    private String     _serverIP;
    private String     _logoURL;
    private String     _packHost;
    private String     _packName;
    private String     _packLocation;
    private String     _packVersion;

    public String getServerName() {
        return _serverName;
    }

    public String getServerIP() {
        return _serverIP;
    }

    public String getLogoURL() {
        return _logoURL;
    }

    public String getPackHost() {
        return _packHost;
    }

    public String getPackName() {
        return _packName;
    }

    public String getPackLocation() {
        return _packLocation;
    }

    public String getPackVersion() {
        return _packVersion;
    }

    @Mod.PreInit
    public void preInit(FMLPreInitializationEvent event) {
        _instance = this;

        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        _serverName = config.get("Server", "name", "Example's Server", "Localized name for your server").getString();
        _serverIP = config.get("Server", "ip", "example.com", "URL/IP for your server, that you want users to connect to")
                .getString();
        _logoURL = config.get("Server", "logo", "http://example.com/example.png",
                "Image you want to represent your server (Max size: 250px X 250px)").getString();
        _packHost = config.get("Modpack", "host", "FTB", "The type of server this is (options: FTB/Technic/Custom)").getString();
        _packName = config.get("Modpack", "name", "Unleashed", "Name of the modpack (FTB modpacks: (Unleashed)").getString();
        _packLocation = config.get("Modpack", "url", "http://example.com/modpack.zip",
                "Link to download the modpack (only needed for custom packs)").getString();
        _packVersion = config.get("Modpack", "version", "1.0.0", "Version of the modpack").getString();
        config.save();
    }

    @Mod.Init
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.ServerStarting
    public void serverStarting(FMLServerStartingEvent event) {

    }

}
