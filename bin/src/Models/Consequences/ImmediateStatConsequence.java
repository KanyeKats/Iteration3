package Models.Consequences;

import Models.Entities.Entity;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Models.Map.Tile;
import Views.DamageToasts;

/**
 * Created by josh on 4/6/16.
 */
public class ImmediateStatConsequence extends Consequence {
    private StatModificationList statModifiers;

    public ImmediateStatConsequence(StatModificationList statModList){
        statModifiers = statModList;
    }

    public void execute(Entity entity) {
        statModifiers.applyModifications(entity.getStats());

        // Show on view
        for (StatModification mod : statModifiers.getMods()) {
            DamageToasts.addDamageToast(mod.getDelta(), entity);
        }

    }

    public void remove(Entity entity){
        statModifiers.removeModifications(entity.getStats());
    }
}
