package Controllers;

import Core.State;
import Core.StateManager;
import Models.Entities.Entity;
import Models.Entities.Stats.Stat;
import Models.Map.Direction;
import Models.Map.Map;
import Utilities.Action;
import Utilities.Constants;
import Views.*;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Set;

/**
 * Created by Bradley on 4/7/16.
 */
public class GameViewController extends ViewController {

    private Entity avatar;
    private Map map;
    private GameViewController controller;
    private int refreshCounter; // This is done purely for performance enhancments.


    public GameViewController(StateManager stateManager, Entity avatar, Map map) {
        super(stateManager);
        this.avatar = avatar;
        this.map = map;
        controller = this;
        refreshCounter = 0;
    }

    @Override
    protected void initKeyBindings() {

        // Movement Key Bindings.
        keyBindings.addBinding(KeyEvent.VK_W, new Action() {
            @Override
            public void execute() {
                avatar.move(Direction.NORTH);
            }

            @Override
            public String toString(){ return "Move North";}
        });
        keyBindings.addBinding(KeyEvent.VK_Q, new Action() {
            @Override
            public void execute() {
                avatar.move(Direction.NORTH_WEST);
            }

            @Override
            public String toString(){ return "Move Northwest";}
        });
        keyBindings.addBinding(KeyEvent.VK_E, new Action() {
            @Override
            public void execute() {
                avatar.move(Direction.NORTH_EAST);
            }

            @Override
            public String toString(){ return "Move Northeast";}
        });
        keyBindings.addBinding(KeyEvent.VK_X, new Action() {
            @Override
            public void execute() {
                avatar.move(Direction.SOUTH);
            }

            @Override
            public String toString(){ return "Move South";}
        });
        keyBindings.addBinding(KeyEvent.VK_Z, new Action() {
            @Override
            public void execute() {
                avatar.move(Direction.SOUTH_WEST);
            }

            @Override
            public String toString(){ return "Move Southwest";}
        });
        keyBindings.addBinding(KeyEvent.VK_C, new Action() {
            @Override
            public void execute() {
                avatar.move(Direction.SOUTH_EAST);
            }

            @Override
            public String toString(){ return "Move Southeast";}
        });

        keyBindings.addBinding(KeyEvent.VK_S, new Action() {
            @Override
            public void execute() {
                Models.Menu.Menu skillViewPortMenu = Models.Menu.Menu.createSkillViewPortMenu(stateManager, avatar);
                MenuViewController skillViewPortMenuController = new MenuViewController(stateManager, skillViewPortMenu);
                SkillViewPort skillViewPort = new SkillViewPort(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, skillViewPortMenu, avatar.getStats());
                stateManager.setActiveState(new State(skillViewPortMenuController, skillViewPort));
            }

            @Override
            public String toString(){ return "Skills Menu";}
        });
        keyBindings.addBinding(KeyEvent.VK_I, new Action() {
            @Override
            public void execute() {
                InventoryView inventoryView = new InventoryView(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, avatar.getInventory());
                InventoryViewController inventoryViewController = new InventoryViewController(stateManager, inventoryView, avatar);
                stateManager.setActiveState(new State(inventoryViewController, inventoryView));
            }

            @Override
            public String toString(){ return "Inventory View";}
        });


        keyBindings.addBinding(KeyEvent.VK_SPACE, new Action() {
            @Override
            public void execute() {

                if(avatar.getTileInFront().containsEntity()) {
                    Models.Menu.Menu npcMenu = Models.Menu.Menu.createNPCMenu(stateManager, avatar, avatar.getTileInFront().getEntity());
                    MenuViewController npcMenuController = new MenuViewController(stateManager, npcMenu);
                    NPCMenuView npcMenuView = new NPCMenuView(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, npcMenu);
                    stateManager.setActiveState(new State(npcMenuController, npcMenuView));
                }
            }

            @Override
            public String toString(){ return "NPC Menu View";}
        });


        keyBindings.addBinding(KeyEvent.VK_ESCAPE, new Action() {
            @Override
            public void execute() {
                Models.Menu.Menu pauseMenu = Models.Menu.Menu.createPauseMenu(stateManager, controller);
                MenuViewController skillViewPortMenuController = new MenuViewController(stateManager, pauseMenu);
                PauseMenuView pauseView = new PauseMenuView(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, pauseMenu);
                stateManager.setActiveState(new State(skillViewPortMenuController, pauseView));
            }

            @Override
            public String toString(){ return "Pause";}
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void update() {
        if(avatar.getStats().getStat(Stat.LIVES) == 0){
            Models.Menu.Menu gameOverMenu = Models.Menu.Menu.createGameOverMenu(stateManager);
            MenuViewController skillViewPortMenuController = new MenuViewController(stateManager, gameOverMenu);
            PauseMenuView pauseView = new PauseMenuView(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, gameOverMenu);
            stateManager.setActiveState(new State(skillViewPortMenuController, pauseView));          }

        if(refreshCounter % Constants.FRAME_RATE == 0){
            Set<Entity> entitiesOnMap = map.getEntitiesOnMap();
            for(Entity entity : entitiesOnMap){
                entity.update();
            }
        }
        refreshCounter++;
    }
}
