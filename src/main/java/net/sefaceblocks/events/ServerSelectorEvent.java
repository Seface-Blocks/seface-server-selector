package net.sefaceblocks.events;

import net.sefaceblocks.guis.ServerSelectorGui;
import net.sefaceblocks.utils.BungeeMessageChannel;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class ServerSelectorEvent implements Listener {

  @EventHandler
  private void whenClickOnInventory(InventoryClickEvent event) {
    int clickedSlot = event.getRawSlot();
    Player player = (Player) event.getWhoClicked();
    Inventory inventory = event.getInventory();

    if(!inventory.equals(ServerSelectorGui.getInventory())) { return; }
    if(ServerSelectorGui.getServers().get(clickedSlot) == null) { return; }
    event.setCancelled(true);

    String server = ServerSelectorGui.getServers().get(clickedSlot);
    BungeeMessageChannel.connectTo(player, server);
  }
}
