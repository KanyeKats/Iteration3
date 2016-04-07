package Models.Entities.Occupation;

import Models.Entities.Skills.ActiveSkills.ActiveSkill;
import Models.Entities.Skills.ActiveSkills.ActiveSkillList;
import Models.Entities.Skills.ActiveSkills.CommonSkills.BasicAttack;
import Models.Entities.Skills.ActiveSkills.CommonSkills.BindWounds;
import Models.Entities.Stats.StatModificationList;

/**
 * Created by Aidan on 4/6/2016.
 */
public abstract class Occupation {

    private BasicAttack basicAttack;
    private BindWounds bindWounds;
    private ActiveSkillList activeSkillList;
    StatModificationList statModificationList;

    Occupation(){

        activeSkillList.getActiveSkillList().add(basicAttack);

    }


}
