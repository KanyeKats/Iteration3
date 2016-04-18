package Models.Entities.Skills.ActiveSkills;

import Utilities.Savable.Savable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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

        for(ActiveSkill skill : activeSkillList){
            Element activeSkill = doc.createElement("active-skill");
            activeSkill.setAttribute("type", skill.toString());
            activeSkill.setAttribute("value", Integer.toString(skill.getLevel()));
            parentElement.appendChild(activeSkill);
        }

        return doc;
    }

    @Override
    public void load(Element data) {
        // Get the item child nodes of the tile
        NodeList activeSkillNodes = data.getElementsByTagName("active-skill");

        if (activeSkillNodes.getLength() != 0) {
            // Get all item elements
            for (int i = 0; i < activeSkillNodes.getLength(); i++) {
                // Get the node/element
                Node node = activeSkillNodes.item(i);
                Element skillElement = (Element) node;

                String skillName = skillElement.getAttribute("type");
                String skillLevelString = skillElement.getAttribute("value");
                int skillLevel = Integer.valueOf(skillLevelString);

                activeSkillList.get(i).setLevel(skillLevel);
            }
        }
    }
}
