package Models.Entities.Skills.InfluenceEffect;

import Models.Consequences.Consequence;
import Models.Map.Map;
import Models.Map.Tile;
import Utilities.MapUtilities.MapNavigationUtilities;
import javafx.geometry.Point3D;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Aidan on 4/10/2016.
 */
public class SphericalEffect extends Effect {

    public SphericalEffect(int range, Point3D location, Consequence consequence, Map map){
        super(range,location,consequence,map);
        start();
    }

    @Override
    protected ArrayList<ArrayList<Tile>> getAffectedTiles() {
        return MapNavigationUtilities.getTilesinSphere(getLocation(), getRange(), getMap());
    }

    @Override
    public Image getImage() {
        return null;
    }
}
