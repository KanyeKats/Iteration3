package Models.Entities.Skills.ActiveSkills.SummonerSkills.Boons;

import Models.Consequences.ImmediateStatConsequence;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;

/**
 * Created by josh on 4/6/16.
 */
public class LudaSpeed extends Boon {

    private final int BASE_COOLDOWN_TIME = 20000;       //20 seconds
    private final int BASE_SPEED_BOOST = 2;
    private final int BASE_ACTIVE_TIME = 5000;          //5 seconds

    public LudaSpeed(){
        StatModification speedStatMod = new StatModification(Stat.MOVEMENT, BASE_SPEED_BOOST);
        consequence = new ImmediateStatConsequence(new StatModificationList(speedStatMod));
        cooldownTime = BASE_COOLDOWN_TIME;
        activeTime = BASE_ACTIVE_TIME;
    }

    @Override
    public void incrementLevel(){
        ++level;
        StatModification speedStatMod = new StatModification(Stat.MOVEMENT, BASE_SPEED_BOOST*level);
        consequence = new ImmediateStatConsequence(new StatModificationList(speedStatMod));
        activeTime = (int)(level * 0.6 * BASE_ACTIVE_TIME);
    }

    @Override
    public String toString(){
        return "Luda Speed";
    }
}
