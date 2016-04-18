package Models.Entities.NPC.AI.Decision;

import Models.Entities.Entity;
import Models.Entities.NPC.AI.VisualInfo;
import Models.Entities.NPC.NPC;
import Models.Map.Direction;
import Models.Map.MapUtilities.MapUtilities;

/**
 * Created by Bradley on 4/14/2016.
 */
public class AttackDecision implements Decision {

    private Entity enemy;

    public AttackDecision(Entity enemy) {
        this.enemy = enemy;
    }

    @Override
    public void executeDecision(NPC npc) {
        if(MapUtilities.distanceBetweenPoints(npc.getLocation(), enemy.getLocation()) == 1){
            // If you are right next to the enemy, attack them.
            npc.getActiveSkillList().get(0).activate(npc);
            System.out.println("ATTACKING NOW");
        }else{
            // Otherwise walk towards them.
            Direction movementDirection = MapUtilities.closestDirectionBetweenAandB(npc.getLocation(), enemy.getLocation());
            npc.move(movementDirection);
        }
    }

    @Override
    public boolean continuePursuing(VisualInfo visualInfo) {
        return true;
    }
}
