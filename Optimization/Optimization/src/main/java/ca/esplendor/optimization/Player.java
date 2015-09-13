package ca.esplendor.optimization;

import java.util.Comparator;

/**
 * Created by chenzheng on 15-09-10.
 */
public class Player implements Comparable<Player>{
    private String name;
    private String team;
    private Position position;
    private double price;
    private double dollarPerPoints;
    private double projectPoints;
    public Player(){};
    public Player(String name, Position position, double price, double projectPoints, double dollarPerPoints) {
        this.name = name;
        this.position = position;
        this.price = price;
        this.dollarPerPoints = dollarPerPoints;
        this.projectPoints = projectPoints;
    }

    public static Player clone(Player player) {
        return new Player(player.getName(), player.getPosition(), player.getPrice(), player.getProjectPoints(), player.getDollarPerPoints());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDollarPerPoints() {
        return dollarPerPoints;
    }

    public void setDollarPerPoints(double dollarPerPoints) {
        this.dollarPerPoints = dollarPerPoints;
    }

    public double getProjectPoints() {
        return projectPoints;
    }

    public void setProjectPoints(double projectPoints) {
        this.projectPoints = projectPoints;
    }

    public String toString(){
        return "Name:: " + this.getName()
                + " Price:: " + this.getPrice() + " Pro Points:: " + this.getProjectPoints()
                + " $/Point:: " + this.getDollarPerPoints();
    }

    public int compareTo(Player p) {
        double compareRate = ((Player) p).getDollarPerPoints();
        double diff = this.dollarPerPoints - compareRate;
        return diff > 0 ? 1 : (diff == 0 ? 0 : -1);
    }


    public static Comparator<Player> PointsDescComparator
            = new Comparator<Player>() {

        public int compare(Player p1, Player p2) {
            return new Double(p2.getProjectPoints()).compareTo(p1.getProjectPoints());
        }
    };

    public static Comparator<Player> DollarPerPointsComparator
            = new Comparator<Player>() {

        public int compare(Player p1, Player p2) {
            return new Double(p1.getDollarPerPoints()).compareTo(p2.getDollarPerPoints());
        }
    };


    public static Comparator<Player> PriceComparator
            = new Comparator<Player>() {

        public int compare(Player p1, Player p2) {
            return new Double(p1.getPrice()).compareTo(p2.getPrice());
        }
    };

    public boolean equals(Player player){
        if (player != null && this.getName().equalsIgnoreCase(player.getName())) {
             return true;
        } else {
            return false;
        }
    }

    public boolean isBetterThan(Player comparingPlayer) {
        if (comparingPlayer == null) { throw new RuntimeException("Can not compare null");}
        if (this.getPosition() != comparingPlayer.getPosition()
                && !(this instanceof FLEX) && !(comparingPlayer instanceof FLEX)) {
            throw new RuntimeException("Can not compare player " + this.getPosition() + " with " + comparingPlayer.getPosition()
            + "Current Player " + this + " comparingPlayer " + comparingPlayer);
        }
        if (this.dollarPerPoints < comparingPlayer.getDollarPerPoints()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean hasHigherProPointsThan(Player comparingPlayer) {
        if (comparingPlayer == null) { throw new RuntimeException("Can not compare null");}
        if (this.getPosition() != comparingPlayer.getPosition()
                && !(this instanceof FLEX) && !(comparingPlayer instanceof FLEX)) {
            throw new RuntimeException("Can not compare player " + this.getPosition() + " with " + comparingPlayer.getPosition()
                    + "Current Player " + this + " comparingPlayer " + comparingPlayer);
        }
        if (this.projectPoints > comparingPlayer.getProjectPoints()) {
            return true;
        } else {
            return false;
        }
    }
}
