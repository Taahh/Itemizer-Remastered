package me.taahanis.itemizer;

import org.bukkit.plugin.java.JavaPlugin;

public class Itemizer extends JavaPlugin {

    @Override
    public void onEnable()
    {
        getCommand("itemizer").setExecutor(new ItemizerCommand());
    }

    @Override
    public void onDisable()
    {

    }


}
