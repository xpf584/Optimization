package ca.esplendor.optimization;

/**
 * Created by chenzheng on 15-09-10.
 */
public class K extends Player {

    public K(String name, double price, double points, double rate) {
        super(name, Position.K, price, points, rate);
    }
    public K(String name,  double price, double rate) {
        super(name, Position.K, price, price/rate, rate);
    }
    public String toString() {
        return super.getPosition().name() + " => " + super.toString();
    }
    public static K clone(K player) {
        return new K(player.getName(), player.getPrice(), player.getProjectPoints(), player.getDollarPerPoints());
    }
}
