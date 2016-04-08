package Models.Entities.Skills.ActiveSkills;

import Models.Entities.Entity;
import Models.Entities.Skills.Consequences.Consequence;
import Models.Entities.Skills.Skill;

/**
 * Created by josh on 4/6/16.
 */
public abstract class ActiveSkill extends Skill {
    protected Consequence consequence;
    protected int cooldownTime;
    protected boolean isCooledDown = true;

    public void activate(Entity entity){
        if(isCooledDown){
            if(percentChanceByLevel())
                consequence.execute(entity);
        }
    }

    //TODO: Finish this here function with some sort of timer or something
    final public void doTheCoolDown(){

    }

    //Calculates a random number, checks if it's more than 0.9^level
    //This value doesn't go below 0.5 until level=7
    protected boolean percentChanceByLevel(){
        if(Math.random() > Math.pow(0.9, level))
            return true;
        else
            return false;
    }
}
