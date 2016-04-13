package Models.Entities.Skills.InfluenceEffect;

import Models.Consequences.Consequence;
import Models.Map.Direction;
import Models.Map.Map;
import Models.Map.Tile;
import javafx.geometry.Point3D;
import java.awt.Image;
import java.util.ArrayList;

/**
 * Created by johnkaufmann on 4/2/16.
 * TODO:
 */
public class ConicalEffect extends Effect {

    private Direction direction;

    public ConicalEffect(int range, Point3D location, Consequence consequence, Direction direction, Map map) {
        super(range, location, consequence ,map);
        this.direction = direction;
        start();
    }

    @Override
    protected ArrayList<ArrayList<Tile>> getAffectedTiles() {
        return null;
    }

    @Override
    protected void traverseThroughTiles(ArrayList<Tile> tiles) {

    }

    @Override
    public Image getImage() {
        return null;
    }
}
