package Controllers;

import Core.StateManager;
import Utilities.Action;
import Views.NPCShopView;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Josh on 4/15/2016.
 */
public class NPCShopViewController extends ViewController {

    // References to the shop view to get selected, set selected, etc.
    private NPCShopView npcShopView;

    public NPCShopViewController(StateManager stateManager, NPCShopView npcShopView) {
        super(stateManager);
        this.npcShopView = npcShopView;
        
    }

    @Override
    protected void initKeyBindings() {

        //// NAVIGATION ////
        keyBindings.addBinding(KeyEvent.VK_UP, new Action() {
            @Override
            public void execute() {
                npcShopView.switchToNPCInventory();
            }
        });
        keyBindings.addBinding(KeyEvent.VK_DOWN, new Action() {
            @Override
            public void execute() {
                npcShopView.switchToAvatarInventory();
            }
        });
        keyBindings.addBinding(KeyEvent.VK_LEFT, new Action() {
            @Override
            public void execute() {
                npcShopView.previousItem();
            }
        });
        keyBindings.addBinding(KeyEvent.VK_RIGHT, new Action() {
            @Override
            public void execute() {
                npcShopView.nextItem();
            }
        });

        //// ACTIONS ////
        keyBindings.addBinding(KeyEvent.VK_ENTER, new Action() {
            @Override
            public void execute() {
                npcShopView.selectItem();
            }
        });
        keyBindings.addBinding(KeyEvent.VK_SPACE, new Action() {
            @Override
            public void execute() {
                npcShopView.makeTrade();
            }
        });

        //// EXIT ////
        keyBindings.addBinding(KeyEvent.VK_ESCAPE, new Action() {
            @Override
            public void execute() {
                stateManager.goToPreviousState();
            }

        });
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // NOT NECESSARY FOR THIS VIEW
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // NOT NECESSARY FOR THIS VIEW
    }

    @Override
    public void update() {

    }
}
