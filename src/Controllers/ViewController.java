package Controllers;

import Core.StateManager;
import Utilities.KeyBindings;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Bradley on 4/4/2016.
 */
public abstract class ViewController {

    protected StateManager stateManager;
    protected KeyBindings keyBindings;

    public ViewController(StateManager stateManager){
        this.stateManager = stateManager;
        this.keyBindings = new KeyBindings();
        initKeyBindings();
    }

    protected abstract void initKeyBindings();

    public void keyPressed(KeyEvent e){
        keyBindings.keyPressed(e.getKeyCode());
    }

    public void keyReleased(KeyEvent e){
        keyBindings.keyReleased(e.getKeyCode());
    }

    public abstract void mouseClicked(MouseEvent e);
    public abstract void mouseDragged(MouseEvent e);
    public abstract void update();

    public void remapKey(int oldKey, int newKey){
        KeyBindings newKeys = new KeyBindings();
        for(int i : keyBindings.getKeys()) {
            if(i == oldKey) {
                newKeys.addBinding(newKey, keyBindings.getKeyAction(oldKey));
                keyBindings.removeBinding(oldKey);
            }
            else{
                newKeys.addBinding(i, keyBindings.getKeyAction(i));
            }
        }
        keyBindings = newKeys;
    }

    public KeyBindings getKeyBindings(){
        return keyBindings;
    }

}
