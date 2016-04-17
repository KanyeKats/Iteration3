package Models.Entities.NPC;

import Models.Consequences.BehaviorConsequence;
import Models.Consequences.SleepConsequence;
import Models.Entities.Entity;
import Models.Entities.Equipment;
import Models.Entities.Inventory;
import Models.Entities.NPC.AI.Brain.Brain;
import Models.Entities.NPC.AI.Personality;
import Models.Entities.Occupation.Occupation;
import Models.Entities.Stats.Stats;
import Models.Map.Direction;
import Models.Map.Map;
import Models.Map.Terrain;
import Utilities.Savable.Savable;
import Views.Graphics.Assets;
import javafx.geometry.Point3D;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Created by Aidan on 4/6/2016.
 */
public class NPC extends Entity implements Savable{

    //needs a brain and such
    private Brain brain;

    public NPC(Occupation occupation, Point3D location, Map map, Terrain[]passableTerrain, Personality personality, Boolean isFlyer) {
        super(occupation, location, map, isFlyer, passableTerrain);
        this.brain = new Brain(personality, this);
    }

    @Override
    public void update() {
        super.update();
        brain.think();
    }

    public String getDialog() { return brain.getDialog(); }

    @Override
    public void interacted(Entity entity){

    }

    @Override
    public void makeSleep() {
        brain.setIsSleeping(true);
    }

    @Override
    public void wakeUp(){
        brain.setIsSleeping(false);
    }

    @Override
    public void fear(Direction direction){
        brain.getFrontalLobe().setisFeared(true,this.getDirection(),this);
        brain.getVisualInfo().setStillFeared(true);
    }

    @Override
    public void dontFear(){
        brain.getFrontalLobe().setisFeared(false,null,this);
        brain.getVisualInfo().setStillFeared(false);
    }

    @Override
    public HashMap<Direction,BufferedImage>  polymorph() {
        //need to save the previous images
        //i let the consequence hold the images and give them back
        //to the entity when the polymorph is over
        HashMap<Direction,BufferedImage> tempImages = new HashMap<>();
        HashMap<Direction,BufferedImage> images = this.getImages();
        for(Direction direction : images.keySet()){
            BufferedImage image = images.get(direction);
            tempImages.put(direction,image);
        }
        brain.getFrontalLobe().actLikeFrog(true);
        //TODO: Make frog images not bug
        images.put(Direction.NORTH, Assets.BUG_NORTH);
        images.put(Direction.NORTH_EAST, Assets.BUG_NORTH_EAST);
        images.put(Direction.SOUTH_EAST, Assets.BUG_SOUTH_EAST);
        images.put(Direction.SOUTH, Assets.BUG_SOUTH);
        images.put(Direction.SOUTH_WEST, Assets.BUG_SOUTH_WEST);
        images.put(Direction.NORTH_WEST, Assets.BUG_NORTH_WEST);
        return tempImages;
    }

    @Override
    public void changeBack(HashMap<Direction,BufferedImage> images) {
        brain.getFrontalLobe().actLikeFrog(false);
        setImages(images);
    }

    public boolean willTrade() { return brain.willTrade(); }

    public Document save(Document doc, Element parentElement) {

        Element entity = doc.createElement("entity");
        String locString = Integer.toString((int)getLocation().getX()) + ",";
        locString += Integer.toString((int)getLocation().getY()) + ",";
        locString += Integer.toString((int)getLocation().getZ());
        entity.setAttribute("location", locString);
        entity.setAttribute("direction", String.valueOf(getDirection()));
        entity.setAttribute("is-mounted", String.valueOf(isMounted()));
        parentElement.appendChild(entity);

        //save inventory
        if(this.getInventory() != null) {
            Element inventory = doc.createElement("inventory");
            inventory.setAttribute("capacity", Integer.toString(this.getInventory().getCapacity()));
            entity.appendChild(inventory);

            this.getInventory().save(doc, inventory);
        }

        //save equipment
        if(this.getEquipment() != null) {
            Element equipment = doc.createElement("equipment");
            entity.appendChild(equipment);

            this.getEquipment().save(doc, equipment);
        }

        //save occupation
        if(this.getOccupation() != null) {
            Element occupation = doc.createElement("occupation");
            occupation.setAttribute("value", this.getOccupation().toString());
            entity.appendChild(occupation);
        }

        //save stats
        if(this.getStats() != null){
            Element stats = doc.createElement("stats");
            entity.appendChild(stats);
            this.getStats().save(doc, stats);
        }

        //save active skills
        if(this.getActiveSkillList() != null){
            Element activeSkillList = doc.createElement("active-skill-list");
            entity.appendChild(activeSkillList);
            this.getActiveSkillList().save(doc,activeSkillList);
        }

        //save passive skills
        if(this.getPassiveSkillList() != null){
            Element passiveSkillList = doc.createElement("passive-skill-list");
            entity.appendChild(passiveSkillList);
            this.getPassiveSkillList().save(doc,passiveSkillList);
        }

        //save mount
        if(this.getMount() != null){
            Element mount = doc.createElement("mount");
            mount.setAttribute("prev-speed", Integer.toString(this.getMount().getEntityPrevspeed()));
            entity.appendChild(mount);
            this.getMount().save(doc, mount);
        }

        //save passable terrains
        if(this.getPassableTerrains() != null){
            Element passable = doc.createElement("passable-terrains");
            entity.appendChild(passable);
            for(Terrain t : getPassableTerrains()){
                Element terrain = doc.createElement("terrain");
                terrain.setAttribute("type", t.name());
                passable.appendChild(terrain);
            }
        }

        //save brain
        if(this.brain != null){
            Element brain = doc.createElement("brain");
            brain.setAttribute("personality", String.valueOf(this.brain.getPersonality()));
            entity.appendChild(brain);
        }


        return doc;
    }
}
