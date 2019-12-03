import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class NoSideTorch extends JavaPlugin implements Listener {

    @Override
    //Plugin enable function.  Needed to enable the no side torch mod.
    public void onEnable() {
        System.out.println("No Side Torch Plugin Started");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    //Plugin disable function.  Needed to disable the no side torch mod when server shuts down.
    public void onDisable() {
        System.out.println("No Side Torch Plugin stopped");
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onSideTorchPlacement(BlockPlaceEvent event)
    {
        Player player = event.getPlayer();
        String name = player.getPlayerListName();
        Block block = event.getBlock();
        if(event.getBlock().getType() == Material.WALL_TORCH)
        {
            Location loc = block.getLocation();
            Location belowLoc = new Location(loc.getWorld(), loc.getX(), loc.getY()-1, loc.getZ());
            Block belowBlock = belowLoc.getBlock();
            if(belowBlock.getType().isSolid())
            {
                player.sendMessage(name + " Thou Shall Not Side Torch!");
                event.setCancelled(true);
            }
        }
    }
    
