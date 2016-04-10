package Models.Entities.Skills.InfluenceEffect;

import Models.Consequences.Consequence;
import Models.Map.Direction;
import Models.Map.Map;
import Models.Map.Tile;
import Utilities.MapUtilities.MapNavigationUtilities;
import javafx.geometry.Point3D;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Aidan on 4/10/2016.
 */
public class SphericalEffect extends Effect {

    public SphericalEffect(int range, Point3D location, Consequence consequence, Direction direction, Map map){
        super(range,location,consequence, direction,map);
        start();
    }

    @Override
    protected void traverseThroughTiles() {

        ArrayList<Tile> tilesinRange = MapNavigationUtilities.getTilesinSphere(location,range,map);
        for(Tile tile: tilesinRange){
            if(tile.getEntity() != null){
                consequence.execute(tile.getEntity());
            }
        }
    }

    @Override
    public Image getImage() {
        return null;
    }
}
