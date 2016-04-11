package Models.Entities.Skills.InfluenceEffect;

import Models.Consequences.Consequence;
import Models.Map.Direction;
import Models.Map.Map;
import javafx.geometry.Point3D;
import java.awt.Image;

/**
 * Created by johnkaufmann on 4/2/16.
 * TODO:
 */
public class PrismEffect extends Effect{
    private Direction direction;
    public PrismEffect(int range, Point3D location, Consequence consequence, Direction direction, Map map) {
        super(range, location, consequence, map);
        this.direction = direction;
        start();
    }

    @Override
    protected void traverseThroughTiles() {
        //TODO: does the prism effect have two dimensions of range it needs? both how many columns there are as well as the comlumn's height?
    }

    @Override
    public Image getImage() {
        return null;
    }
}

