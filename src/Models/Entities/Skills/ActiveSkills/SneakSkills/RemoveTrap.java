package Models.Entities.Skills.ActiveSkills.SneakSkills;

import Models.Entities.Entity;
import Models.Entities.Skills.ActiveSkills.ActiveSkill;
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
        //Map map = entity.getMap();

        //if(map.getTile(entity.getOrientation().getPointAdjacentTo(entity.getLocation())).getAreaEffect().)
        if(isCooledDown){
            if(percentChanceByLevel()){
                //Implement removing a trap
            }
        }
    }
}
