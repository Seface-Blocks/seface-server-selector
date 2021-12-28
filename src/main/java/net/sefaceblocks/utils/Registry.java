package net.sefaceblocks.utils;

import net.sefaceblocks.commands.ServerSelectorCommand;
import net.sefaceblocks.events.ServerSelectorEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Registry {
  private static final String CHANNEL = "BungeeCord";

  /** Registry config.yml */
  public static void registerConfiguration(JavaPlugin plugin) {
    plugin.getConfig().options().copyDefaults(false);
    plugin.saveDefaultConfig();
  }

  /** Registry Bungee Message Channel */
  public static void registerBungeeMessageChannel(JavaPlugin plugin) {
    plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, CHANNEL);
    plugin.getServer().getMessenger().registerIncomingPluginChannel(plugin, CHANNEL, new BungeeMessageChannel());
  }

  public static void registerCommands(JavaPlugin plugin) {
    plugin.getCommand("servers").setExecutor(new ServerSelectorCommand());
  }

  public static void registerEvents(JavaPlugin plugin) {
    plugin.getServer().getPluginManager().registerEvents(new ServerSelectorEvent(), plugin);
  }
}
