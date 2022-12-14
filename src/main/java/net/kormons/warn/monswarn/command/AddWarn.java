package net.kormons.warn.monswarn.command;

import net.kormons.warn.monswarn.MonsWarn;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddWarn implements CommandExecutor {
    private static MonsWarn instance;
    public String prefix;


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (label.equalsIgnoreCase("경고지급")) {

            prefix = "§e§l[§f§lMwarn§e§l]§f";

            Player player = (Player) sender;

            if (player.isOp()) {

                if (args.length != 1) {

                    player.sendMessage("사용법:/경고지급 (경고수) (플레이어) (사유)");

                    OfflinePlayer play;

                    if (args.length == 2) {

                        play = Bukkit.getOfflinePlayer(args[1]);
                        int num = Integer.parseInt(args[0]);

                        if (!play.hasPlayedBefore()) {
                            player.sendMessage("해당 플레이어는 접속한적이 없습니다.");
                            return true;
                        }
                    } else if (args.length == 3) {


                        play = Bukkit.getOfflinePlayer(args[1]);
                        int num = Integer.parseInt(args[0]);
                        String reason = args[2];
                        instance = MonsWarn.getInstance();
                        int warn = MonsWarn.addWarn(player, play, num, reason);

                        if (warn == 0) {

                            player.sendMessage(play.getName() + "님에게 경고" + num + "만큼을 주었습니다.");

                            Bukkit.broadcastMessage("     ");
                            Bukkit.broadcastMessage("------경고 안내------");
                            Bukkit.broadcastMessage(prefix + play.getName() + "님에게 " + num + " 만큼의 경고를 지급 했습니다");
                            Bukkit.broadcastMessage(prefix + "사유: " + reason);
                            Bukkit.broadcastMessage(prefix + "처리자:" + player.getName());
                            Bukkit.broadcastMessage("-------------------");
                            Bukkit.broadcastMessage("     ");

                        } else if (warn == 1) {

                            player.sendMessage(play.getName() + "님에게 경고" + num + "만큼을 주었습니다.");

                            Bukkit.broadcastMessage("     ");
                            Bukkit.broadcastMessage("------경고 안내------");
                            Bukkit.broadcastMessage(prefix + play.getName() + "님에게 " + num + " 만큼의 경고를 지급 했습니다");
                            Bukkit.broadcastMessage(prefix + "사유: " + reason);
                            Bukkit.broadcastMessage(prefix + "처리자:" + player.getName());
                            Bukkit.broadcastMessage(prefix + "누적경고 5회로 인하여, 밴 처리 되었습니다.");
                            Bukkit.broadcastMessage("-------------------");
                            Bukkit.broadcastMessage("     ");

                        }
                    }
                }
            } else {

                player.sendMessage(prefix + "당신은 이 명령어를 사용할 권한이 없습니다.");
                return true;
            }
        }


        return true;
    }
}


