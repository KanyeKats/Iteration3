package Models.Entities.Skills.ActiveSkills.SummonerSkills.Boons;

import Models.Consequences.ImmediateStatConsequence;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;

/**
 * Created by josh on 4/6/16.
 */
public class Shield extends Boon {

    private final int BASE_COOLDOWN_TIME = 20000;       //20 seconds
    private final int BASE_DEFENSE_BOOST = 100;
    private final int BASE_ACTIVE_TIME = 10000;          //10 seconds
    private final int BASE_MANA_REQUIRED = 5;
    private final int MANA_LEVEL_MULTIPLIER = 1;

    public Shield(){
        StatModification defenseStatMod = new StatModification(Stat.HARDINESS, BASE_DEFENSE_BOOST);
        consequence = new ImmediateStatConsequence(new StatModificationList(defenseStatMod));
        cooldownTime = BASE_COOLDOWN_TIME;
        activeTime = BASE_ACTIVE_TIME;
        manaRequired = BASE_MANA_REQUIRED;
        manaLevelMultiplier = MANA_LEVEL_MULTIPLIER;
    }

    @Override
    public void incrementLevel(){
        ++level;
        StatModification defenseStatMod = new StatModification(Stat.HARDINESS, BASE_DEFENSE_BOOST*level);
        consequence = new ImmediateStatConsequence(new StatModificationList(defenseStatMod));
        activeTime = (int)(level * 0.6 * BASE_ACTIVE_TIME);
    }

    @Override
    public String toString(){
        return "Shield";
    }
}
