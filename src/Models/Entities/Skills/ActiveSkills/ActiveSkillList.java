package Models.Entities.Skills.ActiveSkills;

import Utilities.Savable.Savable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
 * Created by josh on 4/6/16.
 */
public class ActiveSkillList implements Savable {
    private ArrayList<ActiveSkill> activeSkillList = new ArrayList<>();

    public void add(ActiveSkill activeSkill){
        activeSkillList.add(activeSkill);
    }

    public boolean remove(ActiveSkill activeSkill){
        return activeSkillList.remove(activeSkill);
    }

    public ActiveSkill get(int index){
        return activeSkillList.get(index);
    }

    public boolean contains(ActiveSkill activeSkill){
        return activeSkillList.contains(activeSkill);
    }

    public int indexOf(ActiveSkill activeSkill){
        return activeSkillList.indexOf(activeSkill);
    }

    public boolean isEmpty(){
        return activeSkillList.isEmpty();
    }

    public void clear(){
        activeSkillList.clear();
    }

    public int size(){
        return activeSkillList.size();
    }

    @Override
    public Document save(Document doc, Element parentElement) {
        return null;
    }

    @Override
    public void load(Element data) {
        try {
            // Get the tilesNodes from the xml file
            NodeList skillNodes = data.getElementsByTagName("ActiveSkill");

            //find out how many tiles there are
            int skillNodesLength = skillNodes.getLength();

            //instantiate every tile to the map
            for(int i=0; i<skillNodesLength; i++) {

                Element skillElement = (Element) skillNodes.item(i);

                int x = Integer.parseInt(skillElement.getAttribute("x"));
                int y = Integer.parseInt(skillElement.getAttribute("y"));
                int z = Integer.parseInt(skillElement.getAttribute("z"));

                //construct an empty tile and load it into the game
                ActiveSkill activeSkill = new ActiveSkill() {
                };
                activeSkill.load(skillElement);


                // Check to see if this column has already been started
                this.activeSkillList.add(activeSkill);
            }
        } catch (Exception e) {
            System.out.println("Error parsing map again");
            e.printStackTrace();
        }
    }
}
