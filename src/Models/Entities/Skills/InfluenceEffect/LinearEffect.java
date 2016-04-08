package Models.Entities.Skills.InfluenceEffect;

import Models.Entities.Skills.Consequences.Consequence;
import Models.Map.Map;
import javafx.geometry.Point3D;

import java.awt.*;

/**
 * Created by johnkaufmann on 4/2/16.
 * TODO:
 */
public class LinearEffect extends Effect {
    public LinearEffect(int range, Point3D location, Consequence consequence, Map map) {
        super(range, location, consequence, map);
        start();
    }

    @Override
    protected void traverseThroughTiles() {

    }
}
