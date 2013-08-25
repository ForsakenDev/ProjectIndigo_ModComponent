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
    private String     _serverInformationURL;

    public String getServerInformationURL() {
        return _serverInformationURL;
    }

    @Mod.PreInit
    public void preInit(FMLPreInitializationEvent event) {
        _instance = this;

        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        String GENERAL = Configuration.CATEGORY_GENERAL;
        _serverInformationURL = config.get(GENERAL, "server_information_url", "http://example.com/server_info.json",
                "Link to the server's JSON information page").getString();
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
