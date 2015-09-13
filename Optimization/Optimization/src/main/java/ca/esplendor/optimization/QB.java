package ca.esplendor.optimization;

import java.util.Comparator;

/**
 * Created by chenzheng on 15-09-10.
 */
public class QB extends Player {

    public QB(String name,  double price, double points, double rate) {
        super(name, Position.QB, price, points, rate);
    }

    public QB(String name,  double price, double rate) {
        super(name, Position.QB, price, price/rate, rate);
    }

    public String toString() {
        return super.getPosition().name() + " => "  + super.toString();
    }

    public static QB clone(QB player) {
        return new QB(player.getName(), player.getPrice(), player.getProjectPoints(), player.getDollarPerPoints());
    }

/*
    public String toString(){
        return "Name:: " + this.getName() + " Rate:: " + this.getRate() + " Price:: " + this.getPrice();
    }

    public int compareTo(QB p) {
        double compareRate = ((QB) p).getRate();
        double diff = this.getRate() - compareRate;
        return diff > 0 ? 1 : (diff == 0 ? 0 : -1);
    }


    public static Comparator<QB> DollarPerPointsComparator
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
    };*/
}
