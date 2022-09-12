package net.kormons.warn.monswarn;

import net.kormons.warn.monswarn.command.*;
import net.kormons.warn.monswarn.event.SetUp;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class MonsWarn extends JavaPlugin {


    private static MonsWarn instance;
    public String prefix = "§e§l[§f§lMwarn§e§l]§f";

    @Override
    public void onEnable() {

        System.out.println("플러그인 로드 완료");

        this.getCommand("경고지급").setExecutor(new AddWarn());
        this.getCommand("경고확인").setExecutor(new CheckWarn());
        this.getCommand("경고삭제").setExecutor(new RemoveWarn());
        this.getCommand("경고리로드").setExecutor(new ReloadConfig());
        this.getCommand("경고셋업").setExecutor(new WarnSetUp());

        this.getServer().getPluginManager().registerEvents(new SetUp(), this);

        instance = this;
        instance.saveDefaultConfig();


    }

    @Override
    public void onDisable() {

        System.out.println("플러그인 언로드 완료");

        instance.saveConfig();
    }

    public static MonsWarn getInstance() {
        return instance;
    }

    public static int addWarn(Player player, OfflinePlayer warnPlayer, int num, String reason) {

        String prefix;
        OfflinePlayer play = warnPlayer;
        prefix = "§e§l[§f§lMwarn§e§l]§f";


        instance = MonsWarn.getInstance();
        int count = instance.getConfig().getInt("경고." + play.getUniqueId() + ".경고수");
        int i = count + num;
        instance.getConfig().set("경고." + play.getUniqueId() + ".경고수", i);

        instance.saveConfig();

        if (count == 5 || count > 5 || i == 5 || i > 5) {

            Player player1 = (Player) play;

            player1.banPlayerIP("누적경고 5회 이상");

            return 1;

        }

        return 0;
    }

    public static boolean removeWarn(Player player, int num, OfflinePlayer play) {

        instance = MonsWarn.getInstance();

        int count = instance.getConfig().getInt("경고." + play.getUniqueId() + ".경고수");
        int i = count - num;

        if (i < 0) {
            return false;
        }
        instance.getConfig().set("경고." + play.getUniqueId() + ".경고수", i);

        instance.saveConfig();

        return true;

    }

    public static int checkWarn(Player player) {

        instance = MonsWarn.getInstance();
        int num = instance.getConfig().getInt("경고." + player.getUniqueId() + ".경고수");

        return num;
    }

    public static void setUpWarn(Player player) {

        instance = MonsWarn.getInstance();
        instance.getConfig().set("경고." + player.getUniqueId() + ".플레이어 닉네임", player.getName());
        instance.getConfig().set("경고." + player.getUniqueId() + ".경고수", 0);


        instance.saveConfig();

    }
}
