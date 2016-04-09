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

    private boolean tileHasNPC(Entity entity){
        Entity npc = entity.getTileInFront().getEntity();
        if(npc != null)
            return true;
        else
            return false;
    }

    private void stealItem(Entity entity){
        Entity npc = entity.getTileInFront().getEntity();
        entity.addItemToInventory(npc.getInventory().removeRandomItem());
    }
}
