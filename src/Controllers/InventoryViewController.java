package Controllers;

import Core.StateManager;
import Models.Entities.Entity;
import Models.Items.Takable.TakableItem;
import Models.Menu.Menu;
import Utilities.Action;
import Views.InventoryView;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by sergiopuleri on 4/14/16.
 */
public class InventoryViewController extends ViewController {

    // References to the inventory view to get selected, set selected, etc.
    private InventoryView inventoryView;
    // Needs ref to entity in order to equip / drop items
    private Entity entity;

    public InventoryViewController(StateManager stateManager, InventoryView inventoryView, Entity entity) {
        super(stateManager);
        this.inventoryView = inventoryView;
        this.entity = entity;
    }

    @Override
    protected void initKeyBindings() {

        //// NAVIGATION ////
        keyBindings.addBinding(KeyEvent.VK_UP, new Action() {
            @Override
            public void execute() {
                inventoryView.previousItem();
                inventoryView.refresh();
            }
        });
        keyBindings.addBinding(KeyEvent.VK_DOWN, new Action() {
            @Override
            public void execute() {
                inventoryView.nextItem();
                inventoryView.refresh();}
        });
        keyBindings.addBinding(KeyEvent.VK_LEFT, new Action() {
            @Override
            public void execute() {
                inventoryView.previousItem();
                inventoryView.refresh();
            }
        });
        keyBindings.addBinding(KeyEvent.VK_RIGHT, new Action() {
            @Override
            public void execute() {
                inventoryView.nextItem();
                inventoryView.refresh();
            }
        });

        //// ACTIONS ////
        keyBindings.addBinding(KeyEvent.VK_ENTER, new Action() {
            @Override
            public void execute() {
                TakableItem item = inventoryView.getSelectedItem();
                if (item != null) {
                    item.onUse(entity);
                    inventoryView.refresh();
                }
            }
        });
        keyBindings.addBinding(KeyEvent.VK_D, new Action() {
            @Override
            public void execute() {
                TakableItem item = inventoryView.getSelectedItem();
                if (item != null) {
                    entity.dropItem(inventoryView.getSelectedIndex());
                    inventoryView.refresh();
                }
            }
        });

        //// EXIT ////
        keyBindings.addBinding(KeyEvent.VK_ESCAPE, new Action() {
            @Override
            public void execute() {
                stateManager.goToPreviousState();
            }

        });
        keyBindings.addBinding(KeyEvent.VK_I, new Action() {
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
