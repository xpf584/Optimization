package ca.esplendor.optimization;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by chenzheng on 15-09-15.
 *
 * This class will control the available team members
 */


public class FlexTeamManager {
    static List<Player> playerList = new ArrayList<Player>();
    static QB qb = null;
    static DEF def = null;
    static TE te = null;
    static FLEX flex = null;
    static RB rb1 = null;
    static RB rb2 = null;
    static WR wr1 = null;
    static WR wr2 = null;

    static {
        playerList.add(qb);
        playerList.add(def);
        playerList.add(te);
        playerList.add(flex);
        playerList.add(rb1);
        playerList.add(rb2);
        playerList.add(wr1);
        playerList.add(wr2);
    }






}
