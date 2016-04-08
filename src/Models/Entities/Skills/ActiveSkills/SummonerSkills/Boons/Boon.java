package Models.Entities.Skills.ActiveSkills.SummonerSkills.Boons;

import Models.Entities.Entity;
import Models.Entities.Skills.ActiveSkills.ActiveSkill;

import java.util.TimerTask;

/**
 * Created by josh on 4/6/16.
 */
public abstract class Boon extends ActiveSkill{
    protected int activeTime;
    protected Entity entity;

    @Override
    public void activate(Entity e){
        if(isCooledDown){
            if(percentChanceByLevel()) {
                this.entity = e;
                consequence.execute(e);
                isCooledDown = false;


                effectTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        consequence.remove(entity);
                        doTheCoolDown();
                    }
                }, activeTime);

            }
        }
    }
}
