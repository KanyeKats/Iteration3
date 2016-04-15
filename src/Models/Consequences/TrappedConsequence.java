package Models.Consequences;

import Models.Entities.Entity;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Magic_Buddha on 4/15/2016.
 */
public class TrappedConsequence extends BehaviorConsequence {

    //shhould prolly change super class to have this protected..
    private Timer timer = new Timer();
    private int activeTime;
    private int originalSpeed;


    public TrappedConsequence(int activeTime) {
        super(activeTime);
        //not necessary if activeTime is set to protected in super class
        this.activeTime = activeTime;
    }

    //if above done, no need to override this function
    @Override
    public void execute(Entity entity) {

        add(entity);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                //Reset behavior change
                remove(entity);
            }
        }, activeTime);
    }

    //most likely could be private if changes done
    @Override
    public void remove(Entity entity) {
        StatModification buff = new StatModification(Stat.MOVEMENT, originalSpeed);
        StatModificationList modList = new StatModificationList(buff);
        modList.applyModifications(entity.getStats());
    }

    private void add(Entity entity) {
        originalSpeed = entity.getStats().getStat(Stat.MOVEMENT);
        StatModification deBuff = new StatModification(Stat.MOVEMENT, -originalSpeed);
        StatModificationList modList = new StatModificationList(deBuff);
        modList.applyModifications(entity.getStats());
    }
}
