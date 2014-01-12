package at.junction.CreeperWorks;


import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CreeperWorks extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Enabling CreeperWorks");
        getServer().getPluginManager().registerEvents(new CreeperWorksListener(this), this);

    }

    @Override
    public void onDisable() {

    }

    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("spawn-creeper")) {
            //args - world x y z num
            if (args.length != 5) {
                sender.sendMessage(ChatColor.RED + "Usage: spawn-creeper world x y z number");
                return true;
            }

            World w = getServer().getWorld(args[0]);
            Location loc = new Location(w, Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]));
            spawnCreepers(w, loc, Integer.parseInt(args[4]));

        }
        return true;
    }

    void spawnCreepers(World w, Location loc, int number) {
        for (int i = 0; i < number; i++) {

            Creeper creeper = (Creeper) w.spawnEntity(loc, EntityType.CREEPER);
            creeper.setPowered(true);
            creeper.setMetadata("CW-SPAWN", new FixedMetadataValue(this, true));
            creeper.setHealth(20);
            creeper.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 36000, 24));
        }
    }


}
