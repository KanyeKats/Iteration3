package Models.Entities.Skills.ActiveSkills.SneakSkills;

import Models.Entities.Entity;
import Models.Entities.Skills.ActiveSkills.ActiveSkill;
import Models.Map.AreaEffect;
import Models.Map.Map;

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
        //if there is a trap

        //TODO: Trap is detected but how do i know if the area effect I am facing is a trap?

        //Stringed so many calls together
        //TODO: Use the get tile in front method
        AreaEffect potentialTrap = entity.getMap().getTile(entity.getDirection().getPointAdjacentTo(entity.getLocation())).getAreaEffect();
        if(potentialTrap != null && potentialTrap.isRemovable()){
            if(isCooledDown) {
                if (percentChanceByLevel()) {
                    //Implement removing a trap
                }
            }
        }
    }
}
