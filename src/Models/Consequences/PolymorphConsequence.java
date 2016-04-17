package Models.Consequences;

import Models.Entities.Entity;
import Models.Entities.Skills.ActiveSkills.SummonerSkills.Enchantments.Polymorph;
import Models.Map.Direction;
import javafx.geometry.Point3D;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Aidan on 4/15/2016.
 */
public class PolymorphConsequence extends Consequence {

    private int activeTime;
    private Timer timer = new Timer();
    private HashMap<Direction,BufferedImage> images;

    public PolymorphConsequence(int activeTime){
            this.activeTime = activeTime;
        }

        //TODO: Figure out how to implement behavior changes
        //TODO: Reset behavior changes in the timer
        public void execute(Entity entity) {
            //Execute behavior change
            //polymorph spell will hold the original images
            images = entity.polymorph();


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
            entity.changeBack(images);
        }

        public Consequence makeTeleport(Point3D endingPoint) {
            return null;
        }

        public Consequence makeRiver(int waterVelocity) {
            return null;
        }

    }

