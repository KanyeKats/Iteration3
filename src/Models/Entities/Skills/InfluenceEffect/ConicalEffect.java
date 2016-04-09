package Models.Entities.Skills.InfluenceEffect;

import Models.Consequences.Consequence;
import Models.Map.Map;
import javafx.geometry.Point3D;

/**
 * Created by johnkaufmann on 4/2/16.
 * TODO:
 */
public class ConicalEffect extends Effect {
    public ConicalEffect(int range, Point3D location, Consequence consequence, Map map) {
        super(range, location, consequence, map);
        start();
    }

    @Override
    protected void traverseThroughTiles() {

    }

    @Override
    public Image getImage() {
        return null;
    }
}
