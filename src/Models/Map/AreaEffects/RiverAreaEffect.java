package Models.Map.AreaEffects;

import Models.Entities.Entity;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Models.Entities.Stats.Stats;
import Models.Map.AreaEffect;
import Models.Map.Decal;
import Models.Map.Direction;

import java.awt.*;

/**
 * Created by johnkaufmann on 4/7/16.
 * TODO:
 */
public class RiverAreaEffect extends AreaEffect {
    // Instance variables
    private Direction flowDirection;
    private int flowSpeed;

    public RiverAreaEffect(Direction flowDirection, int flowSpeed) {
        // Set instance variables
        this.flowDirection = flowDirection;
        this.flowSpeed = flowSpeed;

        // Set decal
        this.decal = Decal.river;

        // Set visibility and removability
        this.isVisible = true;
        this.isRemovable = false;
    }

    @Override
    public void activate(Entity entity) {
        // Get his stats
        Stats stats = entity.getStats();

        // Get his current movement speed
        int originalSpeed = stats.getStat(Stat.MOVEMENT);

        // Create movement speed mod of the speed of the river's flow
        StatModificationList movementSpeedMods = new StatModificationList(new StatModification(Stat.MOVEMENT, flowSpeed));

        // Modify the entity's movement speed to the rivers flow speed
        movementSpeedMods.applyModifications(stats);

        // Now, move the entity in the appropriate direction with his buffed up river flow speed
        entity.move(flowDirection);

        // Once he's done moving, remove the movement speed buff and set back his original movement speed

        // Create movement speed mod of entity's original speed
        StatModificationList removeRiverSpeedMods = new StatModificationList(new StatModification(Stat.MOVEMENT, originalSpeed));

        // Set the entity's sped back
        removeRiverSpeedMods.applyModifications(stats);
    }

    @Override
    public Image getImage() {
        return null;
    }
}
