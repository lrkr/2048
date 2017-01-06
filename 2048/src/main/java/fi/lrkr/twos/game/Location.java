package fi.lrkr.twos.game;

/**
 * Class provides a representation of location in coordinate space with x, y 
 * coordinates. Used for returning locations from methods.
 */
public class Location {

    private final int x;
    private final int y;

    /**
     * Constructor for Location objects with x and y coordinate.
     * 
     * @param y Location's y coordinate
     * @param x Location's x coordinate
     */
    public Location(int y, int x) {
        this.y = y;
        this.x = x;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
}
