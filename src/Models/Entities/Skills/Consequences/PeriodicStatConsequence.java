package Models.Entities.Skills.Consequences;

import Models.Entities.Entity;
import Models.Entities.Stats.StatModificationList;

/**
 * Created by josh on 4/6/16.
 */
public class PeriodicStatConsequence extends Consequence {
    private int period;
    private int numCycles;
    private StatModificationList statModifiers;

    public PeriodicStatConsequence(StatModificationList statModList){
        statModifiers = statModList;
    }

    //TODO: Make this only happen once every period using a timer
    public void execute(Entity entity) {
        statModifiers.applyModifications(entity.getStats());
    }

    public Consequence makeTrap(int damage, int speedReduction) {
        return null;
    }
}
