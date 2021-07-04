package com.patarov.labyrinth;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * @author Mirsaid Patarov
 * @version 15F501
 */
public class Labyrinth implements Listener {
  @EventHandler
  public void onSign(SignChangeEvent e) {
    e.setLine(0, ChatColor.translateAlternateColorCodes('&', e.getLine(0)));
    e.setLine(1, ChatColor.translateAlternateColorCodes('&', e.getLine(1)));
    e.setLine(2, ChatColor.translateAlternateColorCodes('&', e.getLine(2)));
    e.setLine(3, ChatColor.translateAlternateColorCodes('&', e.getLine(3)));
  }

  @EventHandler
  public void onSignChange(SignChangeEvent e) {
    if (e.getPlayer().getWorld().getName().equalsIgnoreCase("Labyrinth")) {
      if (e.getLine(0).equals("GTS")) {
        e.setLine(0, "");
        e.setLine(1, ChatColor.GREEN + "Go to");
        e.setLine(2, ChatColor.GREEN + "Spawn");
        e.setLine(3, "");
      }
    }
  }

  @EventHandler
  public void onPlayerInteract(PlayerInteractEvent e) {
    Player player = e.getPlayer();
    Block b = e.getClickedBlock();
    Action action = e.getAction();

    if (player.getWorld().getName().equalsIgnoreCase("Labyrinth")) {
      if (e.getClickedBlock() == null) {
        return;
      }

      if (action == Action.RIGHT_CLICK_BLOCK && b != null && (b.getType() == Material.SIGN_POST || b.getType() == Material.WALL_SIGN)) {
        Sign sign = (Sign) b.getState();
        if (sign.getLine(1).equals(ChatColor.GREEN + "Go to") && sign.getLine(2).equals(ChatColor.GREEN + "Spawn")) {
          player.teleport(new Location(Bukkit.getWorld("Aristi"), 0.5d, 65, 0.5d,  -89.850006f, -0.4500304f));
        }
      }
    }
  }

  @EventHandler
  public void onMove(PlayerMoveEvent e) {
    Player p = (Player) e.getPlayer();

    if (!p.hasPermission("eno.labyrinth")) {
      if (p.getWorld().getName().equalsIgnoreCase("Labyrinth")) {
        p.setGameMode(GameMode.ADVENTURE);
      }
    }

    if (p.getHealth() < 3) {
      p.setHealth(20);
      p.teleport(new Location(Bukkit.getWorld("Labyrinth"), 0.5d, 65, 0.5d, -89.850006f, -0.4500304f));
      e.setCancelled(true);
    }
  }
}
