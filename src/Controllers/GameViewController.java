package Controllers;

import Core.StateManager;
import Models.Consequences.ImmediateStatConsequence;
import Models.Entities.Entity;
import Models.Entities.Skills.InfluenceEffect.AngularEffect;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Models.Map.Direction;
import Models.Map.Map;
import Utilities.Action;
import javafx.geometry.Point3D;

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
        keyBindings.addBinding(KeyEvent.VK_1, new Action() {
            @Override
            public void execute() {
                new AngularEffect(5, new Point3D(0,0,0), new ImmediateStatConsequence(new StatModificationList(
                        new StatModification(Stat.STRENGTH,10),
                        new StatModification(Stat.AGILITY,6),
                        new StatModification(Stat.INTELLECT,0),
                        new StatModification(Stat.HARDINESS,10),
                        new StatModification(Stat.MOVEMENT,15),
                        new StatModification(Stat.MAX_HEALTH,50),
                        new StatModification(Stat.MAX_MANA,0),
                        new StatModification(Stat.EXPERIENCE, 0))), map, Direction.NORTH);
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
}
