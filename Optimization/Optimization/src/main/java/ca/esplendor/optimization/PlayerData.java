package ca.esplendor.optimization;

import java.util.*;

/**
 * Created by chenzheng on 15-09-10.
 *
 * This is for testing only.
 */
public class PlayerData {
    private static List<Player> playerList = new ArrayList<Player>();
    private static List<QB> qbList = new ArrayList<QB>();
    private static List<RB> rbList = new ArrayList<RB>();
    private static List<WR> wrList = new ArrayList<WR>();
    private static List<TE> teList = new ArrayList<TE>();
    private static List<K> kList = new ArrayList<K>();
    private static List<DEF> defList = new ArrayList<DEF>();
    private static List<FLEX> flexList = new ArrayList<FLEX>();
    private static Comparator<Player> desiredComparator = Player.DollarPerPointsComparator;
    static {



    }
    private static void loadSampleData(){
        clearPlayerList();
        playerList.add(new QB("Taylor, Tyrod", 5000, 295));
        playerList.add(new QB("Carr, Derek", 6300, 371));
        playerList.add(new QB("Bortles, Blake", 6400, 376));
        playerList.add(new QB("Flacco, Joe", 8200, 466));
        playerList.add(new QB("Brees, Drew",  8900, 476));
        playerList.add(new QB("Brown, Antonio", 9300, 481));


        playerList.add(new WR("Adams, Davante",  5500, 443));
        playerList.add(new WR("Brown, Antonio", 9300, 481));
        playerList.add(new WR("Johnson, Calvin", 8500, 486));
        playerList.add(new WR("Johnson, Charles",5600, 524));
        playerList.add(new WR("Coleman, Brandon", 4500, 543));


        playerList.add(new RB("Abdullah, Ameer", 5900, 529));
        playerList.add(new RB("Forsett, Justin", 7800, 542));
        playerList.add(new RB("Lacy, Eddie", 8500, 540));
        playerList.add(new RB("Miller, Lamar", 7300, 542));
        playerList.add(new RB("Ivory, Chris", 6400, 544));
        playerList.add(new RB("Stewart, Jonathan", 7100, 565));

        playerList.add(new TE("Olsen, Greg", 5900, 535));
        playerList.add(new TE("Gronkowski, Rob", 8100, 522));
        playerList.add(new TE("Bennett, Martellus", 6200, 566));
        playerList.add(new TE("Graham, Jimmy", 6900, 658));
        playerList.add(new TE("Cameron, Jordan", 5500, 669));

        playerList.add(new K("Bailey, Dan",  5000, 553));
        playerList.add(new K("Crosby, Mason",  5100, 555));
        playerList.add(new K("Parkey, Cody",  5000, 557));
        playerList.add(new K("Catanzaro, Chandler", 4700, 568));
        playerList.add(new K("Franks, Andrew", 4500, 572));

        playerList.add(new DEF("New York Jets",4400, 599));
        playerList.add(new DEF("Tampa Bay Buccaneers",  4500, 626));
        playerList.add(new DEF("Arizona Cardinals", 4300, 633));
        playerList.add(new DEF("Miami Dolphins", 4700, 636));
        playerList.add(new DEF("Oakland Raiders", 4000, 637));
    }

    public static List<Player> getPlayerList() {
        playerList.sort(desiredComparator);
        return playerList;
    }

    public static void setPlayerList(List<Player> playerList) {
        PlayerData.playerList = playerList;
    }

    public static void loadPlayerList(List<Map<String, String>> playerMapList, List<String> filteredOutTeam, Double maxRate){
        clearPlayerList();
        for (Map<String, String> map : playerMapList) {
            Position position = Position.fromCode(map.get("Pos"));
            String playerName = map.get("Player");
            if (playerName.isEmpty()) throw new RuntimeException("Player Name is Empty!");
            String priceString = map.get("Price").replace("$", "");
            String rateString = map.get("$/Point");
            double rate = rateString.isEmpty() ? 0 : new Double(rateString);
            String projectedPointsString = map.get("FFP").replace("$", "");
            double projectedPoints =  projectedPointsString.isEmpty() ? 0 : new Double(projectedPointsString);
            double price;
            try {
                price = priceString.isEmpty() ? 0 : new Double(priceString);
            } catch (Exception e) {
                price = rate * projectedPoints;
            }
            String team = map.get("Team");
            if (filteredOutTeam.contains(team)) {
                continue;
            }
            if (maxRate != null && maxRate > 0 && rate > maxRate) {
                continue;
            }
            if (position == Position.QB) {
                playerList.add(new QB(playerName, price, projectedPoints, rate));
                qbList.add(new QB(playerName, price, projectedPoints, rate));
            } else if (position == Position.RB) {
                playerList.add(new RB(playerName, price, projectedPoints, rate));
                rbList.add(new RB(playerName, price, projectedPoints, rate));
                flexList.add(new RB(playerName, price, projectedPoints, rate));
            } else if (position == Position.WR) {
                playerList.add(new WR(playerName, price, projectedPoints, rate));
                wrList.add(new WR(playerName, price, projectedPoints, rate));
                flexList.add(new WR(playerName, price, projectedPoints, rate));
            } else if (position == Position.TE) {
                playerList.add(new TE(playerName, price, projectedPoints, rate));
                teList.add(new TE(playerName, price, projectedPoints, rate));
                flexList.add(new TE(playerName, price, projectedPoints, rate));
            } else if (position == Position.K) {
                playerList.add(new K(playerName, price, projectedPoints, rate));
                kList.add(new K(playerName, price, projectedPoints, rate));
            } else if (position == Position.DEF) {
                playerList.add(new DEF(playerName, price, projectedPoints, rate));
                defList.add(new DEF(playerName, price, projectedPoints, rate));
            } else {
                // Unknown player.
            }
        }
    }

    public static void setDesiredComparator(Comparator<Player> desiredComparator) {
        PlayerData.desiredComparator = desiredComparator;
    }

    private static void clearPlayerList() {
        playerList.clear();
        qbList.clear();
        rbList.clear();
        wrList.clear();
        teList.clear();
        kList.clear();
        defList.clear();
        flexList.clear();
    }

    public static List<QB> getQbList() {
        Collections.sort(qbList, desiredComparator);
        return qbList;
    }

    public static List<RB> getRbList() {
        Collections.sort(rbList, desiredComparator);
        return rbList;
    }


    public static List<WR> getWrList() {
        Collections.sort(wrList, desiredComparator);
        return wrList;
    }


    public static List<TE> getTeList() {
        Collections.sort(teList, desiredComparator);
        return teList;
    }

    public static List<FLEX> getFlexList() {
        Collections.sort(flexList, desiredComparator);
        return flexList;
    }


    public static List<K> getkList() {
        Collections.sort(kList, desiredComparator);
        return kList;
    }


    public static List<DEF> getDefList() {
        Collections.sort(defList, desiredComparator);
        return defList;
    }


}
