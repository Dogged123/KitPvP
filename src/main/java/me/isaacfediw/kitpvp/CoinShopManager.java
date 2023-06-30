package me.isaacfediw.kitpvp;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.Collections;

public class CoinShopManager implements Listener {
    @EventHandler
    public void onCoinShopClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        PlayerInventory inv = p.getInventory();
        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD + "Coin Shop")){
            ItemStack coin = new ItemStack(Material.GOLD_NUGGET);
            ItemMeta coinMeta = coin.getItemMeta();
            coinMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Coin");
            coinMeta.setLore(Collections.singletonList(ChatColor.GOLD + "Trade with villagers at spawn for gear!"));
            coinMeta.addEnchant(Enchantment.DURABILITY, 1, true);
            coin.setItemMeta(coinMeta);

            ItemStack compCoin = new ItemStack(Material.GOLD_BLOCK);
            ItemMeta compCoinMeta = compCoin.getItemMeta();
            compCoinMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Compressed Coin");
            compCoinMeta.addEnchant(Enchantment.DURABILITY, 1, true);
            compCoin.setItemMeta(compCoinMeta);

            switch (e.getSlot()){
                case 0:
                    if (!inv.containsAtLeast(new ItemStack(Material.EMERALD), 4) && !inv.containsAtLeast(new ItemStack(Material.DIAMOND), 4)){
                        p.sendMessage(ChatColor.RED + "You do not have enough resources to purchase a coin!");
                        break;
                    }
                    if (inv.containsAtLeast(new ItemStack(Material.EMERALD), 4)){
                        inv.removeItem(new ItemStack(Material.EMERALD, 4));
                    }else if (inv.containsAtLeast(new ItemStack(Material.DIAMOND), 4)){
                        inv.removeItem(new ItemStack(Material.DIAMOND, 4));
                    }
                    inv.addItem(coin);
                    break;
                case 2:
                    if (!inv.containsAtLeast(compCoin, 1)){
                        p.sendMessage(ChatColor.RED + "You do not have enough resources to purchase 64 coins!");
                        break;
                    }
                    inv.removeItem(compCoin);
                    for (int i = 0; i < 64; i++){
                        inv.addItem(coin);
                    }
                    break;
                case 4:
                    if (!inv.containsAtLeast(coin, 64)){
                        p.sendMessage(ChatColor.RED + "You do not have enough coins to purchase a compressed coin!");
                        break;
                    }
                    for (int i = 0; i < 64; i++){
                        inv.removeItem(coin);
                    }
                    inv.addItem(compCoin);
                    break;
                case 6:
                    if (!inv.containsAtLeast(coin, 40)){
                        p.sendMessage(ChatColor.RED + "You do not have enough coins to purchase golden apples!");
                        break;
                    }
                    for (int i = 0; i < 40; i++){
                        inv.removeItem(coin);
                    }
                    inv.addItem(new ItemStack(Material.GOLDEN_APPLE, 8));
                    break;
                case 8:
                    if (!inv.containsAtLeast(coin, 8)){
                        p.sendMessage(ChatColor.RED + "You do not have enough coins to purchase an ender pearl!");
                        break;
                    }
                    for (int i = 0; i < 8; i++){
                        inv.removeItem(coin);
                    }
                    inv.addItem(new ItemStack(Material.ENDER_PEARL));
                    break;
            }
            e.setCancelled(true);
        }
    }
}
