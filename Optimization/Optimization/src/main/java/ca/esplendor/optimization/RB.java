package ca.esplendor.optimization;

/**
 * Created by chenzheng on 15-09-10.
 */
public class RB extends FLEX {

    public RB(String name, double price, double points, double rate) {
        super(name, Position.RB, price, points, rate);
    }
    public RB(String name,  double price, double rate) {
        super(name, Position.RB, price, price/rate, rate);
    }
    public String toString() {
        return super.getPosition().name() + " => " + super.toString();
    }
    public static RB clone(RB player) {
        return new RB(player.getName(), player.getPrice(), player.getProjectPoints(), player.getDollarPerPoints());
    }
}
