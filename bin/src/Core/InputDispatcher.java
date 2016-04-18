package Core;

import Controllers.ViewController;

import java.awt.*;
import java.awt.event.*;

/**
 * Created by Bradley on 4/4/2016.
 */
public class InputDispatcher implements KeyListener, MouseListener, MouseMotionListener {

    private ViewController activeController;

    public void setActiveController(ViewController activeController){
        this.activeController = activeController;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        activeController.mouseClicked(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        activeController.mouseDragged(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        activeController.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        activeController.keyReleased(e);
    }

    // Methods that needed to be overridden but are not useful to us.
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
