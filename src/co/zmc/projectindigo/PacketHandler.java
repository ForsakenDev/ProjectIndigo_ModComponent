package co.zmc.projectindigo;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler {

    private String getData() {
        String data = "{ \"name\": \"" + Main._instance.getServerName() + "\",";
        data += "\"ip\": \"" + Main._instance.getServerIP() + "\",";
        data += "\"port\": \"" + MinecraftServer.getServer().getPort() + "\",";
        data += "\"logo\": \"" + Main._instance.getLogoURL() + "\",";
        data += "\"download_url\": \"" + Main._instance.getDownloadURL() + "\",";
        data += "\"version\": \"" + Main._instance.getVersion() + "\",";
        data += "\"mc_version\": \"" + MinecraftServer.getServer().getMinecraftVersion() + "\" }";
        return data;
    }

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

}