package at.junction.MobDrops;


import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;


import java.util.ArrayList;
import java.util.List;


public class MobDropsListener implements Listener {
    MobDrops plugin;

    public MobDropsListener(MobDrops plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMobSpawnEvent(CreatureSpawnEvent event) {
        if (event.getEntityType().equals(EntityType.CREEPER)) {
            Creeper creeper = (Creeper) event.getEntity();
            int randomNumber = 1 + (int) (Math.random() * 100);
            if (randomNumber > 50) {
                creeper.setPowered(true);
            }
        }

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMobDeathEvent(EntityDeathEvent event) {
        if (event.getEntityType().equals(EntityType.CREEPER)) {
            Creeper creeper = (Creeper) event.getEntity();
            if (creeper.isPowered()) {
                event.setDroppedExp(event.getDroppedExp() * 2);
                List<ItemStack> drops = new ArrayList<ItemStack>(event.getDrops());
                ItemStack firework = new ItemStack(Material.FIREWORK, 1 + (int) (Math.random() * 3));
                FireworkMeta meta = (FireworkMeta) firework.getItemMeta();


                FireworkEffect.Builder builder = FireworkEffect.builder();

                int RandomColor = 1 + (int) (Math.random() * 17);
                switch (RandomColor) {
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
                    case 17:
                        builder.withColor(Color.YELLOW);
                        break;
                    default:
                        builder.withColor(Color.WHITE);
                }

                int RandomEffect = 1 + (int) (Math.random() * 5);

                switch (RandomEffect) {
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
                    case 5:
                        builder.with(FireworkEffect.Type.BALL);
                        break;
                    default:
                        builder.with(FireworkEffect.Type.BALL);
                        break;
                }
                int flicker = 1 + (int) (Math.random() * 100);
                if (flicker > 50) {
                    builder.flicker(true);
                }
                int trail = 1 + (int) (Math.random() * 100);
                if (trail > 50) {
                    builder.trail(true);
                }
                meta.addEffect(builder.build());
                meta.setPower((int) (1 + (int) (Math.random() * 3)));
                firework.setItemMeta(meta);
                drops.add(firework);

            }
        }
    }
}

