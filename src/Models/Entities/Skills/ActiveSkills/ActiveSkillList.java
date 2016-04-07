package Models.Entities.Skills.ActiveSkills;

import java.util.ArrayList;

/**
 * Created by josh on 4/6/16.
 */
public class ActiveSkillList {
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
}
