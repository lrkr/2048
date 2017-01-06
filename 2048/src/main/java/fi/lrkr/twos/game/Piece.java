
package fi.lrkr.twos.game;

/**
 * Class provides basic functionality for Piece objects which the Board 
 * comprises of.
 */

public class Piece {

    private int value;
    private int exp;
    
    /**
     * Constructor for default value Piece.
     */
    public Piece() {
        this(2);
    }
    
    /**
     * Constructor creates a new Piece object with an initial value.
     * 
     * @param value Initial value for the Piece
     */
    public Piece(int value) {
        this.value = value;
        this.exp = 1;
    }
    
    /**
     * Doubles the value of a piece and increments its exponent, unless the value is 0.
     */
    public void doubleValue() {
        if (this.value == 0) {
            return;
        }
        this.value = this.value * 2;
        this.exp++;
    }
    
    public int getValue() {
        return value;
    }
    
    public int getExp() {
        return exp;
    }
}
