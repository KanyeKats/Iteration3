package Controllers;

import Core.StateManager;
import Models.Entities.Entity;
import Models.Map.Map;

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

    }

    @Override
    protected void initKeyBindings() {

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
}
