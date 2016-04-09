package Models.Entities.Skills.ActiveSkills.SneakSkills;

import Models.Entities.Entity;
import Models.Entities.Skills.ActiveSkills.ActiveSkill;
import Models.Map.Direction;
import Models.Map.Tile;
import javafx.geometry.Point3D;

import java.util.Random;

/**
 * Created by josh on 4/6/16.
 */
public class PickPocket extends ActiveSkill {

    public final int BASE_COOLDOWN_TIME = 20000;    //20 seconds

    public PickPocket(){
        cooldownTime = BASE_COOLDOWN_TIME;
    }

    public void activate(Entity entity){
        if(isCooledDown && tileHasNPC(entity)){
            if(percentChanceByLevel()){
                stealItem(entity);
            }
        }
    }

    //TODO: Is there too much LoD going on here to be comfortable?
    private boolean tileHasNPC(Entity entity){
        Point3D point = entity.getOrientation().getPointAdjacentTo(entity.getLocation());
        Entity npc = entity.getMap().getEntity(point);
        if(npc != null)
            return true;
        else
            return false;
    }

    //TODO: Is there too much LoD going on here to be comfortable?
    private void stealItem(Entity entity){
        Point3D point = entity.getOrientation().getPointAdjacentTo(entity.getLocation());
        Entity npc = entity.getMap().getEntity(point);
        entity.addItemToInventory(npc.getInventory().removeRandomItem());
    }
}
