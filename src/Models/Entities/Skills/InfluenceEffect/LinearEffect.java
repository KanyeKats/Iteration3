package Models.Entities.Skills.InfluenceEffect;

import Models.Consequences.Consequence;
import Models.Map.Direction;
import Models.Map.Map;
import Models.Map.Tile;
import javafx.geometry.Point3D;
import java.awt.Image;

/**
 * Created by johnkaufmann on 4/2/16.
 * TODO:
 */
public class LinearEffect extends Effect {
    private final int range;
    private final Point3D location;
    private final Consequence consequence;
    private final Direction direction;
    private final Map map;

    public LinearEffect(int range, Point3D location, Consequence consequence, Direction direction, Map map) {
        super(range, location, consequence, direction, map);
        this.range = range;
        this.location = location;
        this.consequence = consequence;
        this.direction = direction;
        this.map = map;
        start();
    }

    @Override
    public void traverseThroughTiles() {

        //Linear effects need a visual effect to accompany this motion
        Point3D point = direction.getPointAdjacentTo(location);
        for(int i = 0; i < range; i++ ) {
            Tile tile = map.getTile(point);
            if (tile.getEntity() != null) {
                consequence.execute(tile.getEntity());
                break;
            }
            point = direction.getPointAdjacentTo(point);
        }
    }

    @Override
    public Image getImage() {
        return null;
    }
}
