package at.junction.MobDrops;


import org.bukkit.plugin.java.JavaPlugin;

public class MobDrops extends JavaPlugin{

    @Override
    public void onEnable(){
        getLogger().info("Enabling MobDrops");
        getServer().getPluginManager().registerEvents(new MobDropsListener(this), this);

    }
    public void onDisable(){

    }

    public void onCommand(){

    }

}
