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
            ItemStack firework = new ItemStack(Material.FIREWORK, 1 + (int) (Math.random() * 3));
            FireworkMeta meta = (FireworkMeta) firework.getItemMeta();


            FireworkEffect.Builder builder = FireworkEffect.builder();

            switch (rand.nextInt(17)) {
                case 0:
                    builder.withColor(Color.YELLOW);
                    break;
                case 1:
                    builder.withColor(Color.AQUA);
                    break;
                case 2:
                    builder.withColor(Color.BLACK);
                    break;
                case 3:
                    builder.withColor(Color.BLUE);
                    break;
                case 4:
                    builder.withColor(Color.FUCHSIA);
                    break;
                case 5:
                    builder.withColor(Color.GRAY);
                    break;
                case 6:
                    builder.withColor(Color.GREEN);
                    break;
                case 7:
                    builder.withColor(Color.LIME);
                    break;
                case 8:
                    builder.withColor(Color.MAROON);
                    break;
                case 9:
                    builder.withColor(Color.NAVY);
                    break;
                case 10:
                    builder.withColor(Color.OLIVE);
                    break;
                case 11:
                    builder.withColor(Color.ORANGE);
                    break;
                case 12:
                    builder.withColor(Color.PURPLE);
                    break;
                case 13:
                    builder.withColor(Color.RED);
                    break;
                case 14:
                    builder.withColor(Color.SILVER);
                    break;
                case 15:
                    builder.withColor(Color.TEAL);
                    break;
                case 16:
                    builder.withColor(Color.WHITE);
                    break;
                default:
                    builder.withColor(Color.WHITE);
            }


            switch (rand.nextInt(5)) {
                case 0:
                    builder.with(FireworkEffect.Type.BALL);
                    break;
                case 1:
                    builder.with(FireworkEffect.Type.CREEPER);
                    break;
                case 2:
                    builder.with(FireworkEffect.Type.STAR);
                    break;
                case 3:
                    builder.with(FireworkEffect.Type.BURST);
                    break;
                case 4:
                    builder.with(FireworkEffect.Type.BALL_LARGE);
                    break;

                default:
                    builder.with(FireworkEffect.Type.BALL);
                    break;
            }

            if (rand.nextInt(2) > 0) {
                builder.flicker(true);
            }

            if (rand.nextInt(2) > 0) {
                builder.trail(true);
            }
            meta.addEffect(builder.build());
            meta.setPower((int) (1 + (int) (Math.random() * 3)));
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
                event.setRadius((int)(event.getRadius() * 1.5));
            }
        }
    }
}

