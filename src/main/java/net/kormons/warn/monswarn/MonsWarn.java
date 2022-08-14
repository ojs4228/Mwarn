package net.kormons.warn.monswarn;

import net.kormons.warn.monswarn.command.AddWarn;
import net.kormons.warn.monswarn.command.CheckWarn;
import net.kormons.warn.monswarn.command.ReloadConfig;
import net.kormons.warn.monswarn.command.RemoveWarn;
import net.kormons.warn.monswarn.event.SetUp;
import org.bukkit.plugin.java.JavaPlugin;

public final class MonsWarn extends JavaPlugin {
    

    private  static MonsWarn instance;
    public String prefix = "§e§l[§f§lMwarn§e§l]§f";

    @Override
    public void onEnable() {
        
        System.out.println("플러그인 로드 완료");

        this.getCommand("경고지급").setExecutor(new AddWarn());
        this.getCommand("경고확인").setExecutor(new CheckWarn());
        this.getCommand("경고삭제").setExecutor(new RemoveWarn());
        this.getCommand("경고리로드").setExecutor(new ReloadConfig());
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
}
