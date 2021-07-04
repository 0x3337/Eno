package com.patarov.chat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

/**
 * @author Mirsaid Patarov
 * @version 16A501
 */
public class Chat implements Listener {
  @EventHandler
  public void onChat(AsyncPlayerChatEvent e) {
    Player player = e.getPlayer();
    Plugin config = Bukkit.getServer().getPluginManager().getPlugin("Eno");

    PermissionUser user = PermissionsEx.getUser(player);

    if (config.getConfig().getBoolean("chat.chatEnable")) {
      String prefix = user.getPrefix();
      char colorValue = prefix.charAt(1);
      prefix = prefix.substring(2, prefix.length());
      ChatColor color = ChatColor.WHITE;

      switch (colorValue) {
        case '0':
          color = ChatColor.BLACK;
          break;
        case '1':
          color = ChatColor.DARK_BLUE;
          break;
        case '2':
          color = ChatColor.DARK_GREEN;
          break;
        case '3':
          color = ChatColor.DARK_AQUA;
          break;
        case '4' :
          color = ChatColor.DARK_RED;
          break;
        case '5':
          color = ChatColor.DARK_PURPLE;
          break;
        case '6':
          color = ChatColor.GOLD;
          break;
        case '7':
          color = ChatColor.GRAY;
          break;
        case '8':
          color = ChatColor.DARK_GRAY;
          break;
        case '9':
          color = ChatColor.BLUE;
          break;
        case 'a':
          color = ChatColor.GREEN;
          break;
        case 'b':
          color = ChatColor.AQUA;
          break;
        case 'c':
          color = ChatColor.RED;
          break;
        case 'd':
          color = ChatColor.LIGHT_PURPLE;
          break;
        case 'e':
          color = ChatColor.YELLOW;
          break;
        case 'f':
          color = ChatColor.WHITE;
          break;
      }

      e.setFormat(color + prefix + "%s" + user.getSuffix() + ChatColor.DARK_AQUA + " - " + ChatColor.WHITE + "%s");
    }
  }
}
