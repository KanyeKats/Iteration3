package Models.Entities.Skills.InfluenceEffect;

import Models.Consequences.Consequence;
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
public class RadialEffect extends Effect {

    public RadialEffect(int range, Point3D location, Consequence consequence, Map map) {

        super(range, location, consequence, map);
        start();
    }

    @Override
    protected ArrayList<ArrayList<Tile>> getAffectedTiles() {
        return MapNavigationUtilities.getTilesinPlane(getLocation(),getRange(),getMap());
    }

    @Override
    public Image getImage() {
        return null;
    }
}
