package ca.esplendor.optimization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by chenzheng on 15-09-10.
 */
public class FlexTeam2 {

    private QB qb;
    private List<RB> rbList = new ArrayList<RB>();
    private List<WR> wrList = new ArrayList<WR>();
    private TE te;
    private K k;
    private DEF def;

    private List<Player> teamMembers = new ArrayList<Player>();

    private final double budget = 60000;
    private final int MAX_RB_SIZE = 3;
    private final int MAX_WR_SIZE = 3;

    public FlexTeam2(){}


    public void updateRbMemberIfRequired(RB member) {
        teamMembers.removeAll(rbList);
        rbList.add(member);
        Collections.sort(rbList, Player.DollarPerPointsComparator);
        if (rbList.size() > MAX_RB_SIZE) {

            rbList = rbList.subList(0, MAX_RB_SIZE - 1);
        }
        teamMembers.addAll(rbList);
    }

    public void updateWrMemberIfRequired(WR member) {
        teamMembers.removeAll(wrList);
        wrList.add(member);
        Collections.sort(wrList, Player.DollarPerPointsComparator);
        if (wrList.size() > MAX_WR_SIZE) {
            wrList = wrList.subList(0, MAX_WR_SIZE - 1);
        }
        teamMembers.addAll(wrList);
    }

    public double getTeamCost() {
        double total = 0;
        if (!hasAllTeamMembers()) return total;
        else {
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

    public void updateQbIfRequired(QB member) {
        if (member == null) return;
        if (qb == null) {
            qb = member;
            teamMembers.add(qb);
            return;
        }

        if (member.getDollarPerPoints() < qb.getDollarPerPoints()){
            teamMembers.remove(qb);
            qb = member;
            teamMembers.add(qb);
        }
    }

    public TE getTe() {
        return te;
    }

    public void updateTeIfRequired(TE member) {
        if (member == null) return;
        if (te == null) {
            this.te = member;
            teamMembers.add(this.te);
            return;
        }

        if (member.getDollarPerPoints() < te.getDollarPerPoints()){
            teamMembers.remove(this.te);
            this.te = member;
            teamMembers.add(this.te);
        }
    }

    public K getK() {
        return k;
    }

    public void updateKIfRequired(K member) {
        if (member == null) return;
        if (k == null) {
            k = member;
            teamMembers.add(k);
            return;
        }

        if (member.getDollarPerPoints() < k.getDollarPerPoints()){
            teamMembers.remove(k);
            k = member;
            teamMembers.add(k);
        }
    }

    public DEF getDef() {
        return def;
    }

    public void updateDefIfRequired(DEF member) {
        if (member == null) return;
        if (def == null) {
            def = member;
            teamMembers.add(def);
            return;
        }

        if (member.getDollarPerPoints() < def.getDollarPerPoints()){
            teamMembers.remove(def);
            def = member;
            teamMembers.add(def);
        }
    }

    public List<RB> getRbList() {
        Collections.sort(rbList, Player.DollarPerPointsComparator);
        return rbList;
    }


    public List<WR> getWrList() {
        Collections.sort(wrList, Player.DollarPerPointsComparator);
        return wrList;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Cost::").append(getTeamCost())
        .append("Pro Pts::").append(getTeamProjectedPoints()).append("\n");
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
}
