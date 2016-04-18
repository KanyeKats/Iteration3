package Models.Entities.Skills.ActiveSkills.SneakSkills;

import Models.Consequences.ImmediateStatConsequence;
import Models.Consequences.TimedConsequence;
import Models.Entities.Entity;
import Models.Entities.Skills.ActiveSkills.ActiveSkill;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;

import java.util.TimerTask;

/**
 * Created by josh on 4/6/16.
 */
public class Creep extends ActiveSkill {

    public final int BASE_COOLDOWN_TIME = 20000;    //20 seconds
    public final int BASE_EFFECT_TIME = 10000;      //10 seconds
    private final int BASE_MOVEMENT_DECREMENT = -6;
    private final int BASE_OFFENSIVE_INCREMENT = 3;
    private Entity entity;

    public Creep(){
        cooldownTime = BASE_COOLDOWN_TIME;
    }

    public void activate(Entity e){
        if(isCooledDown){
            if(percentChanceByLevel()){
                e.setVisible(false);
                isCooledDown = false;
                this.entity = e;
                this.consequence.execute(e);

                effectTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        entity.setVisible(true);
                        doTheCoolDown();
                    }
                }, BASE_EFFECT_TIME);

            }
        }
    }

    @Override
    public void incrementLevel() {
        ++level;
        StatModificationList statModificationList = new StatModificationList(
                new StatModification(Stat.MOVEMENT,BASE_MOVEMENT_DECREMENT-level),
                new StatModification(Stat.OFFSENSIVE_RATING,BASE_OFFENSIVE_INCREMENT*level));
        this.consequence = new TimedConsequence(statModificationList, BASE_EFFECT_TIME);
    }

    @Override
    public String toString(){
        return "Creep";
    }
}
