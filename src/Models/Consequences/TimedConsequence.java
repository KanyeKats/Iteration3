package Models.Consequences;

import Models.Entities.Entity;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Aidan on 4/8/2016.
 */
public class TimedConsequence extends Consequence {

    private int duration;
    private StatModificationList statModificationList;
    private Entity entity;
    private Timer timer = new Timer();

    public TimedConsequence(StatModificationList statModificationList, int duration){
        this.statModificationList = statModificationList;
        this.duration = duration;

    }

    @Override
    public void execute(Entity entity) {
        statModificationList.applyModifications(entity.getStats());
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                remove(entity);
            }
        },duration);
    }

    @Override
    public void remove(Entity entity) {
        statModificationList.removeModifications(entity.getStats());
        timer.cancel();
        timer.purge();
    }
}
