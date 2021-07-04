package com.patarov;

import com.patarov.spy.*;
import com.patarov.chat.*;
import com.patarov.labyrinth.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import com.patarov.chat.Chat;
import com.patarov.labyrinth.Labyrinth;
import com.patarov.spy.Spy;

/**
 * @author Mirsaid Patarov
 * @version 15F301
 */
public class Syntak extends JavaPlugin {
  public void onEnable() {
    Bukkit.getPluginManager().registerEvents(new Chat(), this);
    Bukkit.getPluginManager().registerEvents(new Labyrinth(), this);

    this.getCommand("spy").setExecutor(new Spy());
    this.getCommand("unspy").setExecutor(new Spy());

    this.saveDefaultConfig();
    getConfig().options().copyDefaults(true);
    saveConfig();
  }

  public void onDisable() {
    saveConfig();
  }

  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a) {
    Player player = (Player) sender;

    if (player.hasPermission("eno.help")) {
      if (cmd.getName().equalsIgnoreCase("eno")) {
        player.sendMessage(ChatColor.GOLD + "------- " + ChatColor.WHITE +  "Eno Version: 1.0" + ChatColor.GOLD + " -------");
        player.sendMessage(ChatColor.GOLD + "/eno" + ChatColor.WHITE +": plugin commands");
        player.sendMessage(ChatColor.GOLD + "/shop" + ChatColor.WHITE +": teleport you to the store");
        player.sendMessage(ChatColor.GOLD + "/setshop" + ChatColor.WHITE +": Create shop location");
        player.sendMessage(ChatColor.GOLD + "/spy" + ChatColor.WHITE +": it makes you invisible");
        player.sendMessage(ChatColor.GOLD + "/unspy" + ChatColor.WHITE +": it makes you visible");
      }
    }

    if (player.hasPermission("eno.shop")) {
      if (player.hasPermission("eno.setshop")) {
        if (cmd.getName().equalsIgnoreCase("setshop")) {
          getConfig().createSection("shop");
          this.getConfig().set("shop.world", player.getLocation().getWorld().getName());
          this.getConfig().set("shop.x", player.getLocation().getX());
          this.getConfig().set("shop.y", player.getLocation().getY());
          this.getConfig().set("shop.z", player.getLocation().getZ());
          this.getConfig().set("shop.yaw", player.getLocation().getYaw());
          this.getConfig().set("shop.pitch", player.getLocation().getPitch());

          player.sendMessage(ChatColor.GREEN + "[Server]" + ChatColor.WHITE + " Coordinates for the store set");
          saveConfig();
        }
      }

      if (cmd.getName().equalsIgnoreCase("shop")) {
        if (getConfig().contains("shop")) {
          World shopWorld = Bukkit.getWorld(this.getConfig().getString("shop.world"));
          double shopX = this.getConfig().getDouble("shop.x");
          double shopY = this.getConfig().getDouble("shop.y");
          double shopZ = this.getConfig().getDouble("shop.z");
          float shopYaw = this.getConfig().getInt("shop.yaw");
          float shopPitch = this.getConfig().getInt("shop.pitch");

          Location shop = new Location((World) shopWorld, shopX, shopY, shopZ, shopYaw, shopPitch);
          player.teleport(shop);
        } else {
          player.sendMessage(ChatColor.RED + "[Server]" + ChatColor.WHITE + " To use this command, you need a VIP");
        }
      }
    }

    return false;
  }
}
