package Models.Consequences;

import Models.Entities.Entity;
import javafx.geometry.Point3D;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by josh on 4/6/16.
 */
public class BehaviorConsequence extends Consequence {

    private int activeTime;
    private Timer timer = new Timer();

    public BehaviorConsequence(int activeTime){
        this.activeTime = activeTime;
    }


    //TODO: Figure out how to implement behavior changes
    //TODO: Reset behavior changes in the timer
    public void execute(Entity entity) {
        //Execute behavior change

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
