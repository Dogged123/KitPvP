package me.isaacfediw.kitpvp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class CoinShopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) return true;
        if (args.length == 0){
            System.out.println("Please specify the player you want the shop to open to");
            return true;
        }
        Player p = Bukkit.getPlayer(args[0]);
        Inventory coinShop = Bukkit.createInventory(p, 18, ChatColor.GOLD + "Coin Shop");

        ItemStack coin = new ItemStack(Material.GOLD_NUGGET);
        ItemMeta coinMeta = coin.getItemMeta();
        coinMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Coin");
        coinMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        coinMeta.setLore(Collections.singletonList(ChatColor.GOLD + "Click to purchase, costs 4 emeralds or diamonds"));
        coin.setItemMeta(coinMeta);

        ItemStack coins = new ItemStack(Material.GOLD_NUGGET, 64);
        ItemMeta coinsMeta = coins.getItemMeta();
        coinsMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Coin");
        coinsMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        coinsMeta.setLore(Collections.singletonList(ChatColor.GOLD + "Click to purchase, costs 1 compressed coin"));
        coins.setItemMeta(coinsMeta);

        ItemStack compCoin = new ItemStack(Material.GOLD_BLOCK);
        ItemMeta compCoinMeta = compCoin.getItemMeta();
        compCoinMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Compressed Coin");
        compCoinMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        compCoinMeta.setLore(Collections.singletonList(ChatColor.GOLD + "Click to purchase, costs 64 coins"));
        compCoin.setItemMeta(compCoinMeta);

        ItemStack gapple = new ItemStack(Material.GOLDEN_APPLE, 8);
        ItemMeta gappleMeta = gapple.getItemMeta();
        gappleMeta.setDisplayName(ChatColor.GOLD + "Golden Apple");
        gappleMeta.setLore(Collections.singletonList(ChatColor.GOLD + "Click to purchase, costs 40 coins"));
        gapple.setItemMeta(gappleMeta);

        ItemStack pearl = new ItemStack(Material.ENDER_PEARL);
        ItemMeta pearlMeta = pearl.getItemMeta();
        pearlMeta.setDisplayName(ChatColor.DARK_GREEN + "Ender Pearl");
        pearlMeta.setLore(Collections.singletonList(ChatColor.GOLD + "Click to purchase, costs 8 coins"));
        pearl.setItemMeta(pearlMeta);

        coinShop.setItem(0, coin);
        coinShop.setItem(2, coins);
        coinShop.setItem(4, compCoin);
        coinShop.setItem(6, gapple);
        coinShop.setItem(8, pearl);
        p.openInventory(coinShop);

        return true;
    }
}
