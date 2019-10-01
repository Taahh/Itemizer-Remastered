package me.taahanis.itemizer;

import org.bukkit.plugin.java.JavaPlugin;

public class Itemizer extends JavaPlugin {

    @Override
    public void onEnable()
    {
        getLogger().info("§6[§bItemizer§6] §aEnabling ITEMIZER v" + getDescription().getVersion());
        getLogger().info("§6[§bItemizer§6] §aCreated by: " + getDescription().getAuthors().get(0));
        getCommand("itemizer").setExecutor(new ItemizerCommand());
    }

    @Override
    public void onDisable()
    {
        getLogger().info("§6[§bItemizer§6] §aDisabling ITEMIZER v" + getDescription().getVersion());
        getLogger().info("§6[§bItemizer§6] §aCreated by: " + getDescription().getAuthors().get(0));
    }


}
