package Models.Entities.Skills.ActiveSkills.SummonerSkills.Boons;

import Models.Entities.Skills.Consequences.ImmediateStatConsequence;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;

/**
 * Created by josh on 4/6/16.
 */
public class BabyEinstein extends Boon {

    private final int BASE_COOLDOWN_TIME = 20000;       //20 seconds
    private final int BASE_DEFENSE_BOOST = 2;
    private final int BASE_ACTIVE_TIME = 10000;          //10 seconds

    public BabyEinstein(){
        StatModification intellectStatMod = new StatModification(Stat.INTELLECT, BASE_DEFENSE_BOOST);
        consequence = new ImmediateStatConsequence(new StatModificationList(intellectStatMod));
        cooldownTime = BASE_COOLDOWN_TIME;
        activeTime = BASE_ACTIVE_TIME;
    }

    @Override
    public void incrementLevel(){
        ++level;
        StatModification intellectStatMod = new StatModification(Stat.INTELLECT, BASE_DEFENSE_BOOST*level);
        consequence = new ImmediateStatConsequence(new StatModificationList(intellectStatMod));
        activeTime = (int)(level * 0.6 * BASE_ACTIVE_TIME);
    }
}
