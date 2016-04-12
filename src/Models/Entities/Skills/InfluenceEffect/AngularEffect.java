package Models.Entities.Skills.InfluenceEffect;

import Models.Consequences.Consequence;
import Models.Entities.Entity;
import Models.Map.Direction;
import Models.Map.Map;
import javafx.geometry.Point3D;
import java.awt.Image;

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
    protected void traverseThroughTiles() {

    }

    public Direction getDirection() {
        return direction;
    }

  @Override
   public Image getImage() {
       return null;
   }
}
