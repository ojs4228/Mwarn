package net.kormons.warn.monswarn.command;

import net.kormons.warn.monswarn.MonsWarn;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CheckWarn implements CommandExecutor {

    private static MonsWarn instance;
    public String prefix;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (label.equalsIgnoreCase("경고확인")) {

            prefix = "§e§l[§f§lMwarn§e§l]§f";

            Player player = (Player) sender;

            int num = MonsWarn.checkWarn(player);

            player.sendMessage(prefix + "당신의 경고수는" + num + "입니다.");
        }

        return true;
    }
}

