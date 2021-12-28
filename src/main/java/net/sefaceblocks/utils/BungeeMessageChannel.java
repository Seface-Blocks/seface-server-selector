package net.sefaceblocks.utils;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import static net.sefaceblocks.SefaceServerSelector.*;

public class BungeeMessageChannel implements PluginMessageListener {
    private static final String CHANNEL = "BungeeCord";


    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] messages) {
        if(!channel.equals(CHANNEL)) { return; }

        ByteArrayDataInput input = ByteStreams.newDataInput(messages);
        String subchannel = input.readUTF();
    }

    public static void connectTo(Player player, String server) {
        ByteArrayDataOutput output = ByteStreams.newDataOutput();

        output.writeUTF("Connect");
        output.writeUTF(server);

        player.sendPluginMessage(getInstance(), CHANNEL, output.toByteArray());
    }
}
