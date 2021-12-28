package net.sefaceblocks;

import lombok.Getter;
import net.sefaceblocks.utils.Registry;
import org.bukkit.plugin.java.JavaPlugin;

public class SefaceServerSelector extends JavaPlugin {
  @Getter private static SefaceServerSelector instance;

  @Override
  public void onEnable() {
    super.onEnable();
    instance = this;

    Registry.registerConfiguration(this);
    Registry.registerBungeeMessageChannel(this);
  }
}
