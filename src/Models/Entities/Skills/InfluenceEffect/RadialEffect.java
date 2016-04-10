package Models.Entities.Skills.InfluenceEffect;

import Models.Consequences.Consequence;
import Models.Map.Map;
import Models.Map.Tile;
import Utilities.MapNavigationUtilities;
import javafx.geometry.Point3D;
import java.awt.Image;
import java.util.ArrayList;


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

        //I have this part in now we need to do visual effects
        ArrayList<Tile> tilesinRange = MapNavigationUtilities.getTilesinPlane(location,range,map);
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
