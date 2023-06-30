package me.isaacfediw.kitpvp;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public final class KitPvP extends JavaPlugin {

    public static ArrayList<ItemStack[]> kits = new ArrayList<>();
    @Override
    public void onEnable() {
        KitPvP plugin = this;

        Bukkit.getServer().getPluginManager().registerEvents(new PlayerEvents(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new AutoTnt(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new CoinShopManager(), this);
        getCommand("rules").setExecutor(new Rules());
        getCommand("coinShop").setExecutor(new CoinShopCommand());
        getCommand("coin").setExecutor(new GetCoinsCommand());

        ItemStack[] ironKit = {new ItemStack(Material.IRON_HELMET),
                new ItemStack(Material.IRON_CHESTPLATE),
                new ItemStack(Material.IRON_LEGGINGS),
                new ItemStack(Material.IRON_BOOTS),
                new ItemStack(Material.IRON_AXE),
                new ItemStack(Material.IRON_SWORD),
                new ItemStack(Material.DIAMOND_PICKAXE),
                new ItemStack(Material.BOW),
                new ItemStack(Material.FISHING_ROD),
                new ItemStack(Material.SANDSTONE, 64),
                new ItemStack(Material.COOKED_BEEF, 64),
                new ItemStack(Material.GOLDEN_APPLE, 8),
                new ItemStack(Material.LAVA_BUCKET),
                new ItemStack(Material.WATER_BUCKET),
                new ItemStack(Material.ARROW, 64)};

        ItemStack[] diamondKit = {new ItemStack(Material.DIAMOND_HELMET),
                new ItemStack(Material.DIAMOND_CHESTPLATE),
                new ItemStack(Material.DIAMOND_LEGGINGS),
                new ItemStack(Material.DIAMOND_BOOTS),
                new ItemStack(Material.DIAMOND_SWORD),
                new ItemStack(Material.DIAMOND_PICKAXE),
                new ItemStack(Material.FISHING_ROD),
                new ItemStack(Material.SANDSTONE, 64),
                new ItemStack(Material.COOKED_BEEF, 64),
                new ItemStack(Material.GOLDEN_APPLE, 4),
                new ItemStack(Material.WATER_BUCKET)};

        ItemStack[] tntKit = {new ItemStack(Material.IRON_HELMET),
                new ItemStack(Material.DIAMOND_CHESTPLATE),
                new ItemStack(Material.IRON_LEGGINGS),
                new ItemStack(Material.DIAMOND_BOOTS),
                new ItemStack(Material.IRON_AXE),
                new ItemStack(Material.DIAMOND_SWORD),
                new ItemStack(Material.DIAMOND_PICKAXE),
                new ItemStack(Material.BOW),
                new ItemStack(Material.FISHING_ROD),
                new ItemStack(Material.WOOD, 64),
                new ItemStack(Material.COBBLESTONE, 64),
                new ItemStack(Material.TNT, 32),
                new ItemStack(Material.GOLDEN_CARROT, 64),
                new ItemStack(Material.GOLDEN_APPLE, 4),
                new ItemStack(Material.WATER_BUCKET),
                new ItemStack(Material.ARROW, 64)};

        ItemStack[] randomKit1 = {new ItemStack(Material.DIAMOND_HELMET),
                new ItemStack(Material.IRON_CHESTPLATE),
                new ItemStack(Material.IRON_LEGGINGS),
                new ItemStack(Material.DIAMOND_BOOTS),
                new ItemStack(Material.DIAMOND_AXE),
                new ItemStack(Material.IRON_SWORD),
                new ItemStack(Material.DIAMOND_PICKAXE),
                new ItemStack(Material.SANDSTONE, 32),
                randomItemStack(),
                randomItemStack(),
                randomItemStack(),
                randomItemStack()};

        ItemStack[] randomKit2 = {new ItemStack(Material.IRON_HELMET),
                new ItemStack(Material.DIAMOND_CHESTPLATE),
                new ItemStack(Material.DIAMOND_LEGGINGS),
                new ItemStack(Material.IRON_BOOTS),
                new ItemStack(Material.IRON_AXE),
                new ItemStack(Material.DIAMOND_SWORD),
                new ItemStack(Material.IRON_PICKAXE),
                new ItemStack(Material.SANDSTONE, 32),
                randomItemStack(),
                randomItemStack(),
                randomItemStack(),
                randomItemStack()};

        kits.add(ironKit);
        kits.add(diamondKit);
        kits.add(tntKit);
        kits.add(randomKit1);
        kits.add(randomKit2);

        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ChatColor.RED + "Clearing all entities!");
                new BukkitRunnable(){
                    Integer timeTillClear = 5;
                    @Override
                    public void run(){
                        if (timeTillClear == 0){
                            for (Entity entity : Bukkit.getWorld("Spawn1").getEntities()){
                                if (!entity.getType().equals(EntityType.PLAYER)){
                                    entity.remove();
                                }
                            }
                            Bukkit.broadcastMessage(ChatColor.RED + "Entities Cleared!");
                            cancel();
                            TextComponent discord = new TextComponent(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Discord");
                            discord.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/6dp2ASWw"));
                            Bukkit.broadcastMessage(ChatColor.DARK_AQUA + "Join our discord server!");
                            for(Player p : Bukkit.getOnlinePlayers()){
                                p.spigot().sendMessage(discord);
                            }
                            return;
                        }
                        Bukkit.broadcastMessage(ChatColor.RED + timeTillClear.toString());
                        timeTillClear --;
                    }
                }.runTaskTimer(plugin, 0, 20);
            }
        }.runTaskTimer(this, 0, 6000);
    }
    public ItemStack randomItemStack(){
        ArrayList<ItemStack> itemStacks = new ArrayList<>();

        ItemStack wood = new ItemStack(Material.WOOD, 64);
        ItemStack cobblestone = new ItemStack(Material.COBBLESTONE, 64);
        ItemStack water = new ItemStack(Material.WATER_BUCKET);
        ItemStack lava = new ItemStack(Material.LAVA_BUCKET);
        ItemStack beef = new ItemStack(Material.COOKED_BEEF, 64);
        ItemStack goldenCarrots = new ItemStack(Material.GOLDEN_CARROT, 64);
        ItemStack goldenApples = new ItemStack(Material.GOLDEN_APPLE, 4);
        ItemStack tnt = new ItemStack(Material.TNT, 16);
        ItemStack bow = new ItemStack(Material.BOW);
        ItemStack rod = new ItemStack(Material.FISHING_ROD);
        ItemStack arrows = new ItemStack(Material.ARROW, 64);

        itemStacks.add(wood);
        itemStacks.add(cobblestone);
        itemStacks.add(water);
        itemStacks.add(lava);
        itemStacks.add(beef);
        itemStacks.add(goldenCarrots);
        itemStacks.add(goldenApples);
        itemStacks.add(tnt);
        itemStacks.add(bow);
        itemStacks.add(rod);
        itemStacks.add(arrows);

        int random = (int) (Math.random() * itemStacks.size());

        return itemStacks.get(random);
    }
}
