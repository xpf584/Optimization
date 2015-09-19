package ca.esplendor.optimization;

import java.util.*;

/**
 * Created by chenzheng on 15-09-10.
 * Note:: When insert, make sure the best rate player
 */
public class FDTeam {

    private QB qb;
    private TE te;
    private K k;
    private DEF def;
    private List<RB> rbList = new ArrayList<RB>();
    private List<WR> wrList = new ArrayList<WR>();

    private Set<String> teamMemberNames = new HashSet<String>();

    public static final double budget = 60000;
    public static final int MAX_RB_SIZE = 2;
    public static final int MAX_WR_SIZE = 3;

    public FDTeam(){}

    public boolean isWithinBudget() {
        return getTeamCost() <= budget;
    }


    public double getTeamCost() {
        double total = 0;
        total += qb != null ? qb.getPrice() : 0;
        for (RB rb : rbList) {
            total += rb.getPrice();
        }
        for (WR wr : wrList) {
            total += wr.getPrice();
        }
        total += te != null ? te.getPrice() : 0;
        total += k != null ? k.getPrice() : 0;
        total += def != null ? def.getPrice(): 0;
        return total;
    }

    public double getTeamPoints(){
        double points = 0;

        points += qb != null ? qb.getProjectPoints() : 0;
        for (RB rb : rbList) {
            points += rb.getProjectPoints();
        }
        for (WR wr : wrList) {
            points += wr.getProjectPoints();
        }
        points += te != null ? te.getProjectPoints() : 0;
        points += k != null ? k.getProjectPoints() : 0;
        points += def != null ? def.getProjectPoints(): 0;
        return points;

    }

    public void refreshTeamMemberNames() {
        teamMemberNames.clear();
        if (qb != null) {
            teamMemberNames.add(qb.getName());
        }

        for (RB member : rbList) {
            teamMemberNames.add(member.getName());
        }
        for (WR member : wrList) {
            teamMemberNames.add(member.getName());
        }
        if (te != null) {
            teamMemberNames.add(te.getName());
        }
        if (def != null) {
            teamMemberNames.add(def.getName());
        }
        if (k != null) {
            teamMemberNames.add(k.getName());
        }
    }


    public boolean isTeamReady(){
        return hasAllTeamMembers() && getTeamCost() <= budget;
    }

    public boolean hasAllQb() {
        return qb != null;
    }

    public boolean hasAllRb() {
        return rbList.size() == MAX_RB_SIZE;
    }

    public boolean hasAllWr(){
        return wrList.size() == MAX_WR_SIZE;
    }

    public boolean hasAllTe(){
        return te != null;
    }

    public boolean hasAllK() {
        return k != null;
    }

    public boolean hasAllDef() {
        return def != null;
    }

    public boolean hasAllTeamMembers(){
        return hasAllQb() && hasAllRb() && hasAllWr() && hasAllTe() && hasAllK() && hasAllDef();
    }

    public QB getQb() {
        return qb;
    }



    public TE getTe() {
        return te;
    }


    public K getK() {
        return k;
    }

    public DEF getDef() {
        return def;
    }

    public boolean setQb(QB qb) {
        this.qb = qb;
        refreshTeamMemberNames();
        return true;
    }

    public boolean setTe(TE te) {
        this.te = te;
        refreshTeamMemberNames();
        return true;
    }

    public boolean setK(K k) {
        this.k = k;
        refreshTeamMemberNames();
        return true;
    }

    public boolean setDef(DEF def) {
        this.def = def;
        refreshTeamMemberNames();
        return true;
    }

    public List<RB> getRbList(Comparator<Player> comparator) {
        Collections.sort(rbList, comparator);
        return rbList;
    }

    public List<WR> getWrList(Comparator<Player> comparator) {
        Collections.sort(wrList, comparator);
        return wrList;
    }

    public List<RB> getRbList() {
        return rbList;
    }

    public boolean setRbList(List<RB> rbList) {
        if (rbList.size() > MAX_RB_SIZE) {
            rbList = rbList.subList(0, MAX_RB_SIZE);
        }
        this.rbList = rbList;
        refreshTeamMemberNames();
        return true;
    }

    public boolean setWrList(List<WR> wrList) {
        if (wrList.size() > MAX_WR_SIZE) {
            wrList = wrList.subList(0, MAX_WR_SIZE);
        }
        this.wrList = wrList;
        refreshTeamMemberNames();
        return true;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Cost::").append(getTeamCost())
        .append("Pro Pts::").append(getTeamPoints()).append("\n");
        if (!isTeamReady()) {
            builder.append("Team is not ready yet...\n");
        }

        if (qb != null) {
            builder.append(qb).append("\n");
        }
        if (rbList != null && rbList.size() > 0) {
            for (RB rb : rbList) {
                builder.append(rb).append("\n");
            }
        }
        if (wrList != null && wrList.size() > 0) {
            for (WR wr : wrList) {
                builder.append(wr).append("\n");
            }
        }
        if (te != null) {
            builder.append(te).append("\n");
        }
        if (k != null) {
            builder.append(k).append("\n");
        }
        if (def != null) {
            builder.append(def).append("\n");
        }
        return builder.toString();
    }

    public Set<String> getTeamMemberNames() {
        return teamMemberNames;
    }

    public boolean hasMember(Player player) {
        return teamMemberNames.contains(player.getName());
    }
}
