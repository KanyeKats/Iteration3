package Models.Entities.Occupation;

import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;

/**
 * Created by Aidan on 4/6/2016.
 */
public class Smasher extends Occupation {

    //constructor of smasher will contain the stats that make up a smasher




    public Smasher(){

        this.statModificationList = new StatModificationList(
                new StatModification(Stat.STRENGTH,10),
                new StatModification(Stat.AGILITY,6),
                new StatModification(Stat.INTELLECT,0),
                new StatModification(Stat.HARDINESS,10),
                new StatModification(Stat.MOVEMENT,4),
                new StatModification(Stat.MAX_HEALTH,50),
                new StatModification(Stat.MAX_MANA,0),
                new StatModification(Stat.EXPERIENCE, 0));


    }

}
