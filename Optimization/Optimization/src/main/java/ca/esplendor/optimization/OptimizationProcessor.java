package ca.esplendor.optimization;

import ca.esplendor.utils.ExcelLoader;
import org.apache.log4j.Logger;
import java.util.List;
import java.util.Map;


/**
 * Created by chenzheng on 15-09-10.
 */
public class OptimizationProcessor {
    final static Logger log = Logger.getLogger(OptimizationProcessor.class);

    public static void main2(String args[]) {
        List<Map<String, String>> mapList = ExcelLoader.readCsvIntoHashMap(ExcelLoader.inputFile);
        PlayerData.loadPlayerList(mapList);
        List<Player> playerList = PlayerData.getPlayerList();
        List<QB> qbList = PlayerData.getQbList();
        List<RB> rbList = PlayerData.getRbList();
        List<WR> wrList = PlayerData.getWrList();
        List<TE> teList = PlayerData.getTeList();
        List<K> kList = PlayerData.getkList();
        List<DEF> defList = PlayerData.getDefList();


        log.info("Filtered player list size: " + playerList.size());
        Team bestTeam = null;
        Team team = new Team();
        long numberOfTeamChecked = 0;
        long numberOfUnderBudgetTeams = 0;
        for (QB qb : qbList) {
            team.setQb(qb);
            for (TE te : teList) {
                team.setTe(te);
                for (K k : kList) {
                    team.setK(k);
                    for (DEF def: defList) {
                        team.setDef(def);
                        for(int rb1 = 0; rb1 < rbList.size(); rb1++) {
                            RB firstRb = rbList.get(rb1);
                            if (team.getRbList().size() >= 1) {
                                team.getRbList().remove(0);
                            }
                            team.addRb(firstRb);

                            for (int rb2 = rb1 + 1; rb2 < rbList.size(); rb2++) {
                                RB secondRb = rbList.get(rb2);
                                if (team.getRbList().size() >= 2) {
                                    team.getRbList().remove(1);
                                }
                                team.addRb(secondRb);
                                for (int wr1 = 0; wr1 < wrList.size(); wr1++) {
                                    WR firstWr = wrList.get(wr1);
                                    if (team.getWrList().size() >= 1) {
                                        team.getWrList().remove(0);
                                    }
                                    team.addWr(firstWr);

                                    for (int wr2 = wr1 + 1; wr2 < wrList.size(); wr2++) {
                                        WR secondWr = wrList.get(wr2);
                                        if (team.getWrList().size() >= 2) {
                                            team.getWrList().remove(1);
                                        }
                                        team.addWr(secondWr);
                                        for (int wr3 = wr2 + 1; wr3 < wrList.size(); wr3++) {
                                            WR thirdWr = wrList.get(wr3);
                                            if (team.getWrList().size() >= 3) {
                                                team.getWrList().remove(2);
                                            }
                                            team.addWr(thirdWr);
                                            numberOfTeamChecked++;
                                            if (numberOfTeamChecked % 1000000000 == 0) {
                                                log.info("numberOfTeamChecked::" + numberOfTeamChecked);
                                            }
                                            if (team.hasAllTeamMembers() && team.hasTeamMeetBudgetRequirement()) {
                                                numberOfUnderBudgetTeams ++;
                                                if (numberOfUnderBudgetTeams % 2 == 0) {
                                                    log.info("numberOfUnderBudgetTeams:: " + numberOfUnderBudgetTeams);
                                                }

                                                /*log.info(team.getTeamProjectedPoints());*/
                                                //System.out.println("New Team Costs:: " + team.getTeamCost());

                                                if (bestTeam == null || bestTeam.getTeamProjectedPoints() < team.getTeamProjectedPoints()) {
                                                    bestTeam =  Team.cloneTeam(team);
                                                    log.info("Found Better Team!!!!!!!!!!!!!");
                                                    log.info("New Team points:: " + bestTeam.getTeamProjectedPoints());
                                                    log.info("New Team Costs:: " + bestTeam.getTeamCost());
                                                    log.info(bestTeam);
                                                }
                                                //teamList.add(team);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        /*teamList.sort(Team.PointsDescComparator);
        for (Team teamItem : teamList) {
            .out.println(teamItem);
        }*/

    }


    public static void mainForFlex(String args[]) {
        List<Map<String, String>> mapList = ExcelLoader.readCsvIntoHashMap(ExcelLoader.inputFile);
        PlayerData.loadPlayerList(mapList);
        List<Player> playerList = PlayerData.getPlayerList();
        List<QB> qbList = PlayerData.getQbList();
        List<RB> rbList = PlayerData.getRbList();
        List<WR> wrList = PlayerData.getWrList();
        List<TE> teList = PlayerData.getTeList();
        List<DEF> defList = PlayerData.getDefList();
        List<FLEX> flexList = PlayerData.getFlexList();


        log.info("Filtered player list size: " + playerList.size());
        FlexTeam bestTeam = null;
        FlexTeam team = new FlexTeam();
        long numberOfTeamChecked = 0;
        long numberOfUnderBudgetTeams = 0;
        for (QB qb : qbList) {
            team.setQb(qb);
            for (TE te : teList) {
                team.setTe(te);
                    for (DEF def: defList) {
                        team.setDef(def);
                        for(int rb1 = 0; rb1 < rbList.size(); rb1++) {
                            RB firstRb = rbList.get(rb1);
                            if (team.getRbList().size() >= 1) {
                                team.getRbList().remove(0);
                            }
                            team.addRb(firstRb);

                            for (int rb2 = rb1 + 1; rb2 < rbList.size(); rb2++) {
                                RB secondRb = rbList.get(rb2);
                                if (team.getRbList().size() >= 2) {
                                    team.getRbList().remove(1);
                                }
                                team.addRb(secondRb);
                                for (int wr1 = 0; wr1 < wrList.size(); wr1++) {
                                    WR firstWr = wrList.get(wr1);
                                    if (team.getWrList().size() >= 1) {
                                        team.getWrList().remove(0);
                                    }
                                    team.addWr(firstWr);

                                    for (int wr2 = wr1 + 1; wr2 < wrList.size(); wr2++) {
                                        WR secondWr = wrList.get(wr2);
                                        if (team.getWrList().size() >= 2) {
                                            team.getWrList().remove(1);
                                        }
                                        team.addWr(secondWr);
                                        for (int flex = 0; flex < flexList.size(); flex++) {
                                            FLEX fx = flexList.get(flex);
                                            for (Player player : team.getTeamMembers()) {
                                                if (player.getName().equalsIgnoreCase(fx.getName())) {
                                                    continue;
                                                }
                                            }
                                            team.setFlex(fx);
                                            numberOfTeamChecked++;
                                            if (numberOfTeamChecked % 1000000000 == 0) {
                                                log.info("numberOfTeamChecked::" + numberOfTeamChecked);
                                            }
                                            if (team.hasAllTeamMembers() && team.hasTeamMeetBudgetRequirement()) {
                                                numberOfUnderBudgetTeams ++;
                                                if (numberOfUnderBudgetTeams % 2 == 0) {
                                                    log.info("numberOfUnderBudgetTeams:: " + numberOfUnderBudgetTeams);
                                                }

                                                /*log.info(team.getTeamProjectedPoints());*/
                                                //System.out.println("New Team Costs:: " + team.getTeamCost());

                                                if (bestTeam == null || bestTeam.getTeamProjectedPoints() < team.getTeamProjectedPoints()) {
                                                    bestTeam =  FlexTeam.cloneTeam(team);
                                                    log.info("Found Better Team!!!!!!!!!!!!!");
                                                    log.info("New Team points:: " + bestTeam.getTeamProjectedPoints());
                                                    log.info("New Team Costs:: " + bestTeam.getTeamCost());
                                                    log.info(bestTeam);
                                                }

                                                //teamList.add(team);
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        /*teamList.sort(Team.PointsDescComparator);
        for (Team teamItem : teamList) {
            .out.println(teamItem);
        }*/

    public static void main(String args[]) {
        List<Map<String, String>> mapList = ExcelLoader.readCsvIntoHashMap(ExcelLoader.inputFile);
        PlayerData.loadPlayerList(mapList);
        List<Player> playerList = PlayerData.getPlayerList();
        List<FLEX> flexList = PlayerData.getFlexList();

        FlexUpdateTeam team = new FlexUpdateTeam();
        for (Player player : playerList) {
            boolean updated = team.updateCostEffectiveMemberIfRequired(player);
            if (team.isTeamReady() && updated) {
                log.info(team);
            }
        }

        for (Player player : playerList) {
            if (!team.hasMember(player)) {
                boolean updated = team.replaceHighPointsMemberIfRequired(player);
                if (updated) {
                    log.info(team);
                }
            }
        }

        for (Player player : flexList) {
            if (!team.hasMember(player)) {
                boolean updated = team.replaceHighPointsMemberIfRequired(player);
                if (updated) {
                    log.info(team);
                }
            }
        }


    }
}

