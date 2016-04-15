package Models.Map.AreaEffects;

import Models.Consequences.TrappedConsequence;
import Models.Entities.Entity;
import Models.Consequences.BehaviorConsequence;
import Models.Map.AreaEffect;
import Models.Map.Decal;

import java.awt.*;
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
        this.decal = Decal.trap();

        // Set visibility and removability
        this.isVisible = false;
        this.isRemovable = true;
    }

    @Override
    public void activate(Entity entity) {
        // TODO: we need to create a behavior consequence that stops movement for trap!
        BehaviorConsequence consequence = new TrappedConsequence(trappedTime);

        // Execute the consequence
        consequence.execute(entity);
    }

    // TODO: Not sure if this should be here? maybe in detect trap skill?

    //TODO: I agree this sould not be here, it should be in the trap skill
    //TODO: because it is the skill that is making the trap visiible.  ... - Aidan

    public void attemptToDetectTrap(int chanceToDetect) {
        Random rand = new Random();

        int  n = rand.nextInt(100);

        if (chanceToDetect < n) return;
        else this.isVisible = true;
    }
}
