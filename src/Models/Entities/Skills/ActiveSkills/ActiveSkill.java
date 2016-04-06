package Models.Entities.Skills.ActiveSkills;

import Models.Entities.Entity;
import Models.Entities.Skills.Consequences.Consequence;
import Models.Entities.Skills.Skill;

/**
 * Created by josh on 4/6/16.
 */
public abstract class ActiveSkill extends Skill {
    private Consequence consequence;
    private int cooldownTime;

    public abstract void activate(Entity entity);

    //Finish this later
    final public void doTheCoolDown(){

    }
}
