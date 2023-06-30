package me.isaacfediw.kitpvp;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collections;

import static me.isaacfediw.kitpvp.KitPvP.kits;

public class PlayerEvents implements Listener {
    KitPvP plugin;
    public PlayerEvents(KitPvP p){
        plugin = p;
    }
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e){
        Player p = e.getPlayer();
        ItemStack[] randomKit1 = {new ItemStack(Material.DIAMOND_HELMET),
                new ItemStack(Material.IRON_CHESTPLATE),
                new ItemStack(Material.IRON_LEGGINGS),
                new ItemStack(Material.DIAMOND_BOOTS),
                new ItemStack(Material.DIAMOND_AXE),
                new ItemStack(Material.IRON_SWORD),
                new ItemStack(Material.DIAMOND_PICKAXE),
                new ItemStack(Material.SANDSTONE, 32),
                plugin.randomItemStack(),
                plugin.randomItemStack(),
                plugin.randomItemStack(),
                plugin.randomItemStack()};
        ItemStack[] randomKit2 = {new ItemStack(Material.IRON_HELMET),
                new ItemStack(Material.DIAMOND_CHESTPLATE),
                new ItemStack(Material.DIAMOND_LEGGINGS),
                new ItemStack(Material.IRON_BOOTS),
                new ItemStack(Material.IRON_AXE),
                new ItemStack(Material.DIAMOND_SWORD),
                new ItemStack(Material.IRON_PICKAXE),
                new ItemStack(Material.SANDSTONE, 32),
                plugin.randomItemStack(),
                plugin.randomItemStack(),
                plugin.randomItemStack(),
                plugin.randomItemStack()};
        kits.set(3, randomKit1);
        kits.set(4, randomKit2);
        equipKit(p);
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000000, 4, true, false), true);
        if (!p.hasPlayedBefore()){
            equipKit(p);
        }
    }

    @EventHandler
    public void playerMoveEvent(PlayerMoveEvent e){
        Player p = e.getPlayer();
        p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000000, 4, true, false), true);
    }
    public void equipKit(Player p){
        int index = (int) (Math.random() * kits.size()) + 1;
        if (index == kits.size()) index = 0;
        ItemStack[] kit = kits.get(index);
        p.getInventory().setHelmet(kit[0]);
        p.getInventory().setChestplate(kit[1]);
        p.getInventory().setLeggings(kit[2]);
        p.getInventory().setBoots(kit[3]);
        for (int i = 4; i < kit.length; i++){
            p.getInventory().addItem(kit[i]);
        }
    }

    @EventHandler
    public void playerDeath(PlayerDeathEvent e){
        Player p = e.getEntity();
        if (p.getKiller() == null){
            e.setDeathMessage(ChatColor.GRAY + "[" + ChatColor.RED + "☠" + ChatColor.GRAY + "] " + ChatColor.RED + p.getName() + " died");
        }
        else{
            e.setDeathMessage(ChatColor.GRAY + "[" + ChatColor.RED + "☠" + ChatColor.GRAY + "] " + ChatColor.RED + p.getName() + " was killed by " + p.getKiller().getName());
            ItemStack coin = new ItemStack(Material.GOLD_NUGGET);
            ItemMeta coinMeta = coin.getItemMeta();
            coinMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Coin");
            coinMeta.addEnchant(Enchantment.DURABILITY, 1, true);
            coinMeta.setLore(Collections.singletonList(ChatColor.GOLD + "Trade with villagers at spawn for gear!"));
            coin.setItemMeta(coinMeta);
            int coinAmount = (int) (Math.random() * 5) + 1;
            for (int i = 0; i < coinAmount; i++){
                p.getKiller().getInventory().addItem(coin);
            }
        }
    }
}
