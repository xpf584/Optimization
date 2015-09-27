package ca.esplendor.optimization;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenzheng on 15-09-15.
 *
 * This class will control the available team members
 */


public class FDTeamManager {
    public static void updateTeam(FDTeam team, Player newPlayer) {
        if (team.getTeamMemberNames().contains(newPlayer.getName())) {
            return;
        }
        if (newPlayer.getPosition() == Position.QB
                && !team.getQb().isBetterThan(newPlayer) && !team.getQb().isCheaperThan(newPlayer)) {
            QB qb = (QB) newPlayer;
            team.setQb(qb);
        } else if (newPlayer.getPosition() == Position.DEF
                && !team.getDef().isBetterThan(newPlayer) && !team.getDef().isCheaperThan(newPlayer)) {
            DEF def = (DEF) newPlayer;
            team.setDef(def);
        } else if (newPlayer.getPosition() == Position.TE
                && !team.getTe().isBetterThan(newPlayer) && !team.getTe().isCheaperThan(newPlayer))  {
            TE te = (TE) newPlayer;
            team.setTe(te);
        } else if (newPlayer.getPosition() == Position.K
                && !team.getK().isBetterThan(newPlayer) && !team.getK().isCheaperThan(newPlayer))  {
            K k = (K) newPlayer;
            team.setK(k);
        } else if (newPlayer.getPosition() == Position.RB) {
            List<RB> list = team.getRbList(Player.DollarPerPointsComparator);
            list.add((RB) newPlayer);
            list.sort(Player.DollarPerPointsComparator);
            list = list.subList(0, Math.min(list.size(), FDTeam.MAX_RB_SIZE));
            team.setRbList(list);
        } else if (newPlayer.getPosition() == Position.WR) {
            List<WR> list = team.getWrList(Player.DollarPerPointsComparator);
            list.add((WR) newPlayer);
            list.sort(Player.DollarPerPointsComparator);
            list = list.subList(0, Math.min(list.size(), FDTeam.MAX_WR_SIZE));
            team.setWrList(list);
        }
    }

    public static boolean  replaceTeamMemberIfPossible(FDTeam team, Player newPlayer) {
        boolean replaced = false;
        if (team.getTeamMemberNames().contains(newPlayer.getName())) {
            return replaced;
        }

        if (newPlayer.getPosition() == Position.QB) {
            if (shouldReplace(team, team.getQb(), newPlayer)
                    && isMemberReplacementWithinBudget(team, team.getQb(), newPlayer)) {
                team.setQb((QB) newPlayer);
                replaced = true;
            } else if (newPlayer.getPosition() == Position.DEF) {
                if (shouldReplace(team, team.getDef(), newPlayer)
                        && isMemberReplacementWithinBudget(team, team.getDef(), newPlayer)) {
                    team.setDef((DEF) newPlayer);
                    replaced = true;
                }
            } else if (newPlayer.getPosition() == Position.TE) {
                if (shouldReplace(team, team.getTe(), newPlayer)
                        && isMemberReplacementWithinBudget(team, team.getTe(), newPlayer)) {
                    team.setTe((TE) newPlayer);
                    replaced = true;
                }
            } else if (newPlayer.getPosition() == Position.K) {
                if (shouldReplace(team, team.getTe(), newPlayer)
                        && isMemberReplacementWithinBudget(team, team.getK(), newPlayer)) {
                    team.setK((K) newPlayer);
                    replaced = true;
                }
            } /*else if (newPlayer.getPosition() == Position.RB) {
            List<RB> list = team.getRbList(Player.DollarPerPointsComparator);
            list.add((RB) newPlayer);
            list.sort(Player.DollarPerPointsComparator);
            list = list.subList(0, Math.min(list.size(), FDTeam.MAX_RB_SIZE));
            team.setRbList(list);
        } else if (newPlayer.getPosition() == Position.WR) {
            List<WR> list = team.getWrList(Player.DollarPerPointsComparator);
            list.add((WR) newPlayer);
            list.sort(Player.DollarPerPointsComparator);
            list = list.subList(0, Math.min(list.size(), FDTeam.MAX_WR_SIZE));
            team.setWrList(list);
        }*/
        }
        return replaced;
    }

    public static <T extends Player> boolean shouldReplace(FDTeam team, T currentMember, T newMember) {
        return newMember.hasHigherProPointsThan(currentMember)
                && isMemberReplacementWithinBudget(team, currentMember, newMember);
    }

    private static <T extends Player> boolean isMemberReplacementWithinBudget(FDTeam team, T currentMember, T newMember) {
        double additionalCost = newMember.getPrice() - currentMember.getPrice();
        return (team.getTeamCost() + additionalCost) <= FDTeam.budget;
    }
}
