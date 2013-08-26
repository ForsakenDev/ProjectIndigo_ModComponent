package co.zmc.projectindigo;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler {

    private String getData() {
        String data = "{ \"name\": \"" + Main._instance.getServerName() + "\",";
        data += "\"ip\": \"" + Main._instance.getServerIP() + "\",";
        data += "\"logo\": \"" + Main._instance.getLogoURL() + "\",";
        data += "\"modpack_host\": \"" + Main._instance.getPackHost() + "\",";
        data += "\"modpack_name\": \"" + Main._instance.getPackName() + "\",";
        data += "\"modpack_loc\": \"" + Main._instance.getPackLocation() + "\",";
        data += "\"modpack_version\": \"" + Main._instance.getPackVersion() + "\" }";
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