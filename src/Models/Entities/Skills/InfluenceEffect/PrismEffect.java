package Models.Entities.Skills.InfluenceEffect;

import Models.Consequences.Consequence;
import Models.Map.Direction;
import Models.Map.Map;
import Models.Map.Tile;
import Utilities.MapUtilities.MapNavigationUtilities;
import javafx.geometry.Point3D;
import java.awt.Image;
import java.util.ArrayList;

/**
 * Created by johnkaufmann on 4/2/16.
 * TODO:
 */
public class PrismEffect extends Effect{
    private Direction direction;
    private int range2;
    public PrismEffect(int range1, int range2, Point3D location, Consequence consequence, Direction direction, Map map) {
        super(range1, location, consequence, map);
        this.direction = direction;
        this.range2 = range2;
        start();
    }

    @Override
    protected void traverseThroughTiles() {
        //TODO: does the prism effect have two dimensions of range it needs? both how many columns there are as well as the comlumn's height?
        ArrayList<Tile> tilesinRange = MapNavigationUtilities.getTilesinPrism(location,range,range2,map);
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

