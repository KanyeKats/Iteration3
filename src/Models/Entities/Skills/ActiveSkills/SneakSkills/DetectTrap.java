package Models.Entities.Skills.ActiveSkills.SneakSkills;

import Models.Entities.Entity;
import Models.Entities.Skills.ActiveSkills.ActiveSkill;
import Models.Map.Map;

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

        //writing this to help myself visualize - Aidan

        Map map = entity.getMap();



        //if there is a trap
        if(isCooledDown){
            if(percentChanceByLevel()){
                //Implement making a trap visible
            }
        }
    }
}
