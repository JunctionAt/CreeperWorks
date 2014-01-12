package at.junction.CreeperWorks;


import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.Random;


import java.util.ArrayList;
import java.util.List;


public class CreeperWorksListener implements Listener {
    CreeperWorks plugin;

    public CreeperWorksListener(CreeperWorks plugin) {
        this.plugin = plugin;
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMobDeathEvent(EntityDeathEvent event) {
        if (event.getEntityType().equals(EntityType.CREEPER) && event.getEntity().hasMetadata("CW-SPAWN")) {
            Creeper creeper = (Creeper) event.getEntity();
            Random rand = new Random();

            event.setDroppedExp(event.getDroppedExp() * 2);
            ItemStack firework = new ItemStack(Material.FIREWORK, rand.nextInt(plugin.config.FIREWORKS_DROPPED)+1);
            FireworkMeta meta = (FireworkMeta) firework.getItemMeta();


            FireworkEffect.Builder builder = FireworkEffect.builder();

            switch (rand.nextInt(3)){
                case 0:
                    builder.withColor(getColor());
                    break;
                case 1:
                    builder.withColor(getColor(), getColor());
                    break;
                case 2:
                    builder.withColor(getColor(), getColor(), getColor());
            }

            if (rand.nextInt(2) > 0){
                builder.withFade(getColor());
            }

            builder.with(getFireworkType());

            if (rand.nextInt(2) > 0) {
                builder.flicker(true);
            }

            if (rand.nextInt(2) > 0) {
                builder.trail(true);
            }
            meta.addEffect(builder.build());
            meta.setPower(rand.nextInt(plugin.config.MAX_POWER)+1);
            firework.setItemMeta(meta);
            event.getDrops().add(firework);

        }
    }

    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity hurt = event.getEntity();

        if (hurt instanceof Creeper) {
            if (hurt.hasMetadata("CW-SPAWN")) {

                if (damager instanceof Creeper) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onExplosionEvent(ExplosionPrimeEvent event){
        if (event.getEntity() instanceof Creeper){
            if (event.getEntity().hasMetadata("CW-SPAWN")){
                event.setRadius((int)(event.getRadius() * plugin.config.EXPLOSION_MODIFIER));
            }
        }
    }
    
    private FireworkEffect.Type getFireworkType(){
        switch (new Random().nextInt(5)) {
            case 0:
                return FireworkEffect.Type.BALL;
            case 1:
                return FireworkEffect.Type.CREEPER;
            case 2:
                return FireworkEffect.Type.STAR;
            case 3:
                return FireworkEffect.Type.BURST;
            case 4:
                return FireworkEffect.Type.BALL_LARGE;
            default:
                return FireworkEffect.Type.BALL;
                
        }

    }
    
    private Color getColor(){
        switch (new Random().nextInt(17)) {
            case 0:
                return Color.YELLOW;
            case 1:
                return Color.AQUA;
            case 2:
                return Color.BLACK;
            case 3:
                return Color.BLUE;
            case 4:
                return Color.FUCHSIA;
            case 5:
                return Color.GRAY;
            case 6:
                return Color.GREEN;
            case 7:
                return Color.LIME;
            case 8:
                return Color.MAROON;
            case 9:
                return Color.NAVY;
            case 10:
                return Color.OLIVE;
            case 11:
                return Color.ORANGE;
            case 12:
                return Color.PURPLE;
            case 13:
                return Color.RED;
            case 14:
                return Color.SILVER;
            case 15:
                return Color.TEAL;
            case 16:
                return Color.WHITE;
            default:
                return Color.WHITE;
        }

    } 
}

