package Models.Consequences;

import Models.Entities.Entity;
import javafx.geometry.Point3D;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Aidan on 4/15/2016.
 */
public class SleepConsequence extends Consequence{

    private int activeTime;
    private Timer timer = new Timer();

    public SleepConsequence(int activeTime){
        this.activeTime = activeTime;
    }


    //TODO: Figure out how to implement behavior changes
    //TODO: Reset behavior changes in the timer
    public void execute(Entity entity) {
        //Execute behavior change
        entity.makeSleep(this);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //Reset behavior change
                //prolly should be:
                //remove(entity)
            }
        }, activeTime);
    }

    //TODO: figure out what this needs to do
    public void remove(Entity entity){

    }

    public Consequence makeTeleport(Point3D endingPoint) {
        return null;
    }

    public Consequence makeRiver(int waterVelocity) {
        return null;
    }

}
