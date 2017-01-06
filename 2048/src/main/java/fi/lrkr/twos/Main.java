package fi.lrkr.twos;

import fi.lrkr.twos.game.Logic;
import fi.lrkr.twos.gui.Gui;
import javax.swing.SwingUtilities;

/**
 * Main class.
 */
public class Main {

    /**
     * Starts a new game when the program is launched.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Logic logic = new Logic();
        Gui gui = new Gui(logic);
        logic.setGui(gui);
        logic.start();
        SwingUtilities.invokeLater(gui);    
    }
}
