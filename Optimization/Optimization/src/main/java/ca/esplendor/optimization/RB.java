package ca.esplendor.optimization;

/**
 * Created by chenzheng on 15-09-10.
 */
public class RB extends Player {
    private Position position;

    public RB(String name, double price, double rate) {
        super(name, Position.QB, price, rate);
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
    };*/
}
