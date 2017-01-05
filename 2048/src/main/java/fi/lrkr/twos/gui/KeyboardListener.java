package fi.lrkr.twos.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

    private final Gui gui;
    private long time;
    private long threshhold;

    public KeyboardListener(Gui gui) {
        this.gui = gui;
        this.time = System.currentTimeMillis();
        this.threshhold = 100;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (System.currentTimeMillis() - time < threshhold) {
            return;
        }
        time = System.currentTimeMillis();
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_RIGHT:
                gui.executeCommand('R');
                break;
            case KeyEvent.VK_LEFT:
                gui.executeCommand('L');
                break;
            case KeyEvent.VK_UP:
                gui.executeCommand('U');
                break;
            case KeyEvent.VK_DOWN:
                gui.executeCommand('D');
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
