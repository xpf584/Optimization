package ca.esplendor.optimization;

import java.util.*;

/**
 * Created by chenzheng on 15-09-10.
 * Note:: When insert, make sure the best rate player
 */
public class FlexUpdateTeam {

    private QB qb;
    private TE te;
    private FLEX flex;
    private DEF def;
    private List<RB> rbList = new ArrayList<RB>();
    private List<WR> wrList = new ArrayList<WR>();

    private Set<String> teamMemberNames = new HashSet<String>();

    private final double budget = 50000;
    private final int MAX_LIST_SIZE = 2;

    public FlexUpdateTeam(){}



    // first check if the player is better than any existing ones in the team,
    // and produce more points, but within budget

    public boolean updateCostEffectiveMemberIfRequired(Player member) {
        boolean isUpdateRequired = false;
        if (member == null) return isUpdateRequired;
        // dont update existing member
        if (teamMemberNames.contains(member)) return isUpdateRequired;
        if (member instanceof  FLEX){
            if (member.getPosition() == Position.QB) {
                if (qb == null) {
                    return setQb((QB) member);
                }
                if (flex == null) {
                    return setFlex((FLEX) member);
                }
                if (isUpdateRequired(qb, member)) {
                    if (isUpdateRequired(flex, member)) {
                        if (qb.isBetterThan(flex)) {
                            return setFlex((FLEX) member);
                        }
                    }
                    return setQb((QB) member);
                }
            }
            if (member.getPosition() == Position.TE) {
                if (te == null) {
                    return setTe((TE) member);
                }
                if (flex == null) {
                    return setFlex((FLEX) member);
                }
                if (isUpdateRequired(te, member)) {
                    if (isUpdateRequired(flex, member)) {
                        if (te.isBetterThan(flex)) {
                            return setFlex((FLEX) member);
                        }
                    }
                    return setTe((TE) member);
                }
            }

            if (member.getPosition() == Position.RB) {
                isUpdateRequired = updateCostEffectiveMemberIfRequired(rbList, (RB) member);
                if (isUpdateRequired) return isUpdateRequired;
                if (isUpdateRequired(flex, member)) {
                    return setFlex((FLEX) member);
                }

            }
            if (member.getPosition() == Position.WR) {
                isUpdateRequired =  updateCostEffectiveMemberIfRequired(wrList, (WR) member);
                if (isUpdateRequired) return isUpdateRequired;
                if (isUpdateRequired(flex, member)) {
                    return setFlex((FLEX) member);
                }
            }
        }

        if (member instanceof  DEF && isUpdateRequired(def, member)) {
            return setDef((DEF) member);
        }

        return false;


    /*    if (member.getPosition() == Position.QB) {
            QB newQb = new QB(member.getName(), member.getPrice(), member.getProjectPoints(), member.getDollarPerPoints());
            FLEX newFlex = new FLEX(member.getName(), Position.QB, member.getPrice(), member.getProjectPoints(), member.getDollarPerPoints());
            if (qb == null) {
                setQb(newQb);
                return;
            }
            if (flex == null) {
                setFlex(newFlex);
                return;
            }
            if (isUpdateRequired(qb, member)) {
                if (isUpdateRequired(flex, member)) {
                    if (qb.isBetterThan(flex)) {
                        setFlex(flex);
                        return;
                    }
                }
                setQb(qb);
                return;
            }*/

    }


    public boolean replaceHighPointsMemberIfRequired(Player member) {
        boolean isUpdateRequired = false;
        if (member == null) return isUpdateRequired;
        // dont update existing member
        if (teamMemberNames.contains(member)) return isUpdateRequired;
        if (member instanceof  FLEX){
            if (member.getPosition() == Position.QB) {
                if (qb == null) {
                    return setQb((QB) member);
                }
                if (flex == null) {
                    return setFlex((FLEX) member);
                }
                if (shouldReplace(qb, member)) {
                    if (shouldReplace(flex, member)) {
                        if (qb.hasHigherProPointsThan(flex)) {
                            return setFlex((FLEX) member);
                        }
                    }
                    return setQb((QB) member);
                }
            }
            if (member.getPosition() == Position.TE) {
                if (te == null) {
                    return setTe((TE) member);
                }
                if (flex == null) {
                    return setFlex((FLEX) member);
                }
                if (shouldReplace(te, member)) {
                    if (shouldReplace(flex, member)) {
                        if (te.hasHigherProPointsThan(flex)) {
                            return setFlex((FLEX) member);
                        }
                    }
                    return setTe((TE) member);
                }
            }

            if (member.getPosition() == Position.RB) {
                isUpdateRequired = replaceIfRequired(rbList, (RB) member);
                if (isUpdateRequired) return isUpdateRequired;
                if (shouldReplace(flex, member)) {
                    return setFlex((FLEX) member);
                }

            }
            if (member.getPosition() == Position.WR) {
                isUpdateRequired =  replaceIfRequired(wrList, (WR) member);
                if (isUpdateRequired) return isUpdateRequired;
                if (shouldReplace(flex, member)) {
                    return setFlex((FLEX) member);
                }
            }
        }

        if (member instanceof  DEF && shouldReplace(def, member)) {
            return setDef((DEF) member);
        }

        return false;


    /*    if (member.getPosition() == Position.QB) {
            QB newQb = new QB(member.getName(), member.getPrice(), member.getProjectPoints(), member.getDollarPerPoints());
            FLEX newFlex = new FLEX(member.getName(), Position.QB, member.getPrice(), member.getProjectPoints(), member.getDollarPerPoints());
            if (qb == null) {
                setQb(newQb);
                return;
            }
            if (flex == null) {
                setFlex(newFlex);
                return;
            }
            if (isUpdateRequired(qb, member)) {
                if (isUpdateRequired(flex, member)) {
                    if (qb.isBetterThan(flex)) {
                        setFlex(flex);
                        return;
                    }
                }
                setQb(qb);
                return;
            }*/

    }


    public<T extends Player> boolean updateCostEffectiveMemberIfRequired(List<T> list, T member) {
        boolean hasUpdated = false;
        int size = list.size();

        T bestMember = size >= 1 ? list.get(0) : null;
        T secondMember = size == 2 ? list.get(1) : null;

        // update the best member
        if (bestMember == null) {
            bestMember = member;
            hasUpdated = true;
        } else {
            // if best member is better, then check second member
            if (bestMember.isBetterThan(member)) {
                if (secondMember != null && secondMember.isBetterThan(member)) {
                    // no update
                } else {
                    secondMember = member;
                    hasUpdated = true;
                }
            } else {
                secondMember = bestMember;
                bestMember = member;
                hasUpdated = true;
            }
        }
        list.clear();
        if (bestMember != null) list.add(bestMember);
        if (secondMember != null) list.add(secondMember);
        if (hasUpdated) refreshTeamMemberNames();
        return hasUpdated;
    }

    // This method should only be used once a full list is ready
    public<T extends Player> boolean replaceIfRequired(List<T> list, T member) {
        if (!isTeamReady()) {
            throw new RuntimeException("This method should only be used once a full list is ready");
        }

        list.sort(Player.PointsDescComparator);

        T bestMember = list.get(0);
        T secondMember = list.get(1);

        boolean betterThanSecond = shouldReplace(secondMember, member);
        boolean betterThanFirst = shouldReplace(bestMember, member);

        if (betterThanFirst && betterThanSecond) {
            secondMember = bestMember;
            bestMember = member;
            list.set(0, bestMember);
            list.set(1, secondMember);
            return true;
        } else if (betterThanSecond) {
            secondMember = member;
            list.set(1, secondMember);
            return true;
        } else {
            return false;
        }
    }


    public<T extends Player> boolean isUpdateRequired (T currentMember, T newMember) {
        boolean isUpdatedRequired = false;
        if (newMember == null) return isUpdatedRequired;
        if (currentMember == null || !currentMember.isBetterThan(newMember)) {
            isUpdatedRequired = true;
        }
        return isUpdatedRequired;
    }

    public<T extends Player> boolean shouldReplace(T currentMember, T newMember) {
        boolean isUpdatedRequired = false;
        if (!currentMember.hasHigherProPointsThan(newMember)) {
            isUpdatedRequired = true;
        }
        return isUpdatedRequired && isMemberReplacementWithinBudget(currentMember, newMember);
    }

    public<T extends Player> boolean isMemberReplacementWithinBudget(T currentMember, T newMember) {
        double additionalCost = newMember.getPrice() - currentMember.getPrice();
        return (getTeamCost() + additionalCost) <= budget;
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
        total += flex != null ? flex.getPrice() : 0;
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
        points += flex != null ? flex.getProjectPoints() : 0;
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
        if (flex != null) {
            teamMemberNames.add(flex.getName());
        }
    }


    public boolean isTeamReady(){
        return hasAllTeamMembers() && getTeamCost() <= budget;
    }

    public boolean hasAllQb() {
        return qb != null;
    }

    public boolean hasAllRb() {
        return rbList.size() == MAX_LIST_SIZE;
    }

    public boolean hasAllWr(){
        return wrList.size() == MAX_LIST_SIZE;
    }

    public boolean hasAllTe(){
        return te != null;
    }

    public boolean hasAllFlex() {
        return flex != null;
    }

    public boolean hasAllDef() {
        return def != null;
    }

    public boolean hasAllTeamMembers(){
        return hasAllQb() && hasAllRb() && hasAllWr() && hasAllTe() && hasAllFlex() && hasAllDef();
    }

    public QB getQb() {
        return qb;
    }



    public TE getTe() {
        return te;
    }


    public FLEX getFlex() {
        return flex;
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

    public boolean setFlex(FLEX flex) {
        this.flex = flex;
        refreshTeamMemberNames();
        return true;
    }

    public boolean setDef(DEF def) {
        this.def = def;
        refreshTeamMemberNames();
        return true;
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
        if (flex != null) {
            builder.append(flex).append("\n");
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
