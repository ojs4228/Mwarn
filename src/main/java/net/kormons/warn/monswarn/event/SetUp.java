package net.kormons.warn.monswarn.event;

import com.google.common.util.concurrent.Service;
import net.kormons.warn.monswarn.MonsWarn;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class SetUp implements Listener {

    private static MonsWarn instance;

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event) {

        OfflinePlayer joinPlayer = event.getPlayer();

        if (!joinPlayer.hasPlayedBefore()) {

            instance = MonsWarn.getInstance();
            instance.getConfig().set("경고." + joinPlayer.getName() + ".경고수", 0);

            instance.saveConfig();
        }

    }

}
