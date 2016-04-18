package Controllers;

import Core.State;
import Core.StateManager;
import Models.Entities.Entity;
import Models.Entities.Skills.ActiveSkills.ActiveSkill;
import Models.Entities.Skills.ActiveSkills.ActiveSkillList;
import Models.Entities.Stats.Stat;
import Models.Map.Direction;
import Models.Map.Map;
import Utilities.Action;
import Utilities.Constants;
import Views.*;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Set;

/**
 * Created by Bradley on 4/7/16.
 */
public class GameViewController extends ViewController {

    private Entity avatar;
    private Map map;
    private GameViewController controller;
    private AreaViewport areaViewport;
    private int refreshCounter; // This is done purely for performance enhancments.


    public GameViewController(StateManager stateManager, Entity avatar, Map map, AreaViewport areaViewport) {
        super(stateManager);
        this.avatar = avatar;
        this.map = map;
        initActiveSkillBindings();
        controller = this;
        this.areaViewport = areaViewport;
        refreshCounter = 0;
    }

    @Override
    protected void initKeyBindings() {

        // Movement Key Bindings.
        keyBindings.addBinding(KeyEvent.VK_W, new Action() {
            @Override
            public void execute() {
                if(areaViewport.isMoving()){
                    areaViewport.move(Direction.NORTH);
                }
                else {
                    avatar.move(Direction.NORTH);
                }
            }

            @Override
            public String toString(){ return "Move North";}
        });
        keyBindings.addBinding(KeyEvent.VK_Q, new Action() {
            @Override
            public void execute() {
                if(areaViewport.isMoving()){
                    areaViewport.move(Direction.NORTH_WEST);
                }
                else {
                    avatar.move(Direction.NORTH_WEST);
                }
            }

            @Override
            public String toString(){ return "Move Northwest";}
        });
        keyBindings.addBinding(KeyEvent.VK_E, new Action() {
            @Override
            public void execute() {
                if(areaViewport.isMoving()){
                    areaViewport.move(Direction.NORTH_EAST);
                }
                else {
                    avatar.move(Direction.NORTH_EAST);
                }
            }
            @Override
            public String toString(){ return "Move Northeast";}
        });
        keyBindings.addBinding(KeyEvent.VK_X, new Action() {
            @Override
            public void execute() {
                if(areaViewport.isMoving()){
                    areaViewport.move(Direction.SOUTH);
                }
                else {
                    avatar.move(Direction.SOUTH);
                }
            }

            @Override
            public String toString(){ return "Move South";}
        });
        keyBindings.addBinding(KeyEvent.VK_Z, new Action() {
            @Override
            public void execute() {
                if(areaViewport.isMoving()){
                    areaViewport.move(Direction.SOUTH_WEST);
                }
                else {
                    avatar.move(Direction.SOUTH_WEST);
                }
            }

            @Override
            public String toString(){ return "Move Southwest";}
        });
        keyBindings.addBinding(KeyEvent.VK_C, new Action() {
            @Override
            public void execute() {
                if(areaViewport.isMoving()){
                    areaViewport.move(Direction.SOUTH_EAST);
                }
                else {
                    avatar.move(Direction.SOUTH_EAST);
                }
            }

            @Override
            public String toString(){ return "Move Southeast";}
        });

        keyBindings.addBinding(KeyEvent.VK_UP, new Action() {
            @Override
            public void execute() {

                avatar.move(Direction.UP);

            }

            @Override
            public String toString(){ return "Move Up";}
        });

        keyBindings.addBinding(KeyEvent.VK_DOWN, new Action() {
            @Override
            public void execute() {

                avatar.move(Direction.DOWN);

            }

            @Override
            public String toString(){ return "Move Down";}
        });

        keyBindings.addBinding(KeyEvent.VK_T, new Action() {
            @Override
            public void execute() {
                if(areaViewport.isMoving()){

                }
                else {
                    avatar.interact();
                }
            }

            @Override
            public String toString(){ return "Interact";}
        });

        keyBindings.addBinding(KeyEvent.VK_U, new Action() {
            @Override
            public void execute() {
                if(avatar.isMounted()){
                    avatar.unMountVehicle();
                }
            }
            @Override
            public String toString(){ return "Unmount";}
        });

        keyBindings.addBinding(KeyEvent.VK_Y, new Action() {
            @Override
            public void execute() {
                if (areaViewport.isMoving()) {
                    areaViewport.setMoving(false);
                }
                else {
                    areaViewport.setMoving(true);
                }
            }
            @Override
            public String toString(){ return "Move Camera";}
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

        keyBindings.addBinding(KeyEvent.VK_P, new Action() {
            @Override
            public void execute() {
                EquipmentView equipmentView = new EquipmentView(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, avatar.getEquipment(), avatar.getStats(), avatar.getOccupation().toString());
                EquipmentViewController equipmentViewController = new EquipmentViewController(stateManager, equipmentView, avatar);
                stateManager.setActiveState(new State(equipmentViewController, equipmentView));
            }

            @Override
            public String toString(){ return "Equipment View";}
        });

        // DEBUG MODE
        keyBindings.addBinding(KeyEvent.VK_SLASH, new Action() {
            @Override
            public void execute() {
                areaViewport.toggleDebugMode();
            }

            @Override
            public String toString(){ return "Debug Mode";}
        });


        keyBindings.addBinding(KeyEvent.VK_SPACE, new Action() {
            @Override
            public void execute() {

                if(avatar.getTileInFront().containsEntity()) {
                    Models.Menu.Menu npcMenu = Models.Menu.Menu.createNPCMenu(stateManager, avatar, avatar.getTileInFront().getEntity());
                    MenuViewController npcMenuController = new MenuViewController(stateManager, npcMenu);
                    BufferedImage oldViewContent = stateManager.getCurrentViewContent();
                    NPCMenuView npcMenuView = new NPCMenuView(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, npcMenu, oldViewContent);
                    stateManager.setActiveState(new State(npcMenuController, npcMenuView));
                }
            }

            @Override
            public String toString(){ return "NPC Menu View";}
        });


        keyBindings.addBinding(KeyEvent.VK_ESCAPE, new Action() {
            @Override
            public void execute() {
                Models.Menu.Menu pauseMenu = Models.Menu.Menu.createPauseMenu(stateManager, controller, avatar);
                MenuViewController skillViewPortMenuController = new MenuViewController(stateManager, pauseMenu);
                BufferedImage oldViewContent = stateManager.getCurrentViewContent();
                PauseMenuView pauseView = new PauseMenuView(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, pauseMenu, oldViewContent);
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
            GameOverView gameOverView = new GameOverView(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, gameOverMenu);
            stateManager.setActiveState(new State(skillViewPortMenuController, gameOverView));          }

        if(refreshCounter % Constants.FRAME_RATE/4 == 0){
            Set<Entity> entitiesOnMap = map.getEntitiesOnMap();
            for(Entity entity : entitiesOnMap){
                entity.update();
            }
            refreshCounter = 0;
        }
        refreshCounter++;
    }

    private void initActiveSkillBindings(){
        ActiveSkillList activeSkills = avatar.getActiveSkillList();
        System.out.println(activeSkills.size());
        int key = KeyEvent.VK_1;
        for(int i=0; i<activeSkills.size(); i++){
            ActiveSkill skill = activeSkills.get(i);
            keyBindings.addBinding(key, new Action() {
                @Override
                public void execute() {
                    skill.activate(avatar);
                }

                @Override
                public String toString(){ return skill.toString();}
            });
            if(key == KeyEvent.VK_9)
                key = KeyEvent.VK_0;
            else if(key == KeyEvent.VK_0)
                key = KeyEvent.VK_J;
            else
                key++;
        }
    }
}
