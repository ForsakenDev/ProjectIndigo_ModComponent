package co.zmc.projectindigo;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler {

    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
        if (packet.channel.equals("projectindigo") && new String(packet.data).equalsIgnoreCase("request_modpack_url")) {
            Packet250CustomPayload newPacket = new Packet250CustomPayload();
            newPacket.channel = "projectindigo";
            newPacket.length = Main._instance.getServerInformationURL().trim().getBytes().length;
            newPacket.data = Main._instance.getServerInformationURL().trim().getBytes();
            manager.addToSendQueue(newPacket);
            System.out.println("Packet requested and returned");
        }
    }

}