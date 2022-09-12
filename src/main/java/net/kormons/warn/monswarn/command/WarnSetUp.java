package net.kormons.warn.monswarn.command;

import net.kormons.warn.monswarn.MonsWarn;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WarnSetUp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (label.equalsIgnoreCase("경고셋업")) {

            Player player = (Player) sender;

            if (player.isOp()) {

                MonsWarn.setUpWarn(player);

                player.sendMessage("경고 셋업 완료");

            } else {
                player.sendMessage("op전용 명령어 입니다");
            }


        }


        return true;
    }
}
