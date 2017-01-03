package fi.lrkr.twos.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

    private final Gui gui;

    public KeyboardListener(Gui gui) {
        this.gui = gui;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_RIGHT:
                gui.ExecuteCommand('R');
                break;
            case KeyEvent.VK_LEFT:
                gui.ExecuteCommand('L');
                break;
            case KeyEvent.VK_UP:
                gui.ExecuteCommand('U');
                break;
            case KeyEvent.VK_DOWN:
                gui.ExecuteCommand('D');
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}