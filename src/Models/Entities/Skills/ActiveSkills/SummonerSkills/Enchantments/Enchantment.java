package Models.Entities.Skills.ActiveSkills.SummonerSkills.Enchantments;

import Models.Entities.Entity;
import Models.Entities.Skills.ActiveSkills.ActiveSkill;
import Models.Entities.Skills.InfluenceEffect.Effect;

/**
 * Created by josh on 4/6/16.
 */
public abstract class Enchantment extends ActiveSkill{
    protected int activeTime;
    protected Effect effect;
    protected final int BASE_RANGE = 4;

    @Override
    public void activate(Entity entity){
        if(isCooledDown){
            if(percentChanceByLevel())
                effect.start();
        }
    }

    //TODO: Implement this when the activeTime timer stops. Don't think this is done here...pretty sure it's done by the consequence...
    private void removeEnchantmentFromEntity(Entity entity){

    }
}
