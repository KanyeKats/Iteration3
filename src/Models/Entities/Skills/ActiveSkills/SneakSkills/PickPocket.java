package Models.Entities.Skills.ActiveSkills.SneakSkills;

import Models.Entities.Entity;
import Models.Entities.Skills.ActiveSkills.ActiveSkill;

/**
 * Created by josh on 4/6/16.
 */
public class PickPocket extends ActiveSkill {

    public final int BASE_COOLDOWN_TIME = 20000;    //20 seconds

    public PickPocket(){
        cooldownTime = BASE_COOLDOWN_TIME;
    }

    //TODO: Implement the actual stealing of an item
    public void activate(Entity entity){
        if(isCooledDown){
            if(percentChanceByLevel()){
                //Implement stealing an item
            }
        }
    }
}
