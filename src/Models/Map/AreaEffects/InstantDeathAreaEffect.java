package Models.Map.AreaEffects;

import Models.Entities.Entity;
import Models.Entities.Skills.Consequences.ImmediateStatConsequence;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Models.Entities.Stats.Stats;
import Models.Map.AreaEffect;
import Models.Map.Decal;

/**
 * Created by johnkaufmann on 4/7/16.
 * TODO:
 */
public class InstantDeathAreaEffect extends AreaEffect {
    public InstantDeathAreaEffect() {
        // Set decal
        this.decal = Decal.level;

        // Set visibility and removability
        this.isVisible = true;
        this.isRemovable = false;
    }

    @Override
    public void activate(Entity entity) {
        // Get the entity's stats
        Stats stats = entity.getStats();

        // Get the max health of the entity
        int maxHealth = stats.getMaxHealth();

        // Create a Stat mod list of one single instant death stat mod
        StatModification deathMod = new StatModification(Stat.HEALTH, -maxHealth);
        StatModificationList deathStatMods = new StatModificationList(deathMod);

        // Set this area effects consequence to a new immediate stat consequence
        // Which kills the entity, using the stat mod we just made
        ImmediateStatConsequence consequence = new ImmediateStatConsequence(deathStatMods);

        // Execute the entity... LITERALLY!
        consequence.execute(entity);
    }
}