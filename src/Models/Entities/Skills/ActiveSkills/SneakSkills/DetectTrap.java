package Models.Entities.Skills.ActiveSkills.SneakSkills;

import Models.Entities.Entity;
import Models.Entities.Skills.ActiveSkills.ActiveSkill;
import Models.Entities.Skills.InfluenceEffect.RadialEffect;
import Models.Map.Map;
import Models.Map.Tile;
import Utilities.MapUtilities.MapNavigationUtilities;

import java.util.ArrayList;

/**
 * Created by josh on 4/6/16.
 */
public class DetectTrap extends ActiveSkill {

    public final int BASE_COOLDOWN_TIME = 500;    //5 seconds
    private final int BASE_MANA_REQUIRED = 5;
    private final int MANA_LEVEL_MULTIPLIER = 1;

    public DetectTrap(){
        cooldownTime = BASE_COOLDOWN_TIME;
        manaRequired = BASE_MANA_REQUIRED;
        manaLevelMultiplier = MANA_LEVEL_MULTIPLIER;
    }

    @Override
    protected boolean percentChanceByLevel(){
        return true;
    }

    @Override
    protected void performSkill(Entity entity) {
        ArrayList<ArrayList<Tile>> potentialTrappedTiles = MapNavigationUtilities.getRadialTiles(entity.getLocation(),2,entity.getMap());

        for (ArrayList<Tile> al : potentialTrappedTiles) {
            for (Tile tile : al) {
                //if there is a trap
                if (tile.getAreaEffect() != null && !tile.getAreaEffect().getVisibility()) {
                    System.out.println(tile.getAreaEffect().toString());
                    if (isCooledDown) {
                        if (percentChanceByLevel()) {
                            //Implement making a trap visible
                            tile.getAreaEffect().setVisibility(true);
                        }
                    }
                }
            }
        }
    }

    @Override
    public String toString(){
        return "Detect Trap";
    }
}
