package ca.esplendor.optimization;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by chenzheng on 15-09-10.
 */
public class Team  {

    private QB qb;
    private List<RB> rbList = new ArrayList<RB>();
    private List<WR> wrList = new ArrayList<WR>();
    private TE te;
    private K k;
    private DEF def;

    private List<Player> teamMembers = new ArrayList<Player>();

    private final double budget = 60000;
    private final int RB_SIZE = 2;
    private final int WR_SIZE = 3;

    public Team(){}

    public static Team cloneTeam(Team toBeCloned) {
        if (toBeCloned != null) {
            Team newTeam = new Team();
            newTeam.setQb(QB.clone(toBeCloned.getQb()));
            newTeam.setDef(DEF.clone(toBeCloned.getDef()));
            newTeam.setK(K.clone(toBeCloned.getK()));
            newTeam.setTe(TE.clone(toBeCloned.getTe()));
            List<RB> rbList = new ArrayList<RB>();
            for (RB rb : toBeCloned.getRbList()) {
                rbList.add(RB.clone(rb));
            }
            newTeam.setRbList(rbList);

            List<WR> wrList = new ArrayList<WR>();
            for (WR wr : toBeCloned.getWrList()) {
                wrList.add(WR.clone(wr));
            }
            newTeam.setWrList(wrList);

            List<Player> playerList = new ArrayList<Player>();
            for (Player player : toBeCloned.getTeamMembers()) {
                playerList.add(Player.clone(player));
            }
            newTeam.setTeamMembers(playerList);
            return newTeam;
        }
        return null;
    }

    public void addRb(RB member) {
        if (rbList.size() < RB_SIZE && !teamMembers.contains(member)) {
            rbList.add(member);
            teamMembers.add(member);
        }
    }

    public void addWr(WR member) {
        if (wrList.size() < WR_SIZE && !teamMembers.contains(member)) {
            wrList.add(member);
            teamMembers.add(member);
        }
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

    public double getTeamProjectedPoints(){
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

    public boolean hasTeamMeetBudgetRequirement(){
        return getTeamCost() <= budget;
    }

    public boolean hasAllQb() {
        return qb != null;
    }

    public boolean hasAllRb() {
        return rbList.size() == RB_SIZE;
    }

    public boolean hasAllWr(){
        return wrList.size() == WR_SIZE;
    }

    public boolean hasAllTe(){
        return te != null;
    }

    public boolean hasAllK(){
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

    public void setQb(QB member) {
        if (member == null) return;
        if (qb == null) {
            qb = member;
            teamMembers.add(qb);
        } else {
            teamMembers.remove(qb);
            qb = member;
            teamMembers.add(qb);
        }
    }

    public TE getTe() {
        return te;
    }

    public void setTe(TE member) {
        if (member == null) return;
        if (te == null) {
            te = member;
            teamMembers.add(te);
        } else {
            teamMembers.remove(te);
            te = member;
            teamMembers.add(te);
        }
    }

    public K getK() {
        return k;
    }

    public void setK(K member) {
        if (member == null) return;
        if (k == null) {
            k = member;
            teamMembers.add(k);
        } else {
            teamMembers.remove(k);
            k = member;
            teamMembers.add(k);
        }
    }

    public DEF getDef() {
        return def;
    }

    public void setDef(DEF member) {
        if (member == null) return;
        if (def == null) {
            def = member;
            teamMembers.add(def);
        } else {
            teamMembers.remove(def);
            def = member;
            teamMembers.add(def);
        }
    }

    public List<RB> getRbList() {
        return rbList;
    }


    public List<WR> getWrList() {
        return wrList;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Cost::").append(getTeamCost())
        .append("Pro Pts::").append(getTeamProjectedPoints()).append("\n");
        if (!hasAllTeamMembers()) {
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

    public static Comparator<Team> PointsDescComparator
            = new Comparator<Team>() {
        public int compare(Team t1, Team t2) {
            return new Double(t2.getTeamProjectedPoints()).compareTo(t1.getTeamProjectedPoints());
        }
    };

    public void setRbList(List<RB> rbList) {
        this.rbList = rbList;
    }

    public void setWrList(List<WR> wrList) {
        this.wrList = wrList;
    }

    public void setTeamMembers(List<Player> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public List<Player> getTeamMembers() {
        return teamMembers;
    }
}
