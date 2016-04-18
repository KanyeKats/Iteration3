package Models.Consequences;

import Models.Entities.Entity;
import Models.Entities.Stats.StatModificationList;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by josh on 4/6/16.
 */
public class PeriodicStatConsequence extends Consequence {
    private int period;
    private int numCycles, cycleCounter;
    private StatModificationList statModifiers;
    private Timer timer = new Timer();
    private Entity entity;

    public PeriodicStatConsequence(StatModificationList statModList, int period, int numCycles){
        statModifiers = statModList;
        this.period = period;
        this.numCycles = numCycles;
    }

    public void execute(Entity e) {
        this.entity = e;
        cycleCounter = 0;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                executePeriodically(entity);
            }
        }, 0, period);
    }

    //Shouldn't do anything, this consequence isn't temporary...
    public void remove(Entity entity){}

    private void executePeriodically(Entity entity){
        if(cycleCounter < numCycles) {
            statModifiers.applyModifications(entity.getStats());
            ++cycleCounter;
        }
        else{
            timer.cancel();
            timer.purge();
        }
    }
}
