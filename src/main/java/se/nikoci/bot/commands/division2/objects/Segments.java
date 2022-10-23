package se.nikoci.bot.commands.division2.objects;

import lombok.Data;

@Data public class Segments {
    String type;
    Metadata metadata;
    String expiryDate;
    Stats stats;

    @Data public static class Metadata {
        String name;
    }

    @Data public static class Stats {
        Single timePlayed;
        Single killsPvP;
        Single killsNpc;
        Single killsSkill;
        Single headshots;
        Single itemsLooted;
        Single xPTotal;
        Single xPClan;
        Single specialization;
        Single killsSpecializationSharpshooter;
        Single killsSpecializationSurvivalist;
        Single killsSpecializationDemolitionist;
        Single eCreditBalance;
        Single commendationCount;
        Single commendationScore;
        Single latestGearScore;
        Single highestPlayerLevel;
        Single xPPve;
        Single timePlayedPve;
        Single killsRoleElite;
        Single killsRoleNamed;
        Single killsFactionBlackbloc;
        Single killsFactionCultists;
        Single killsFactionMilitia;
        Single killsFactionEndgame;
        Single rankDZ;
        Single xPDZ;
        Single timePlayedDarkZone;
        Single roguesKilled;
        Single timePlayedRogue;
        Single timePlayedRogueLongest;
        Single killsFactionDzBlackbloc;
        Single killsFactionDzCultists;
        Single killsFactionDzMilitia;
        Single killsFactionDzEndgame;
        Single killsRoleDzElite;
        Single killsRoleDzNamed;
        Single latestConflictRank;
        Single xPPvp;
        Single timePlayedConflict;
        Single killsBleeding;
        Single killsBurning;
        Single killsShocked;
        Single killsEnsnare;
        Single killsHeadshot;
        Single killsWeaponShotgun;
        Single killsWeaponSubMachinegun;
        Single killsWeaponMounted;
        Single killsWeaponPistol;
        Single killsWeaponRifle;
        Single killsWeaponGrenade;
        Single itemsLootedPerMin;
        Single killsPvPPerMin;
        Single killsNpcPerMin;
        Single playersKilled;
        Single killsSkillPerMin;


        @Data public class Single {
            String rank;
            String percentile;
            String displayName;
            String displayCategory;
            String category;
            String value;
            String displayValue;
            String displayType;
        }
    }
}
