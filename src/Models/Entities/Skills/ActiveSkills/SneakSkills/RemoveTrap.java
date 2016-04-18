package Models.Entities.Skills.ActiveSkills.SneakSkills;

import Models.Entities.Entity;
import Models.Entities.Skills.ActiveSkills.ActiveSkill;
import Models.Map.AreaEffect;
import Models.Map.Map;
import Models.Map.Tile;

/**
 * Created by josh on 4/6/16.
 */
public class RemoveTrap extends ActiveSkill {

    public final int BASE_COOLDOWN_TIME = 20000;    //20 seconds
    private final int BASE_MANA_REQUIRED = 5;
    private final int MANA_LEVEL_MULTIPLIER = 1;

    public RemoveTrap(){
        cooldownTime = BASE_COOLDOWN_TIME;
        manaRequired = BASE_MANA_REQUIRED;
        manaLevelMultiplier = MANA_LEVEL_MULTIPLIER;
    }

//    public void activate(Entity entity){
//
//        Tile potentialTrapTile = entity.getTileInFront();
//        AreaEffect potentialTrap = potentialTrapTile.getAreaEffect();
//
//        //if there is a trap
//        if(potentialTrap != null && potentialTrap.isRemovable()){
//            if(isCooledDown) {
//                if (percentChanceByLevel()) {
//                    //Implement removing a trap
//                    potentialTrapTile.removeAreaEffect();
//                }
//            }
//        }
//    }

    @Override
    protected void performSkill(Entity entity) {
        Tile potentialTrapTile = entity.getTileInFront();
        AreaEffect potentialTrap = potentialTrapTile.getAreaEffect();

        //if there is a trap
        if(potentialTrap != null && potentialTrap.isRemovable()){
            //Implement removing a trap
            potentialTrapTile.removeAreaEffect();
        }
    }

    @Override
    public String toString(){
        return "Remove Trap";
    }
}
