package Models.Entities.Skills.InfluenceEffect;

import Models.Consequences.Consequence;
import Models.Map.Map;
import Models.Map.Tile;
import Utilities.MapUtilities.MapNavigationUtilities;
import javafx.geometry.Point3D;

import java.awt.image.BufferedImage;
import java.util.ArrayList;


/**
 * Created by johnkaufmann on 4/2/16.
 */
public class RadialEffect extends Effect {

    public RadialEffect(int range, Point3D location, Consequence consequence, Map map, BufferedImage decal) {

        super(range, location, consequence, map, decal);
    }

    @Override
    protected ArrayList<ArrayList<Tile>> getAffectedTiles() {
        return MapNavigationUtilities.getRadialTiles(getLocation(), getRange(), getMap());
    }

}
