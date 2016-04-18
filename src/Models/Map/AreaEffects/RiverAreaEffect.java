package Models.Map.AreaEffects;

import Models.Entities.Entity;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.Stats;
import Models.Map.AreaEffect;
import Models.Map.Decal;
import Models.Map.Direction;
import Views.Graphics.Assets;

import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Created by johnkaufmann on 4/7/16.
 *
 */
public class RiverAreaEffect extends AreaEffect {
    // Instance variables
    private Direction flowDirection;
    private int flowSpeed;

    private HashMap<Direction, BufferedImage> flowImages;

    public RiverAreaEffect(Direction flowDirection, int flowSpeed) {
        // Set instance variables
        this.flowDirection = flowDirection;
        this.flowSpeed = flowSpeed;

        // Create hashmap of direction to image
        initFlowImages();

        // Set decal
        this.decal = new Decal(flowImages.get(this.flowDirection));
//        this.decal = Decal.river();

        // Set visibility and removability
        this.isVisible = true;
        this.isRemovable = false;
    }

    private void initFlowImages(){
        this.flowImages = new HashMap<>();
        flowImages.put(Direction.NORTH, Assets.FLOW_N);
        flowImages.put(Direction.NORTH_EAST, Assets.FLOW_NE);
        flowImages.put(Direction.SOUTH_EAST, Assets.FLOW_SE);
        flowImages.put(Direction.SOUTH, Assets.FLOW_S);
        flowImages.put(Direction.SOUTH_WEST, Assets.FLOW_SW);
        flowImages.put(Direction.NORTH_WEST, Assets.FLOW_NW);
    }

    @Override
    public void activate(Entity entity) {
        // Get his stats
        Stats stats = entity.getStats();

        // Get his current movement speed
        int entitysOriginalSpeed = stats.getStat(Stat.MOVEMENT);

        int netFlowSpeed = flowSpeed - entitysOriginalSpeed;

        if (netFlowSpeed <= 0) return;

        // Create movement speed mod of the speed of the river's flow
        stats.setStat(Stat.MOVEMENT, flowSpeed);

        // Now, move the entity in the appropriate direction with his buffed up river flow speed
        entity.move(flowDirection);

        // Once he's done moving, remove the movement speed buff and set back his original movement speed
        // Set the entity's sped back
        stats.setStat(Stat.MOVEMENT, entitysOriginalSpeed);
    }

}
