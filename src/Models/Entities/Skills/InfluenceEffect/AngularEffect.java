package Models.Entities.Skills.InfluenceEffect;

import Models.Consequences.Consequence;
import Models.Map.Direction;
import Models.Map.Map;
import javafx.geometry.Point3D;
import java.awt.Image;

/**
 * Created by johnkaufmann on 4/2/16.
 * TODO:
 */
public class AngularEffect extends Effect {
    private Direction direction;
    public AngularEffect(int range, Point3D location, Consequence consequence, Direction direction, Map map) {
        super(range, location, consequence, map);
        this.direction = direction;
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
