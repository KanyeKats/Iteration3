package Models.Entities.Skills.Effects;

import Models.Entities.Skills.Consequences.Consequence;
import Models.Map.Direction;

import java.awt.*;

/**
 * Created by josh on 4/6/16.
 */
public abstract class Effect {
    private int range;
    private Point location;
    private Consequence consequence;
    private Direction direction;


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
