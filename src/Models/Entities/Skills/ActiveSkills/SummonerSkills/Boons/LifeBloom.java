package Models.Entities.Skills.ActiveSkills.SummonerSkills.Boons;

import Models.Entities.Skills.Consequences.PeriodicStatConsequence;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;

/**
 * Created by josh on 4/6/16.
 */
public class LifeBloom extends Boon {

    private final int BASE_COOLDOWN_TIME = 20000;       //20 seconds
    private final int BASE_HEAL_AMOUNT = 2;
    private final int BASE_ACTIVE_TIME = 5000;          //5 seconds

    public LifeBloom(){
        StatModification healthStatMod = new StatModification(Stat.HEALTH, BASE_HEAL_AMOUNT);
        consequence = new PeriodicStatConsequence(new StatModificationList(healthStatMod));
        cooldownTime = BASE_COOLDOWN_TIME;
        activeTime = BASE_ACTIVE_TIME;
    }

    @Override
    public void incrementLevel(){
        ++level;
        StatModification healthStatMod = new StatModification(Stat.HEALTH, BASE_HEAL_AMOUNT*level);
        consequence = new PeriodicStatConsequence(new StatModificationList(healthStatMod));
        activeTime = (int)(level * 0.6 * BASE_ACTIVE_TIME);
    }
}
