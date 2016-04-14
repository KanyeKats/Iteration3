package Models.Entities.Skills.PassiveSkills;

import Utilities.Savable.Savable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
        return null;
    }

    @Override
    public void load(Element data) {

    }
}
