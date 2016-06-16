package audio.paco.mc.lazulifish;

import audio.paco.mc.lazulifish.command.ChatCommand;
import audio.paco.mc.lazulifish.command.ScareCommand;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;

public class Lazulifish extends JavaPlugin {

    protected Lazulifish(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file) {
        super(loader, description, dataFolder, file);
    }

    public Lazulifish() {
        super();
    }

    @Override
    public void onEnable() {
        this.getCommand("scare").setExecutor(new ScareCommand());
        this.getCommand("chat").setExecutor(new ChatCommand());
    }

    @Override
    public void onDisable() {

    }
}
