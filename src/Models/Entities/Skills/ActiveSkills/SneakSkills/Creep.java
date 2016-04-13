package Models.Entities.Skills.ActiveSkills.SneakSkills;

import Models.Entities.Entity;
import Models.Entities.Skills.ActiveSkills.ActiveSkill;

import java.util.TimerTask;

/**
 * Created by josh on 4/6/16.
 */
public class Creep extends ActiveSkill {

    public final int BASE_COOLDOWN_TIME = 20000;    //20 seconds
    public final int BASE_EFFECT_TIME = 10000;      //10 seconds
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
    public String toString(){
        return "Creep";
    }
}
