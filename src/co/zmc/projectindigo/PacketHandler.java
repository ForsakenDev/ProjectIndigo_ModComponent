package co.zmc.projectindigo;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler {

    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
        if (packet.channel.equals("projectindigo") && new String(packet.data).equalsIgnoreCase("request_modpack_url")) {
            Packet250CustomPayload newPacket = new Packet250CustomPayload();
            newPacket.channel = "projectindigo";
            newPacket.length = getData().trim().getBytes().length;
            newPacket.data = getData().trim().getBytes();
            manager.addToSendQueue(newPacket);
        }
    }

    private String getData() {
        return "{" + "\"name\": \"" + Main._instance.getServerName() + "\"," + "\"ip\": \"" + Main._instance.getServerIP()
                + "\"," + "\"port\": \"" + MinecraftServer.getServer().getPort() + "\"," + "\"logo\": \""
                + Main._instance.getLogoURL() + "\"," + "\"download_url\": \"" + Main._instance.getDownloadURL() + "\","
                + "\"version\": \"" + Main._instance.getVersion() + "\"," + "\"mc_version\": \""
                + MinecraftServer.getServer().getMinecraftVersion() + "\"" + "}";
    }
}