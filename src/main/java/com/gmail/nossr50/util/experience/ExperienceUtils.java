package com.gmail.nossr50.util.experience;

import com.gmail.nossr50.datatypes.experience.XPGainReason;
import com.gmail.nossr50.datatypes.experience.XPGainSource;
import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import com.gmail.nossr50.datatypes.skills.PrimarySkillType;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class ExperienceUtils {
    private ExperienceUtils() {}

    /**
     * Applies an experience gain
     *
     * @param primarySkillType Skill being used
     * @param xp Experience amount to add
     */
    public static void applyXpGain(@NotNull McMMOPlayer mmoPlayer, @NotNull PrimarySkillType primarySkillType, float xp, @NotNull XPGainReason xpGainReason, @NotNull XPGainSource xpGainSource) {
        mmoPlayer.getExperienceManager().applyXpGain(primarySkillType, xp, xpGainReason, xpGainSource);
    }

    public static void processPostXpEvent(@NotNull McMMOPlayer mmoPlayer, @NotNull PrimarySkillType primarySkillType, @NotNull Plugin plugin, @NotNull XPGainSource xpGainSource) {
        mmoPlayer.getExperienceManager().processPostXpEvent(primarySkillType, plugin, xpGainSource);
    }

    /**
     * Updates a players level
     *
     * @param primarySkillType The skill to check
     */
    public static void updateLevelStats(@NotNull McMMOPlayer mmoPlayer, @NotNull PrimarySkillType primarySkillType, @NotNull XPGainReason xpGainReason, @NotNull XPGainSource xpGainSource) {
        mmoPlayer.getExperienceManager().updateLevelStats(primarySkillType, xpGainReason, xpGainSource);
    }
}