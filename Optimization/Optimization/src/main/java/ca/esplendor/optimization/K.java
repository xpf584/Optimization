package ca.esplendor.optimization;

/**
 * Created by chenzheng on 15-09-10.
 */
public class K extends Player {
    private Position position;

    public K(String name, double price, double rate) {
        super(name, Position.TE, price, rate);
    }
}
