package Models.Entities.Skills.ActiveSkills.SneakSkills;

import Models.Entities.Entity;
import Models.Entities.Skills.ActiveSkills.ActiveSkill;
import Models.Map.AreaEffect;
import Models.Map.Map;
import Models.Map.Tile;

/**
 * Created by josh on 4/6/16.
 */
public class RemoveTrap extends ActiveSkill {

    public final int BASE_COOLDOWN_TIME = 20000;    //20 seconds

    public RemoveTrap(){
        cooldownTime = BASE_COOLDOWN_TIME;
    }

    //TODO: Implement checking the map and removing trap if there is one
    public void activate(Entity entity){

        Tile potentialTrapTile = entity.getTileInFront();
        AreaEffect potentialTrap = potentialTrapTile.getAreaEffect();

        //if there is a trap
        if(potentialTrap != null && potentialTrap.isRemovable()){
            if(isCooledDown) {
                if (percentChanceByLevel()) {
                    //Implement removing a trap
                    potentialTrapTile.removeAreaEffect();
                }
            }
        }
    }

    @Override
    public String toString(){
        return "Remove Trap";
    }
}
