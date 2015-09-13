package ca.esplendor.optimization;

/**
 * Created by chenzheng on 15-09-10.
 */
public class TE extends FLEX {

    public TE(String name, double price, double points, double rate) {
        super(name, Position.TE, price, points, rate);
    }
    public TE(String name,  double price, double rate) {
        super(name, Position.TE, price, price/rate, rate);
    }
    public String toString() {
        return super.getPosition().name() + " => " + super.toString();
    }
    public static TE clone(TE player) {
        return new TE(player.getName(), player.getPrice(), player.getProjectPoints(), player.getDollarPerPoints());
    }
}
