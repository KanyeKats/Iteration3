package Models.Entities.Skills.InfluenceEffect;

import Models.Consequences.Consequence;
import Models.Entities.Entity;
import Models.Map.Direction;
import Models.Map.Map;
import Models.Map.Tile;
import Utilities.MapNavigationUtilities;
import javafx.geometry.Point3D;
import java.awt.Image;
import java.util.ArrayList;

/**
 * Created by johnkaufmann on 4/2/16.
 */
public class AngularEffect extends Effect {
    Direction direction;

    public AngularEffect(int range, Point3D location, Consequence consequence, Map map, Direction direction) {
        super(range, location, consequence, map);
        this.direction = direction;
        start();
    }

    @Override
    protected ArrayList<ArrayList<Tile>> getAffectedTiles() {
        return MapNavigationUtilities.getTilesInAngularPlane(getLocation(),getRange(),getMap(), getDirection());
    }

    @Override
    protected void traverseThroughTiles(ArrayList<Tile> tiles) {
        for (Tile tile : tiles) {
            Entity entity = getEntity(tile);
            if (hasEntity(entity)) {
                dealConsequence(entity);
            }
        }
    }

    public Direction getDirection() {
        return direction;
    }

  @Override
   public Image getImage() {
       return null;
   }
}
