package Models.Entities.Skills.ActiveSkills.SneakSkills;

import Models.Entities.Entity;
import Models.Entities.Skills.ActiveSkills.ActiveSkill;
import javafx.geometry.Point3D;

import java.util.Random;

/**
 * Created by josh on 4/6/16.
 */
public class PickPocket extends ActiveSkill {

    public final int BASE_COOLDOWN_TIME = 20000;    //20 seconds
    private Random rand = new Random();

    public PickPocket(){
        cooldownTime = BASE_COOLDOWN_TIME;
    }

    //TODO: Remove the randomly chosen item. How to give it to entity who called pickpocket?
    public void activate(Entity entity){
        Point3D pointCheck = entity.getOrientation().getPointAdjacentTo(entity.getLocation());
        if(isCooledDown){
            if(percentChanceByLevel()){
                int invSize = entity.getInventory().size();
                int randomNum = rand.nextInt(invSize+1);
                //Remove that particular item
            }
        }
    }
}
