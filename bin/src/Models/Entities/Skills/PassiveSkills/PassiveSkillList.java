package Models.Entities.Skills.PassiveSkills;

import Utilities.Savable.Savable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
 * Created by josh on 4/6/16.
 */
public class PassiveSkillList implements Savable {

    private ArrayList<PassiveSkill> passiveSkillList = new ArrayList<>();

    public void add(PassiveSkill passiveSkill){
        passiveSkillList.add(passiveSkill);
    }

    public boolean remove(PassiveSkill passiveSkill){
        return passiveSkillList.remove(passiveSkill);
    }

    public PassiveSkill get(int index){
        return passiveSkillList.get(index);
    }

    public boolean contains(PassiveSkill passiveSkill){
        return passiveSkillList.contains(passiveSkill);
    }

    public int indexOf(PassiveSkill passiveSkill){
        return passiveSkillList.indexOf(passiveSkill);
    }

    public boolean isEmpty(){
        return passiveSkillList.isEmpty();
    }

    public void clear(){
        passiveSkillList.clear();
    }

    public int size(){
        return passiveSkillList.size();
    }

    @Override
    public Document save(Document doc, Element parentElement) {

        for(PassiveSkill skill : passiveSkillList){
            Element activeSkill = doc.createElement("passive-skill");
            activeSkill.setAttribute("type", skill.toString());
            activeSkill.setAttribute("value", Integer.toString(skill.getLevel()));
            parentElement.appendChild(activeSkill);
        }

        return doc;
    }

    @Override
    public void load(Element data) {
        // Get the item child nodes of the tile
        NodeList passiveSkillNodes = data.getElementsByTagName("passive-skill");

        if (passiveSkillNodes.getLength() != 0) {
            // Get all item elements
            for (int i = 0; i < passiveSkillNodes.getLength(); i++) {
                // Get the node/element
                Node node = passiveSkillNodes.item(i);
                Element skillElement = (Element) node;

                String skillName = skillElement.getAttribute("type");
                String skillLevelString = skillElement.getAttribute("value");
                int skillLevel = Integer.valueOf(skillLevelString);

                passiveSkillList.get(i).setLevel(skillLevel);
            }
        }
    }
}
