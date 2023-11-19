package me.groznyogor;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class groznyogor_pomoc extends JavaPlugin implements CommandExecutor {

    private FileConfiguration config;

    @Override
    public void onEnable() {
        getCommand("pomoc").setExecutor(this);

        loadConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("pomoc")) {
            if (args.length == 0) {
                displayHelp(sender);
                return true;
            }

            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("groznyogor.pomocreload")) {
                    reloadConfig();
                    sender.sendMessage(ChatColor.GREEN + "Plugin groznyogor-pomoc został przeładowany.");
                } else {
                    sender.sendMessage(ChatColor.RED + "Nie masz uprawnień do przeładowania pluginu.");
                }
                return true;
            }
        }
        return false;
    }

    private void displayHelp(CommandSender sender) {
        for (String line : config.getStringList("help")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', line));
        }
    }

    private void loadConfig() {
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            saveDefaultConfig();
        }
        config = YamlConfiguration.loadConfiguration(configFile);
    }
}
