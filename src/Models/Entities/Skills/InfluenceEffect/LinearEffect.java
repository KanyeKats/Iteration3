package Models.Entities.Skills.InfluenceEffect;

import Models.Consequences.Consequence;
import Models.Map.Direction;
import Models.Map.Map;
import Models.Map.Tile;
import Utilities.MapUtilities.MapNavigationUtilities;
import javafx.geometry.Point3D;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by johnkaufmann on 4/2/16.
 *
 */
public class LinearEffect extends Effect {
    private Direction direction;
    public LinearEffect(int range, Point3D location, Consequence consequence, Direction direction, Map map, BufferedImage decal) {
        super(range, location, consequence, map, decal);
        this.direction = direction;
    }

    @Override
    protected ArrayList<ArrayList<Tile>> getAffectedTiles() {
        // Get the first point
        Point3D firstPoint = getDirection().getPointAdjacentTo(getLocation());
        return MapNavigationUtilities.getLinearTilesInPlane(firstPoint,getRange(),getMap(), getDirection());
    }

    public Direction getDirection() {
        return direction;
    }
}
