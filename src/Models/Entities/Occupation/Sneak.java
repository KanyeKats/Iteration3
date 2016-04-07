package Models.Entities.Occupation;

import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;

/**
 * Created by Aidan on 4/6/2016.
 */
public class Sneak extends Occupation {

    public Sneak(){

        this.statModificationList = new StatModificationList(
                new StatModification(Stat.STRENGTH,6),
                new StatModification(Stat.AGILITY,10),
                new StatModification(Stat.INTELLECT,5),
                new StatModification(Stat.HARDINESS,6),
                new StatModification(Stat.MOVEMENT,9),
                new StatModification(Stat.MAX_HEALTH,50),
                new StatModification(Stat.MAX_MANA,50),
                new StatModification(Stat.EXPERIENCE, 0));


    }


}
