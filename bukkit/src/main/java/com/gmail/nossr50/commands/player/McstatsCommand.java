package com.gmail.nossr50.commands.player;

import com.gmail.nossr50.core.config.MainConfig;
import com.gmail.nossr50.core.data.UserManager;
import com.gmail.nossr50.core.locale.LocaleLoader;
import com.gmail.nossr50.core.util.commands.CommandUtils;
import com.gmail.nossr50.core.util.scoreboards.ScoreboardManager;
import com.google.common.collect.ImmutableList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;

public class McstatsCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (CommandUtils.noConsoleUsage(sender)) {
            return true;
        }

        if (!CommandUtils.hasPlayerDataKey(sender)) {
            return true;
        }

        switch (args.length) {
            case 0:
                Player player = (Player) sender;

                if (MainConfig.getInstance().getStatsUseBoard() && MainConfig.getInstance().getScoreboardsEnabled()) {
                    ScoreboardManager.enablePlayerStatsScoreboard(player);

                    if (!MainConfig.getInstance().getStatsUseChat()) {
                        return true;
                    }
                }

                player.sendMessage(LocaleLoader.getString("Stats.Own.Stats"));
                player.sendMessage(LocaleLoader.getString("mcMMO.NoSkillNote"));

                CommandUtils.printGatheringSkills(player);
                CommandUtils.printCombatSkills(player);
                CommandUtils.printMiscSkills(player);

                int powerLevelCap = MainConfig.getInstance().getPowerLevelCap();

                if (powerLevelCap != Integer.MAX_VALUE) {
                    player.sendMessage(LocaleLoader.getString("Commands.PowerLevel.Capped", UserManager.getPlayer(player).getPowerLevel(), powerLevelCap));
                } else {
                    player.sendMessage(LocaleLoader.getString("Commands.PowerLevel", UserManager.getPlayer(player).getPowerLevel()));
                }

                return true;

            default:
                return false;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return ImmutableList.of();
    }
}