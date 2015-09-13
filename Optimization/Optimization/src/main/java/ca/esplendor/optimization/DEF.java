package ca.esplendor.optimization;

/**
 * Created by chenzheng on 15-09-10.
 */
public class DEF extends Player {

    public DEF(String name, double price, double points, double rate) {
        super(name, Position.DEF, price, points, rate);
    }
    public DEF(String name,  double price, double rate) {
        super(name, Position.DEF, price, price/rate, rate);
    }
    public String toString() {
        return super.getPosition().name() + " => " + super.toString();
    }
    public static DEF clone(DEF player) {
        return new DEF(player.getName(), player.getPrice(), player.getProjectPoints(), player.getDollarPerPoints());
    }
}
