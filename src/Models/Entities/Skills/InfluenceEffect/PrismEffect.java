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
 */
public class PrismEffect extends Effect{
    private int range2;
    public PrismEffect(int range1, int range2, Point3D location, Consequence consequence, Map map) {
        super(range1, location, consequence, map);
        this.range2 = range2;
        start();
    }

    @Override
    protected ArrayList<ArrayList<Tile>> getAffectedTiles() {
        return MapNavigationUtilities.getTilesinPrism(getLocation(),getRange(), getRange2(), getMap());
    }

    public int getRange2() {
        return range2;
    }

    @Override
    public Image getImage() {
        return null;
    }
}

