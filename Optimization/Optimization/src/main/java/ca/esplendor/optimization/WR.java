package ca.esplendor.optimization;

/**
 * Created by chenzheng on 15-09-10.
 */
public class WR extends Player {
    private Position position;

    public WR(String name, double price, double rate) {
        super(name, Position.RB, price, rate);
    }
}
