package Models.Entities.Skills.Effects;

import Models.Consequences.Consequence;
import Models.Map.Direction;

import java.awt.*;

/**
 * Created by josh on 4/6/16.
 */
public abstract class Effect {
    //I think these need to be changed to protected so that they can be used by the subclasses - Aidan
//    private int range;
//    private Point location;
//    private Consequence consequence;
//    private Direction direction;

    protected int range;
    protected Point location;
    protected Consequence consequence;
    protected Direction direction;


    private void start(){

    }

    public void run(){

    }

    protected abstract void traverseThroughTiles();

    protected abstract void apply();

    protected void dealConsequence(){

    }

    public abstract Image getImage();
}
