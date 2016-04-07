package Models.Map.AreaEffects;

import Models.Entities.Skills.Consequences.ImmediateStatConsequence;
import Models.Map.AreaEffect;
import Models.Map.Decal;

import java.util.Random;

/**
 * Created by johnkaufmann on 4/7/16.
 */
public class TrapAreaEffect extends AreaEffect {
    private boolean isVisible;
    public TrapAreaEffect(int damage) {
        super(new ImmediateStatConsequence().makeDamage(damage), Decal.trap);
    }

    public void attemptToDetectTrap(int chanceToDetect) {
        Random rand = new Random();

        int  n = rand.nextInt(100);

        if (chanceToDetect < n) return;
        else this.isVisible = true;
    }

    public boolean isVisible() {
        return isVisible;
    }
}
