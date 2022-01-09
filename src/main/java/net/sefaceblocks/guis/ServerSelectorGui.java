package net.sefaceblocks.guis;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import lombok.Getter;
import net.sefaceblocks.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static net.sefaceblocks.SefaceServerSelector.*;

public class ServerSelectorGui {
  private static final int GUI_SIZE = getInstance().getConfig().getInt("server-selector.gui.size");
  private static final String GUI_NAME = getInstance().getConfig().getString("server-selector.gui.name");
  private static final ConfigurationSection SERVERS_SECTION = getInstance().getConfig().getConfigurationSection("server-selector.servers");

  @Getter private static final HashMap<Integer, String> servers = new HashMap<>();

  @Getter private static Inventory inventory;

  public ServerSelectorGui() {
    inventory = Bukkit.createInventory(null, GUI_SIZE, Utils.replaceColor(GUI_NAME));

    SERVERS_SECTION.getKeys(false).forEach(server -> {
      int slot = getInstance().getConfig().getInt("server-selector.servers." + server + ".slot");
      String icon = getInstance().getConfig().getString("server-selector.servers." + server + ".icon");
      String name = getInstance().getConfig().getString("server-selector.servers." + server + ".name");
      ArrayList<String> description = (ArrayList<String>) getInstance().getConfig().getStringList("server-selector.servers." + server + ".description");

      servers.put(slot, server);
      this.addServerSkull(Utils.replaceColor(name), Utils.replaceColor(description), slot, icon, server);
    });
  }

  private void addServerSkull(String name, List<String> description, int slot, String icon, String server) {
    ItemStack skull = this.generateSkull(name, description, icon, server);
    inventory.setItem(slot, skull);
  }

  private ItemStack generateSkull(String name, List<String> lore, String texture, String server) {
    // Create a fake profile
    Field profileField;
    GameProfile profile = new GameProfile(UUID.randomUUID(), server);
    profile.getProperties().put("textures", new Property("textures", texture));

    ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
    SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();

    try {
      profileField = skullMeta.getClass().getDeclaredField("profile");
      profileField.setAccessible(true);
      profileField.set(skullMeta, profile);
    } catch (IllegalArgumentException | NoSuchFieldException | IllegalAccessException err) {
      err.printStackTrace();
    }

    skullMeta.setDisplayName(name);
    skullMeta.setLore(lore);
    skull.setItemMeta(skullMeta);

    return skull;
  }
}
