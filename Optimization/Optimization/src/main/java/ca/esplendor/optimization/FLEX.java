package ca.esplendor.optimization;

/**
 * Created by chenzheng on 15-09-10.
 */
public class FLEX extends Player {


    public FLEX(String name, Position position, double price, double points, double rate) {
        super(name, position, price, points, rate);
    }


    public String toString() {
        return super.getPosition().name() + " => " + super.toString();
    }

    public static FLEX clone(FLEX player) {
        return new FLEX(player.getName(), player.getPosition(), player.getPrice(), player.getProjectPoints(), player.getDollarPerPoints());
    }
}
