package rockaBe.testplugin;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class App extends JavaPlugin
{
     public class EnderbogenListener implements Listener {
         @EventHandler
         public void onEvent(ProjectileHitEvent event) {
             Projectile geschoss = event.getEntity();
             if(geschoss.getType() == EntityType.ARROW) {
                 ProjectileSource schuetze = geschoss.getShooter();
                 if(schuetze instanceof Player) {
                     Player spieler = (Player) schuetze;
                     ItemStack gegenstand = spieler.getInventory().getItemInMainHand();
                     ItemMeta metaData = gegenstand.getItemMeta();
                     if(metaData.getDisplayName().equals("Enderbogen")){
                         spieler.teleport(geschoss.getLocation());
                     }
                 }
             }
         }
     }
    public void onEnable() {
        getLogger().info("register enderbogen");
        ItemStack enderbogen = new ItemStack(Material.BOW);
        ItemMeta metaData = enderbogen.getItemMeta();
        metaData.setDisplayName("Enderbogen");
        enderbogen.setItemMeta(metaData);
        enderbogen.addUnsafeEnchantment(Enchantment.LUCK, 1);

        ShapedRecipe enderbogenrezept = new ShapedRecipe(enderbogen);
        enderbogenrezept.shape("*S/", "S*/", "*S/");
        enderbogenrezept.setIngredient('/', Material.STICK);
        enderbogenrezept.setIngredient('S', Material.STRING);
        enderbogenrezept.setIngredient('*', Material.ENDER_PEARL);
        this.getServer().addRecipe(enderbogenrezept);

        PluginManager pluginManager = this.getServer().getPluginManager();
        EnderbogenListener listener = new EnderbogenListener();
        pluginManager.registerEvents(listener, this);
    }
    
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
    }
}
