package Models.Entities.Occupation;

import Models.Entities.Skills.ActiveSkills.ActiveSkill;
import Models.Entities.Skills.ActiveSkills.ActiveSkillList;
import Models.Entities.Skills.ActiveSkills.CommonSkills.BasicAttack;
import Models.Entities.Skills.ActiveSkills.CommonSkills.BindWounds;

/**
 * Created by Aidan on 4/6/2016.
 */
public abstract class Occupation {

    private BasicAttack basicAttack;
    private BindWounds bindWounds;
    private ActiveSkillList activeSkillList;
    private int availableSkillPoints;

    Occupation(){
        this.availableSkillPoints = 10;

    }


}
