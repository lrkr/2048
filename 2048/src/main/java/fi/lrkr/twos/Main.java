package fi.lrkr.twos;

import fi.lrkr.twos.game.Logic;
import fi.lrkr.twos.gui.Gui;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        //jotain vikaa x / y seteissä isommissa boardeissa
        Logic logic = new Logic();
        Gui gui = new Gui(logic);
        logic.setGui(gui);
        logic.startGui();
        SwingUtilities.invokeLater(gui);
        
        //Logic logic = new Logic();
        //logic.start();
    }
}
