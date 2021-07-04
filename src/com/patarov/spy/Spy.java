package com.patarov.spy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Mirsaid Patarov
 * @version 14K201
 */
public class Spy implements CommandExecutor {
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a){
    Player player = (Player) sender;

    if (player.hasPermission("eno.spy")) {
      if (cmd.getName().equalsIgnoreCase("spy")) {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
          p.hidePlayer(player);
        }

        player.sendMessage(ChatColor.GREEN + "[Server]" + ChatColor.WHITE + " You are invisible");
        return true;
      } else if(cmd.getName().equalsIgnoreCase("unspy")) {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
          p.showPlayer(player);
        }

        player.sendMessage(ChatColor.GREEN + "[Server]" + ChatColor.WHITE + " You are visible");

        return true;
      }
    }

    return false;
  }
}