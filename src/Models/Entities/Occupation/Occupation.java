package Models.Entities.Occupation;

import Models.Entities.Skills.ActiveSkills.ActiveSkillList;
import Models.Entities.Skills.ActiveSkills.CommonSkills.BasicAttack;
import Models.Entities.Skills.ActiveSkills.CommonSkills.BindWounds;
import Models.Entities.Skills.PassiveSkills.CommonSkills.Bargain;
import Models.Entities.Skills.PassiveSkills.CommonSkills.Observation;
import Models.Entities.Skills.PassiveSkills.PassiveSkillList;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Models.Entities.Stats.Stats;
import Utilities.Savable.Savable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by Aidan on 4/6/2016.
 */
public abstract class Occupation implements Savable {
    protected ActiveSkillList activeSkillList;
    protected PassiveSkillList passiveSkillList;
    protected StatModificationList statModificationList;

    Occupation(){

        this.activeSkillList = new ActiveSkillList();
        this.passiveSkillList = new PassiveSkillList();
        this.statModificationList = new StatModificationList();

    }

    public void initStats(Stats stats){

        statModificationList.applyModifications(stats);
        stats.setStat(Stat.HEALTH, stats.getMaxHealth());
        stats.setStat(Stat.MANA,stats.getMaxMana());

    }

    public ActiveSkillList initActiveSkills(Stats stats){
        activeSkillList.add(new BindWounds());
        activeSkillList.add(new BasicAttack());

        return activeSkillList;
    }

    public abstract PassiveSkillList initPassiveSkills(Stats stats);

    @Override
    public Document save(Document doc, Element parentElement) {
        return null;
    }

    @Override
    public void load(Element data) {
        this.activeSkillList.load((Element) data.getElementsByTagName("activeSkillList"));
        this.passiveSkillList.load((Element) data.getElementsByTagName("passiveSkillList"));
        this.statModificationList.load((Element) data.getElementsByTagName("statModificationList"));
    }
}
