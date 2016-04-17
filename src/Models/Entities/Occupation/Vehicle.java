package Models.Entities.Occupation;

import Models.Entities.Skills.ActiveSkills.ActiveSkillList;
import Models.Entities.Skills.ActiveSkills.CommonSkills.BasicAttack;
import Models.Entities.Skills.ActiveSkills.CommonSkills.BindWounds;
import Models.Entities.Skills.ActiveSkills.SummonerSkills.Banes.Fireball;
import Models.Entities.Skills.ActiveSkills.SummonerSkills.Boons.LudaSpeed;
import Models.Entities.Skills.ActiveSkills.SummonerSkills.Enchantments.Sleep;
import Models.Entities.Skills.PassiveSkills.CommonSkills.Bargain;
import Models.Entities.Skills.PassiveSkills.CommonSkills.Observation;
import Models.Entities.Skills.PassiveSkills.PassiveSkillList;
import Models.Entities.Skills.PassiveSkills.SummonerSkills.StaffMastery;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Models.Entities.Stats.Stats;
import Models.Map.Direction;
import Views.Graphics.Assets;

import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Created by Aidan on 4/16/2016.
 */
public class Vehicle extends Occupation {

    public Vehicle() {
        this.statModificationList = new StatModificationList(new StatModification(Stat.MOVEMENT, 34));
    }

    @Override
    public PassiveSkillList initPassiveSkills(Stats stats){
        return null;
    }

    @Override
    public ActiveSkillList initActiveSkills(Stats stats){
        return null;
    }

    @Override
    public HashMap<Direction, BufferedImage> initImages(){

        HashMap<Direction, BufferedImage> images = new HashMap<>();

        images.put(Direction.NORTH, Assets.MOUNT_NORTH);
        images.put(Direction.NORTH_EAST, Assets.MOUNT_NORTH_EAST);
        images.put(Direction.SOUTH_EAST, Assets.MOUNT_SOUTH_EAST);
        images.put(Direction.SOUTH, Assets.MOUNT_SOUTH);
        images.put(Direction.SOUTH_WEST, Assets.MOUNT_SOUTH_WEST);
        images.put(Direction.NORTH_WEST, Assets.MOUNT_NORTH_WEST);

        return images;
    }
}
