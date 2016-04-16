package Controllers;

import Core.StateManager;
import Models.Entities.Entity;
import Models.Entities.Stats.Stat;
import Models.Items.Takable.TakableItem;
import Utilities.Action;
import Views.InventoryView;

import java.awt.event.KeyEvent;

/**
 * Created by Josh on 4/16/2016.
 */
public class UseItemOnNPCController extends InventoryViewController {

    // References to the inventory view to get selected, set selected, etc.
    private InventoryView inventoryView;
    // Needs ref to entity in order to equip / drop items
    private Entity avatar;
    private Entity npc;

    public UseItemOnNPCController(StateManager stateManager, InventoryView inventoryView, Entity avatar, Entity npc) {
        super(stateManager, inventoryView, avatar);
        this.inventoryView = inventoryView;
        this.avatar = avatar;
        this.npc = npc;
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
                    item.onUse(npc);
                    avatar.getInventory().removeItem(item);
                    System.out.println("You used an item on the NPC!");
                    inventoryView.refresh();
                    //TODO: Toast that says you used an item
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

}
