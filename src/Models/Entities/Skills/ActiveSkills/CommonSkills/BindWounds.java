package Models.Entities.Skills.ActiveSkills.CommonSkills;

import Models.Entities.Skills.ActiveSkills.ActiveSkill;
import Models.Consequences.ImmediateStatConsequence;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;

/**
 * Created by josh on 4/6/16.
 */
public class BindWounds extends ActiveSkill {

    private final int BASE_COOLDOWN_TIME = 10000;       //10 seconds
    private final int BASE_HEAL_AMOUNT = 5;
    private final int BASE_MANA_REQUIRED = 5;
    private final int MANA_LEVEL_MULTIPLIER = 1;


    public BindWounds(){
        StatModification healthStatMod = new StatModification(Stat.HEALTH, BASE_HEAL_AMOUNT);
        consequence = new ImmediateStatConsequence(new StatModificationList(healthStatMod));
        cooldownTime = BASE_COOLDOWN_TIME;
        manaRequired = BASE_MANA_REQUIRED;
        manaLevelMultiplier = MANA_LEVEL_MULTIPLIER;
    }

    @Override
    public void incrementLevel(){
        ++level;
        StatModification healthStatMod = new StatModification(Stat.HEALTH, BASE_HEAL_AMOUNT*level);
        consequence = new ImmediateStatConsequence(new StatModificationList(healthStatMod));
    }

    @Override
    public String toString(){
        return "Bind Wounds";
    }
}
