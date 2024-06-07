package plugin.untitledplugin;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.UUID;

public class Untitledplugin extends JavaPlugin implements Listener {

    private HashMap<UUID, Location> savedLocations = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {

        Player player = event.getPlayer();
        Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
        UUID playerUUID = player.getUniqueId();

        if (block.getType() == Material.STONE) {
            Location location = player.getLocation();
            savedLocations.put(playerUUID, location);
        } else if (block.getType() != Material.AIR && block.getType() != Material.STONE){
            Location savedLocation = savedLocations.get(playerUUID);
            player.teleport(savedLocation);
        }
    }
}