package Models.Entities.NPC.AI.Brain;

import Models.Entities.Entity;
import Models.Entities.NPC.AI.Decision.*;
import Models.Entities.NPC.AI.Personality;
import Models.Entities.NPC.AI.VisualInfo;
import Models.Entities.NPC.NPC;
import javafx.geometry.Point3D;

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
            System.out.println("GET ITEM!");
            currentDecision = new GoToItemDecision(itemLocations.get(0));
            currentDecision.executeDecision(npc);
        }else if(!areaEffectLocations.isEmpty() && personality.willActivateAreaEffect()){
            System.out.println("GET AOF!");
            currentDecision = new GoToAreaEffectDecision(areaEffectLocations.get(0));
            currentDecision.executeDecision(npc);
        }else if(!entitiesFound.isEmpty()){
            if(personality.willFollow()){
                System.out.println("FOLLOW!");
                currentDecision = new FollowDecision(entitiesFound.get(0));
                currentDecision.executeDecision(npc);
            }else if(personality.willAttack()){
                System.out.println("ATTACK!");
                currentDecision = new AttackDecision(entitiesFound.get(0));
                currentDecision.executeDecision(npc);
            }
        }
    }

    public boolean willTrade(){
        return personality.willTrade();
    }
    public String getDialog() { return personality.getDialog(); }
}