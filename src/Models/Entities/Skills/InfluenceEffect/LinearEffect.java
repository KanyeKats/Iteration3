package Models.Entities.Skills.InfluenceEffect;

import Models.Consequences.Consequence;
import Models.Map.Direction;
import Models.Map.Map;
import Models.Map.Tile;
import Utilities.MapUtilities.MapNavigationUtilities;
import javafx.geometry.Point3D;
import java.awt.Image;
import java.util.ArrayList;

/**
 * Created by johnkaufmann on 4/2/16.
 *
 */
public class LinearEffect extends Effect {
    private Direction direction;
    public LinearEffect(int range, Point3D location, Consequence consequence, Direction direction, Map map) {
        super(range, location, consequence, map);
        this.direction = direction;
        start();
    }

    @Override
    protected ArrayList<ArrayList<Tile>> getAffectedTiles() {
        return MapNavigationUtilities.getLinearTilesInPlane(getLocation(),getRange(),getMap(), getDirection());
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public Image getImage() {
        return null;
    }
}
