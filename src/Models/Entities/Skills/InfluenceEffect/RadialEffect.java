package Models.Entities.Skills.InfluenceEffect;

import Models.Entities.Skills.Consequences.Consequence;
import Models.Map.Map;
import javafx.geometry.Point3D;

import java.awt.*;


/**
 * Created by johnkaufmann on 4/2/16.
 * TODO:
 */
public class RadialEffect extends Effect {

    public RadialEffect(int range, Point3D location, Consequence consequence, Map map) {

        super(range, location, consequence, map);
        start();
    }

    //propagate radially
    @Override
    protected void traverseThroughTiles() {

    }

    @Override
    public Image getImage() {
        return null;
    }
}
