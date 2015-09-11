package ca.esplendor.optimization;

import java.util.Comparator;

/**
 * Created by chenzheng on 15-09-10.
 */
public class QB implements Comparable<QB>{
    private String name;
    private String team;
    private Position position;
    private double price;
    private double rate;
    public QB(){};
    public QB(String name, Position position, double price, double rate) {
        this.name = name;
        this.position = position;
        this.price = price;
        this.rate = rate;
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

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String toString(){
        return "Name:: " + this.getName() + " Rate:: " + this.getRate() + " Price:: " + this.getPrice();
    }

    public int compareTo(QB p) {
        double compareRate = ((QB) p).getRate();
        double diff = this.rate - compareRate;
        return diff > 0 ? 1 : (diff == 0 ? 0 : -1);
    }


    public static Comparator<QB> RateComparator
            = new Comparator<QB>() {

        public int compare(QB p1, QB p2) {
            return new Double(p1.getRate()).compareTo(p2.getRate());
        }
    };

    public static Comparator<QB> PriceComparator
            = new Comparator<QB>() {

        public int compare(QB p1, QB p2) {
            return new Double(p1.getPrice()).compareTo(p2.getPrice());
        }
    };
}
