package ca.esplendor.optimization;

/**
 * Created by chenzheng on 15-09-10.
 */
public class WR extends FLEX {

    public WR(String name, double price, double points, double rate) {
        super(name, Position.WR, price, points, rate);
    }
    public WR(String name,  double price, double rate) {
        super(name, Position.WR, price, price/rate, rate);
    }
    public String toString() {
        return super.getPosition().name() + " => " + super.toString();
    }
    public static WR clone(WR player) {
        return new WR(player.getName(), player.getPrice(), player.getProjectPoints(), player.getDollarPerPoints());
    }
}
