package Models.Entities.Skills.ActiveSkills.SummonerSkills.Banes;

import Models.Entities.Entity;
import Models.Entities.Skills.ActiveSkills.ActiveSkill;
import Models.Consequences.ImmediateStatConsequence;
import Models.Entities.Skills.InfluenceEffect.Effect;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;

/**
 * Created by josh on 4/6/16.
 */
public abstract class Bane extends ActiveSkill {

    protected Effect effect;
    protected final int BASE_DAMAGE_AMOUNT = 5;
    protected final int BASE_RANGE = 4;
    private final int BASE_MANA_REQUIRED = 5;
    private final int MANA_LEVEL_MULTIPLIER = 1;


    public Bane(){
        StatModification damageStatMod = new StatModification(Stat.HEALTH, -BASE_DAMAGE_AMOUNT);
        consequence = new ImmediateStatConsequence(new StatModificationList(damageStatMod));
        manaRequired = BASE_MANA_REQUIRED;
        manaLevelMultiplier = MANA_LEVEL_MULTIPLIER;
    }

//    @Override
//    public void activate(Entity entity){
//        System.out.println("ACTIVATE BANE");
//        if(isCooledDown){
//            if(percentChanceByLevel()) {
//                effect.start();
//                isCooledDown = false;
//                doTheCoolDown();
//            }
//        }
//    }

    @Override
    protected void performSkill(Entity entity) {
        effect.start();
    }

    //Calculates a random number, checks if it's more than 0.8^level
    @Override
    protected boolean percentChanceByLevel(){
        // TODO: Remove this after testing.
//        if(Math.random() > Math.pow(0.8, level))
//            return true;
//        else
//            return false;
        return true;
    }
}
