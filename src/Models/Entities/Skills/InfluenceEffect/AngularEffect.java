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
        Point3D point = direction.getPointAdjacentTo(getLocation());

        for (int i = 1; i < getRange(); i++) {
            //apply effect to the point you moved to then expand out
            super.apply(point);

            //set up for expansion on both sides
            Point3D leftSide = point; //curr point + 4
            Point3D rightSide = point; //curr point + 2

            //if even then expand out i/2 in both directions
            //if odd expand out i/2 (truncated) in both directions
            for (int j = 0; j < i/2; j++) {
                leftSide = rotateEnum(4).getPointAdjacentTo(leftSide);
                rightSide = rotateEnum(2).getPointAdjacentTo(rightSide);
                apply(leftSide);
                apply(rightSide);
            }

            //move to the next radius
            point = direction.getPointAdjacentTo(point);
        }
    }

    //rotate the enum given an integer
    private Direction rotateEnum(int i) {
        return getDirection().values()[getDirection().ordinal() + i % 5];
    }

    public Direction getDirection() {
        return direction;
    }

  @Override
   public Image getImage() {
       return null;
   }
}
