
package _2048.game;

public class Piece {

    private int value;
    private int exp;

    public Piece() {
        this(2);
    }
    
    public Piece(int value) {
        this.value = value;
        this.exp = 1;
    }
    
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

    public void setValue(int value) {
        this.value = value;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
    
    
}
