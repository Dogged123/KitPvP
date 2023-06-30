package me.isaacfediw.kitpvp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class GetCoinsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ItemStack coin = new ItemStack(Material.GOLD_NUGGET);
        ItemMeta coinMeta = coin.getItemMeta();
        coinMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Coin");
        coinMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        coinMeta.setLore(Collections.singletonList(ChatColor.GOLD + "Trade with villagers at spawn for gear!"));
        coin.setItemMeta(coinMeta);
        if (sender instanceof Player){
            Player p = (Player) sender;
            if (!p.hasPermission("KitPvP.GetCoinsCommand")){
                p.sendMessage(ChatColor.RED + "You do not have permission to do this!");
                return true;
            }
            if (args.length == 0){
                p.getInventory().addItem(coin);
            }else if (args.length == 1) {
                for (int i = 0; i < Integer.parseInt(args[0]); i++){
                    p.getInventory().addItem(coin);
                }
            }else if (args.length == 2){
                Player pl = Bukkit.getPlayer(args[1]);
                for (int i = 0; i < Integer.parseInt(args[0]); i++){
                    pl.getInventory().addItem(coin);
                }
                p.sendMessage(ChatColor.GREEN + "Gave " + pl.getName() + " " + args[0] + " coins");
            }
        }else{
            Player pl = Bukkit.getPlayer(args[1]);
            for (int i = 0; i < Integer.parseInt(args[0]); i++){
                pl.getInventory().addItem(coin);
            }
        }
        return true;
    }
}
