package Models.Entities.Skills.ActiveSkills.SummonerSkills.Boons;

import Models.Entities.Entity;
import Models.Entities.Skills.ActiveSkills.ActiveSkill;

/**
 * Created by josh on 4/6/16.
 */
public abstract class Boon extends ActiveSkill{
    protected int activeTime;

    //TODO: Handle the active time of this boon. Some sort of timer
    @Override
    public void activate(Entity entity){
        if(isCooledDown){
            if(percentChanceByLevel())
                consequence.execute(entity);
        }
    }

    //TODO: Implement this when the activeTime timer stops
    private void removeBoonFromEntity(Entity entity){

    }
}
