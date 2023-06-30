package me.isaacfediw.kitpvp;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Rules implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            p.sendMessage(ChatColor.RED + "" + ChatColor.UNDERLINE + "Rules:");
            p.sendMessage(ChatColor.RED + "1. " + ChatColor.WHITE + "Be Respectful");
            p.sendMessage(ChatColor.RED + "2. " + ChatColor.WHITE + "No Hacking");
            p.sendMessage(ChatColor.RED + "3. " + ChatColor.WHITE + "No Excessive Swearing");
            p.sendMessage(ChatColor.RED + "5. " + ChatColor.WHITE + "No Spamming");
            p.sendMessage(ChatColor.RED + "6. " + ChatColor.WHITE + "No Lava Casting");
            p.sendMessage(ChatColor.RED + "7. " + ChatColor.WHITE + "Use Common Sense");
        }

        return true;
    }
}
