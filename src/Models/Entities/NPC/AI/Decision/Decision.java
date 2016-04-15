package Models.Entities.NPC.AI.Decision;

import Models.Entities.NPC.AI.VisualInfo;
import Models.Entities.NPC.NPC;

/**
 * Created by Bradley on 4/14/2016.
 */
public interface Decision {

//    ATTACK(){
//        @Override
//        public void executeDecision(NPC npc){
//
//        }
//    },
//    FOLLOW(){
//        @Override
//        public void executeDecision(NPC npc){
//
//        }
//    },
//    GO_TO_ITEM(){
//        @Override
//        public void executeDecision(NPC npc){
//
//        }
//    },
//    GO_TO_AREA_EFFECT(){
//        @Override
//        public void executeDecision(NPC npc){
//
//        }
//    };

    void executeDecision(NPC npc);
    boolean continuePursuing(VisualInfo visualInfo);
}
