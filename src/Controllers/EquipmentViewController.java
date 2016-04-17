package Controllers;

/**
 * Created by sergiopuleri on 4/15/16.
 */

import Core.State;
import Core.StateManager;
import Models.Entities.Entity;
import Models.Items.Takable.Equippable.EquippableItem;
import Utilities.Action;
import Utilities.Constants;
import Views.EquipmentView;
import Views.InventoryView;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;



/**
 * Created by sergiopuleri on 4/14/16.
 */
public class EquipmentViewController extends ViewController {

    // References to the inventory view to get selected, set selected, etc.
    private EquipmentView equipmentView;

    // Needs ref to entity in order to unequip items
    private Entity entity;

    public EquipmentViewController(StateManager stateManager, EquipmentView equipmentView, Entity entity) {
        super(stateManager);
        this.equipmentView = equipmentView;
        this.entity = entity;
    }

    @Override
    protected void initKeyBindings() {

        //// NAVIGATION ////
        keyBindings.addBinding(KeyEvent.VK_UP, new Action() {
            @Override
            public void execute() {
                equipmentView.previous();
                equipmentView.refresh();
            }
        });
        keyBindings.addBinding(KeyEvent.VK_DOWN, new Action() {
            @Override
            public void execute() {
                equipmentView.next();
                equipmentView.refresh();}
        });
        keyBindings.addBinding(KeyEvent.VK_LEFT, new Action() {
            @Override
            public void execute() {
                equipmentView.previous();
                equipmentView.refresh();
            }
        });
        keyBindings.addBinding(KeyEvent.VK_RIGHT, new Action() {
            @Override
            public void execute() {
                equipmentView.next();
                equipmentView.refresh();
            }
        });

        //// ACTIONS ////
        keyBindings.addBinding(KeyEvent.VK_ENTER, new Action() {
            @Override
            public void execute() {
                EquippableItem item = equipmentView.getSelectedItem();
                if (item != null) {
                    entity.unequip(item);
                    equipmentView.refresh();
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
        keyBindings.addBinding(KeyEvent.VK_P, new Action() {
            @Override
            public void execute() {
                stateManager.goToPreviousState();
            }

        });
        keyBindings.addBinding(KeyEvent.VK_I, new Action() {
            @Override
            public void execute() {
                InventoryView inventoryView = new InventoryView(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, entity.getInventory());
                InventoryViewController inventoryViewController = new InventoryViewController(stateManager, inventoryView, entity);
                stateManager.setActiveState(new State(inventoryViewController, inventoryView));
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
        // I REPEAT.. NOT NECESSARY FOR THIS VIEW.
    }
}
