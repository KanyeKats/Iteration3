package Models.Map.AreaEffects;

import Models.Entities.Entity;
import Models.Entities.Skills.Consequences.BehaviorConsequence;
import Models.Entities.Skills.Consequences.ImmediateStatConsequence;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Models.Map.AreaEffect;
import Models.Map.Decal;

import java.util.Random;

/**
 * Created by johnkaufmann on 4/7/16.
 */
public class TrapAreaEffect extends AreaEffect {

    // Time the entity is trapped for
    private int trappedTime;

    public TrapAreaEffect(int trappedTime) {
        // Set time
        this.trappedTime = trappedTime;

        // Set decal
        this.decal = Decal.trap;

        // Set visibility and removability
        this.isVisible = false;
        this.isRemovable = true;
    }

    @Override
    public void activate(Entity entity) {
        // TODO: we need to create a behavior consequence that stops movement for trap!
        BehaviorConsequence consequence = new BehaviorConsequence(trappedTime);

        // Execute the consequence
        consequence.execute(entity);
    }

    // TODO: Not sure if this should be here? maybe in detect trap skill?
    public void attemptToDetectTrap(int chanceToDetect) {
        Random rand = new Random();

        int  n = rand.nextInt(100);

        if (chanceToDetect < n) return;
        else this.isVisible = true;
    }
}
