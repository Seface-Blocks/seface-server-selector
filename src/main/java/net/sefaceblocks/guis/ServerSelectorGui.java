package net.sefaceblocks.guis;

import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;

import static net.sefaceblocks.SefaceServerSelector.*;

public class ServerSelectorGui {
  private static final ConfigurationSection SERVERS_SECTION = getInstance().getConfig().getConfigurationSection("server-selector.servers");
  private static final HashMap<Integer, String> SERVERS = new HashMap<>();
}
