package Models.Entities.Occupation;

import Models.Entities.Entity;
import Models.Entities.Skills.ActiveSkills.ActiveSkillList;
import Models.Entities.Skills.ActiveSkills.CommonSkills.BasicAttack;
import Models.Entities.Skills.ActiveSkills.CommonSkills.BindWounds;
import Models.Entities.Skills.PassiveSkills.CommonSkills.Bargain;
import Models.Entities.Skills.PassiveSkills.CommonSkills.Observation;
import Models.Entities.Skills.PassiveSkills.PassiveSkillList;
import Models.Entities.Stats.StatModificationList;
import Models.Entities.Stats.Stats;

/**
 * Created by Aidan on 4/6/2016.
 */
public abstract class Occupation {

    private BasicAttack basicAttack;
    private BindWounds bindWounds;
    private Bargain bargain;
    private Observation observation;
    protected ActiveSkillList activeSkillList;
    protected PassiveSkillList passiveSkillList;
    protected StatModificationList statModificationList;

    Occupation(){

        this.activeSkillList.add(basicAttack);
        this.activeSkillList.add(bindWounds);

        this.passiveSkillList.add(bargain);
        this.passiveSkillList.add(observation);

    }

    public void initStats(Stats stats){
        statModificationList.applyModifications(stats);
    }

    public void initSkills(ActiveSkillList activeSkillList, PassiveSkillList passiveSkillList){

        activeSkillList = this.activeSkillList;
        passiveSkillList = this.passiveSkillList;

    }


}
