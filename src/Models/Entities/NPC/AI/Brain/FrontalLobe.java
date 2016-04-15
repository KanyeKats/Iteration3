package Models.Entities.NPC.AI.Brain;

import Models.Entities.Entity;
import Models.Entities.NPC.AI.Decision.*;
import Models.Entities.NPC.AI.Memory;
import Models.Entities.NPC.AI.Personality;
import Models.Entities.NPC.AI.VisualInfo;
import Models.Entities.NPC.NPC;
import Models.Entities.Skills.InfluenceEffect.Effect;
import javafx.geometry.Point3D;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Aidan on 4/7/2016.
 */
public class FrontalLobe {

    private Personality personality;
    private Decision currentDecision;

    public FrontalLobe(Personality personality) {

        this.personality = personality;
        this.currentDecision = null;
    }

    public void process(VisualInfo visualInfo, NPC npc){

        // If there is a current decision and we can continue pursing it, do it!
        if(currentDecision!=null && currentDecision.continuePursuing(visualInfo)){
            currentDecision.executeDecision(npc);
            return;
        }

        // Unpack the visual info.
        ArrayList<Point3D> itemLocations = visualInfo.getItemLocations();
        ArrayList<Entity> entitiesFound = visualInfo.getEntities();
        ArrayList<Point3D> areaEffectLocations = visualInfo.getAreaEffectLocations();

        // Arbitrarily, collecting items will take priority.
        if(!itemLocations.isEmpty() && personality.willCollectItem()){
            currentDecision = new GoToItemDecision(itemLocations.get(0));
            currentDecision.executeDecision(npc);
        }else if(!areaEffectLocations.isEmpty() && personality.willActivateAreaEffect()){
            currentDecision = new GoToAreaEffectDecision(itemLocations.get(0));
            currentDecision.executeDecision(npc);
        }else if(!entitiesFound.isEmpty()){
            if(personality.willFollow()){
                currentDecision = new FollowDecision(entitiesFound.get(0));
                currentDecision.executeDecision(npc);
            }else if(personality.willAttack()){
                currentDecision = new AttackDecision(entitiesFound.get(0));
                currentDecision.executeDecision(npc);
            }
        }
    }

    public boolean willTrade(){
        return personality.willTrade();
    }
}
