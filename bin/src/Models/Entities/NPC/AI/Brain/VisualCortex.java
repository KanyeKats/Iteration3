package Models.Entities.NPC.AI.Brain;

import Models.Entities.Entity;
import Models.Entities.NPC.AI.VisualInfo;
import Models.Entities.NPC.NPC;
import Models.Entities.Stats.Stat;
import Models.Items.Item;
import Models.Map.AreaEffect;
import Models.Map.Direction;
import Models.Map.Map;
import Models.Map.Tile;
import Utilities.MapUtilities.MapNavigationUtilities;
import javafx.geometry.Point3D;

import java.util.ArrayList;

/**
 * Created by Aidan on 4/7/2016.
 */
public class VisualCortex {

    public VisualInfo look(NPC npc, Map map){

        // Create a new visual info object
        VisualInfo visualInfo = new VisualInfo();

        // Set up variables we will need to find the seen tiles.
        Direction direction = npc.getDirection();
        int visualRange = npc.getStats().getStat(Stat.RADIUS_OF_VISIBILITY);
        Point3D startingPoint = npc.getLocation();

        // Find the tiles within the visual range of the npc.
        ArrayList<Point3D> pointsSeen = MapNavigationUtilities.getConicalPoints(startingPoint, visualRange, map, direction);

        // Loop through the points and add the stuff that is seen to teh Visual Info object.
        for(Point3D point : pointsSeen){
            Tile tile = map.getTile(point);
            if(tile!=null){
                Entity entity = tile.getEntity();
                boolean entityIsVisible = tile.containsVisibleEntity();
                ArrayList<Item> item = tile.getItems();
                AreaEffect effect = tile.getAreaEffect();

                if(entity!=null && entityIsVisible){
                    visualInfo.addEntity(entity);
                }
                if(!item.isEmpty()){
                    visualInfo.addItem(point);
                }
                if(effect!=null){
                    visualInfo.addAreaEffect(point);
                }
            }
        }

        return visualInfo;
    }
}
