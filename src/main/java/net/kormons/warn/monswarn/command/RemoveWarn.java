package net.kormons.warn.monswarn.command;

import net.kormons.warn.monswarn.MonsWarn;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RemoveWarn implements CommandExecutor {

    private static MonsWarn instance;
    public String prefix;

    @Override
    public boolean onCommand(CommandSender sender,Command command,String label,String[] args) {

        if (label.equalsIgnoreCase("경고삭제")) {
            
            Player player = (Player)sender;
            if (player.isOp()) {

                if(args.length ==  0 || args.length ==  1 ){

                    player.sendMessage("사용법:/경고지급 (경고수) (플레이어) (사유)");

                }
                
                if (args.length != 1 && args.length == 2) {
                    
                    OfflinePlayer play = Bukkit.getOfflinePlayer(args[1]);
                    int num = Integer.parseInt(args[0]);
                    
                    if (!play.hasPlayedBefore()) {
                        
                        player.sendMessage("해당 플레이어는 접속한적이 없습니다.");
                        
                        return true;
                    }

                    instance = MonsWarn.getInstance();
                    
                    int count = instance.getConfig().getInt("경고." + play.getName() + ".경고수");
                    int i = count - num;

                    if (i < 0){
                        player.sendMessage("경고 삭제시" + play.getName() +"님의 경고가 음수가 되므로 경고를 삭제할 수 없습니다");

                        return true;
                    }

                    instance.getConfig().set("경고." + play.getName() + ".경고수", i);
                    
                    player.sendMessage(play.getName() + "님에게 경고" + num + "만큼을 삭제 했습니다.");
                    
                    instance.saveConfig();
                }
            } else {
                String prefix = "§f§l[§c§l경고§c§l]§f ";

                player.sendMessage(prefix + "당신은 이 명령어를 사용할 권한이 없습니다.");
            }
        }
        return true;
    }
}
