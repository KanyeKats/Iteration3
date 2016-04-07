package Models.Entities.Skills.PassiveSkills.PassiveSkillsVisitors;

import Models.Entities.Skills.PassiveSkills.CommonSkills.Bargain;
import Models.Entities.Skills.PassiveSkills.CommonSkills.Observation;
import Models.Entities.Skills.PassiveSkills.SmasherSkills.BrawlingMastery;
import Models.Entities.Skills.PassiveSkills.SmasherSkills.OneHandedWeaponMastery;
import Models.Entities.Skills.PassiveSkills.SmasherSkills.TwoHandedWeaponMastery;
import Models.Entities.Skills.PassiveSkills.SneakSkills.RangedWeaponMastery;
import Models.Entities.Skills.PassiveSkills.SummonerSkills.StaffMastery;

/**
 * Created by josh on 4/6/16.
 */
public class RangedWeaponVisitor implements PassiveSkillVisitor {
    public void activate(Bargain bargain){}

    public void activate(Observation observation){}

    public void activate(OneHandedWeaponMastery oneHandedWeaponMastery){
        oneHandedWeaponMastery.removeFromStats();
    }

    public void activate(TwoHandedWeaponMastery twoHandedWeaponMastery){
        twoHandedWeaponMastery.removeFromStats();
    }

    public void activate(BrawlingMastery brawlingMastery){
        brawlingMastery.removeFromStats();
    }

    public void activate(RangedWeaponMastery rangedWeaponMastery){
        rangedWeaponMastery.addToStats();
    }

    public void activate(StaffMastery staffMastery){
        staffMastery.removeFromStats();
    }
}
