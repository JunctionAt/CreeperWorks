package at.junction.CreeperWorks;


public class Configuration {
    CreeperWorks plugin;
    public int FIREWORKS_DROPPED;
    public double EXPLOSION_MODIFIER;
    public int MAX_POWER;
    public int EXP_BONUS;
    public Configuration(CreeperWorks plugin){
        this.plugin = plugin;
        load();
    }

    void load(){
        FIREWORKS_DROPPED = plugin.getConfig().getInt("fireworks-dropped");
        EXPLOSION_MODIFIER =  plugin.getConfig().getDouble("explosion-modifier");
        MAX_POWER = plugin.getConfig().getInt("firework-power");
        EXP_BONUS = plugin.getConfig().getInt("exp-bonus");
    }
}
