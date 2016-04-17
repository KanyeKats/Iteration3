package Models.Consequences;

import Models.Entities.Entity;
import Models.Map.Direction;
import javafx.geometry.Point3D;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Aidan on 4/15/2016.
 */
public class FearConsequence extends Consequence {

        private int activeTime;
        private Timer timer = new Timer();
        private Direction direction;

        public FearConsequence(int activeTime){
            this.activeTime = activeTime;
        }

        public FearConsequence(int activeTime, Direction direction){
            this.activeTime = activeTime;
            this.direction = direction;
        }


        //TODO: Figure out how to implement behavior changes
        //TODO: Reset behavior changes in the timer
        public void execute(Entity entity) {
            //Execute behavior change
            entity.fear(direction);


            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    //Reset behavior change
                    //prolly should be:
                    remove(entity);
                }
            }, activeTime);
        }

        //TODO: figure out what this needs to do
        public void remove(Entity entity){
            entity.dontFear();
        }

        public Consequence makeTeleport(Point3D endingPoint) {
            return null;
        }

        public Consequence makeRiver(int waterVelocity) {
            return null;
        }

        public void setDirection(Direction direction){
            this.direction = direction;
        }
    }

