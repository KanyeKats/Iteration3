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

    public final int BASE_COOLDOWN_TIME = 20000;    //20 seconds

    public DetectTrap(RadialEffect radialEffect){
        cooldownTime = BASE_COOLDOWN_TIME;
    }

    //TODO: Implement checking the map and making trap visible if there is one
    public void activate(Entity entity){


        ArrayList<ArrayList<Tile>> potentialTrappedTiles = MapNavigationUtilities.getRadialTiles(entity.getLocation(),2,entity.getMap());

        for (ArrayList<Tile> al : potentialTrappedTiles) {
            for (Tile tile : al) {
                //if there is a trap
                if (!tile.getAreaEffect().getVisibility()) {
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
}
