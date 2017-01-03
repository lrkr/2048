package fi.lrkr.twos.game;

/**
 * Class provides a representation of location in coordinate space with x, y coordinates. Used for returning locations from methods.
 */
public class Location {

    private final int x;
    private final int y;

    public Location(int y, int x) {
        this.y = y;
        this.x = x;
    }
    
    /**
     * 
     * @return The X coordinate.
     */
    public int getX() {
        return x;
    }
    
    /**
     * 
     * @return The Y coordinate.
     */
    public int getY() {
        return y;
    }
}
