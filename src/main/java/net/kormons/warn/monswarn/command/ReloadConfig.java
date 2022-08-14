package net.kormons.warn.monswarn.command;

import net.kormons.warn.monswarn.MonsWarn;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ReloadConfig implements CommandExecutor {

    private static MonsWarn instance;

    @Override
    public boolean onCommand( CommandSender sender,  Command command, String label, String[] args) {

        if(label.equalsIgnoreCase("경고리로드")){


            Player player = (Player) sender;

            if(player.isOp()){

                instance = MonsWarn.getInstance();
                instance.reloadConfig();

                player.sendMessage("경고 콘피크를 리로드 했습니다");

            }else {
                player.sendMessage("해당 명령어는 관리자 권한을 가진분만 사용 가능합니다.");
            }



        }
        return true;
    }
}
