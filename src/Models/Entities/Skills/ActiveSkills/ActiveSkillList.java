package Models.Entities.Skills.ActiveSkills;

import Models.Entities.Skills.PassiveSkills.PassiveSkill;

import java.util.ArrayList;

/**
 * Created by josh on 4/6/16.
 */
public class ActiveSkillList {
    private ArrayList<ActiveSkill> activeSkillList = new ArrayList<>();

    public ArrayList<ActiveSkill> getActiveSkillList() {
        return activeSkillList;
    }

    public void setActiveSkillList(ArrayList<ActiveSkill> activeSkillList) {
        this.activeSkillList = activeSkillList;
    }
}
