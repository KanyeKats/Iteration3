package Controllers;

import Core.StateManager;
import Models.Menu.Menu;
import Utilities.Action;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by Bradley on 4/4/2016.
 */
public class MenuViewController extends ViewController{

    private Menu menu;

    public MenuViewController(StateManager stateManager, Menu menu) {
        super(stateManager);
        this.menu = menu;
    }

    @Override
    protected void initKeyBindings() {
        keyBindings.addBinding(KeyEvent.VK_UP, new Action() {
            @Override
            public void execute() {
                menu.previousOption();
            }
        });
        keyBindings.addBinding(KeyEvent.VK_DOWN, new Action() {
            @Override
            public void execute() {
                menu.nextOption();
            }
        });
        keyBindings.addBinding(KeyEvent.VK_ENTER, new Action() {
            @Override
            public void execute() {
                ArrayList<Action> actions = menu.getSelectedActions();
                if(!actions.isEmpty()){
                    actions.get(0).execute(); // LOD Violations :(
                    menu.refresh();
                }
            }
        });
        keyBindings.addBinding(KeyEvent.VK_ESCAPE, new Action() {
            @Override
            public void execute() {
                stateManager.goToPreviousState();
            }

        });
    }

    // These are not needed in this particular view.
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void update() {}
}
