package Controllers;

import Core.State;
import Core.StateManager;
import Models.Entities.Entity;
import Models.Entities.Skills.ActiveSkills.ActiveSkill;
import Models.Entities.Skills.ActiveSkills.ActiveSkillList;
import Models.Map.Direction;
import Models.Map.Map;
import Utilities.Action;
import Utilities.Constants;
import Views.PauseMenuView;
import Views.SkillViewPort;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Bradley on 4/7/16.
 */
public class GameViewController extends ViewController {

    private Entity avatar;
    private Map map;


    public GameViewController(StateManager stateManager, Entity avatar, Map map) {
        super(stateManager);
        this.avatar = avatar;
        this.map = map;
        initActiveSkillBindings();
    }

    @Override
    protected void initKeyBindings() {

        // Movement Key Bindings.
        keyBindings.addBinding(KeyEvent.VK_W, new Action() {
            @Override
            public void execute() {
                avatar.move(Direction.NORTH);
            }
        });
        keyBindings.addBinding(KeyEvent.VK_Q, new Action() {
            @Override
            public void execute() {
                avatar.move(Direction.NORTH_WEST);
            }
        });
        keyBindings.addBinding(KeyEvent.VK_E, new Action() {
            @Override
            public void execute() {
                avatar.move(Direction.NORTH_EAST);
            }
        });
        keyBindings.addBinding(KeyEvent.VK_X, new Action() {
            @Override
            public void execute() {
                avatar.move(Direction.SOUTH);
            }
        });
        keyBindings.addBinding(KeyEvent.VK_Z, new Action() {
            @Override
            public void execute() {
                avatar.move(Direction.SOUTH_WEST);
            }
        });
        keyBindings.addBinding(KeyEvent.VK_C, new Action() {
            @Override
            public void execute() {
                avatar.move(Direction.SOUTH_EAST);
            }
        });

        keyBindings.addBinding(KeyEvent.VK_S, new Action() {
            @Override
            public void execute() {
                Models.Menu.Menu skillViewPortMenu = Models.Menu.Menu.createSkillViewPortMenu(stateManager, avatar);
                MenuViewController skillViewPortMenuController = new MenuViewController(stateManager, skillViewPortMenu);
                SkillViewPort skillViewPort = new SkillViewPort(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, skillViewPortMenu, avatar.getStats());
                stateManager.setActiveState(new State(skillViewPortMenuController, skillViewPort));
            }
        });
        keyBindings.addBinding(KeyEvent.VK_ESCAPE, new Action() {
            @Override
            public void execute() {
                Models.Menu.Menu pauseMenu = Models.Menu.Menu.createPauseMenu(stateManager);
                MenuViewController skillViewPortMenuController = new MenuViewController(stateManager, pauseMenu);
                PauseMenuView pauseView = new PauseMenuView(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, pauseMenu);
                stateManager.setActiveState(new State(skillViewPortMenuController, pauseView));
            }
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
                    System.out.println("EXECUTING SKILL YO");
                    skill.activate(avatar);
                }
            });
            key++;
        }
    }
}
