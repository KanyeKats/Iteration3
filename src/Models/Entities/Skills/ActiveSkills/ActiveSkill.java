package Models.Entities.Skills.ActiveSkills;

import Models.Entities.Entity;
import Models.Consequences.Consequence;
import Models.Entities.Skills.Skill;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by josh on 4/6/16.
 */
public abstract class ActiveSkill extends Skill {
    protected Consequence consequence;
    protected int cooldownTime;
    protected boolean isCooledDown = true;
    protected Timer cooldownTimer = new Timer();
    protected Timer effectTimer =  new Timer();

    public void activate(Entity entity){
        if(isCooledDown){
            if(percentChanceByLevel())
                consequence.execute(entity);
        }
        else
            System.out.println("Not cooled down!");
    }

    final protected void doTheCoolDown(){
        cooldownTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                isCooledDown = true;
            }
        }, cooldownTime);
    }

    //Calculates a random number, checks if it's more than 0.9^level
    //This value doesn't go below 0.5 until level=7
    protected boolean percentChanceByLevel(){
        if(Math.random() > Math.pow(0.9, level))
            return true;
        else
            return false;
    }
}
