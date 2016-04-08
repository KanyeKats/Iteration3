package Models.Entities.Skills.ActiveSkills.SummonerSkills.Enchantments;

import Models.Entities.Entity;
import Models.Entities.Skills.ActiveSkills.ActiveSkill;
import Models.Entities.Skills.Effects.Effect;

/**
 * Created by josh on 4/6/16.
 */
public abstract class Enchantment extends ActiveSkill{
    protected int activeTime;
    protected Effect effect;

    //TODO: Handle the active time of this enchantment. Some sort of timer
    @Override
    public void activate(Entity entity){
        if(isCooledDown){
            if(percentChanceByLevel())
                effect.run();
        }
    }

    //TODO: Implement this when the activeTime timer stops
    private void removeEnchantmentFromEntity(Entity entity){

    }
}
