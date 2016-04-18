package Models.Entities.Skills.ActiveSkills.SneakSkills;

import Models.Entities.Entity;
import Models.Entities.Skills.ActiveSkills.ActiveSkill;

import Models.Items.Item;
import Models.Items.Takable.TakableItem;
import Models.Map.Direction;
import Models.Map.Tile;
import javafx.geometry.Point3D;

import java.util.Random;

/**
 * Created by josh on 4/6/16.
 */
public class PickPocket extends ActiveSkill {

    public final int BASE_COOLDOWN_TIME = 20000;    //20 seconds
    private final int BASE_MANA_REQUIRED = 5;
    private final int MANA_LEVEL_MULTIPLIER = 1;

    public PickPocket(){
        cooldownTime = BASE_COOLDOWN_TIME;
        manaRequired = BASE_MANA_REQUIRED;
        manaLevelMultiplier = MANA_LEVEL_MULTIPLIER;
    }


    @Override
    protected void performSkill(Entity entity) {
        if(tileHasNPC(entity)){
            stealItem(entity);
        }
    }

    private boolean tileHasNPC(Entity entity){
        Entity npc = entity.getTileInFront().getEntity();
        if(npc != null)
            return true;
        else
            return false;
    }

    private void stealItem(Entity entity){
        Entity npc = entity.getTileInFront().getEntity();
        TakableItem item = npc.getInventory().removeRandomItem();
        if(item != null) {
            entity.addItemToInventory(item);
        }
        else{
            System.out.println("empty inventory!");
        }
    }

    @Override
    public String toString(){
        return "Pick-Pocket";
    }
}
