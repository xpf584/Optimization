package ca.esplendor.optimization;

/**
 * Created by chenzheng on 15-09-10.
 */
public class TE extends Player {
    private Position position;

    public TE(String name, double price, double rate) {
        super(name, Position.WR, price, rate);
    }
}
