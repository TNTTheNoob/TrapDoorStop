package me.pumpkinpro.trapdoorstop;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class TrapDoorStop extends JavaPlugin implements Listener, CommandExecutor {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("trapdoorstop").setExecutor(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    @EventHandler
    public void onBlockInteract(PlayerInteractEvent event){
        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if(event.getClickedBlock().getType().equals(Material.TRAP_DOOR)){
                if(getConfig().getStringList("worlds").contains(event.getPlayer().getWorld().getName())){
                    event.setCancelled(true);
                }
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length != 1){
            sender.sendMessage("Usage: /trapdoorstop reload");
        } else {
            if(args[0].equalsIgnoreCase("reload")){
                reloadConfig();
                sender.sendMessage("Reloaded the config.");
            } else {
                sender.sendMessage("Usage: /trapdoorstop reload");
            }
        }

        return true;
    }
}
