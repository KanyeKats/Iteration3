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
public class PrismEffect extends Effect{
    private int range2;
    public PrismEffect(int range1, int range2, Point3D location, Consequence consequence, Map map, BufferedImage decal) {
        super(range1, location, consequence, map, decal);
        this.range2 = range2;
    }

    @Override
    protected ArrayList<ArrayList<Tile>> getAffectedTiles() {
        return MapNavigationUtilities.getTilesinPrism(getLocation(),getRange(), getRange2(), getMap());
    }

    public int getRange2() {
        return range2;
    }

}

