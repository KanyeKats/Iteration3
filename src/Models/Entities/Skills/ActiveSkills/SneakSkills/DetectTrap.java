package Models.Entities.Skills.ActiveSkills.SneakSkills;

import Models.Entities.Entity;
import Models.Entities.Skills.ActiveSkills.ActiveSkill;

/**
 * Created by josh on 4/6/16.
 */
public class DetectTrap extends ActiveSkill {

    public final int BASE_COOLDOWN_TIME = 20000;    //20 seconds

    public DetectTrap(){
        cooldownTime = BASE_COOLDOWN_TIME;
    }

    //TODO: Implement checking the map and making trap visible if there is one
    public void activate(Entity entity){
        //if there is a trap
        if(isCooledDown){
            if(percentChanceByLevel()){
                //Implement making a trap visible
            }
        }
    }
}
