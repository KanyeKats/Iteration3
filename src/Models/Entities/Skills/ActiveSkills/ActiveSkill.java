package Models.Entities.Skills.ActiveSkills;

import Models.Entities.Entity;
import Models.Consequences.Consequence;
import Models.Entities.Skills.Skill;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Utilities.Savable.Savable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by josh on 4/6/16.
 */
public abstract class ActiveSkill extends Skill implements Savable{
    protected Consequence consequence;
    protected int cooldownTime = 0;
    protected boolean isCooledDown = true;
    protected Timer cooldownTimer = new Timer();
    protected Timer effectTimer =  new Timer();
    //by default, requires no mana
    protected int manaRequired = 0;
    protected int manaLevelMultiplier = 0;

    public void activate(Entity entity){
        if(isCooledDown){
            if(percentChanceByLevel()) {
                if (hasEnoughMana(entity)) {
                    System.out.println("executing skill");
                    isCooledDown = false;
                    useMana(entity);
                    performSkill(entity);
                    doTheCoolDown();

                }
            }
        }
        else
            System.out.println("Not cooled down!");
    }

    final protected void doTheCoolDown(){
        cooldownTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                isCooledDown = true;
            }
        }, cooldownTime);
    }

    //Calculates a random number, checks if it's more than 0.9^level
    //This value doesn't go below 0.5 until level=7
    protected boolean percentChanceByLevel(){
//        if(Math.random() > Math.pow(0.9, level))
//            return true;
//        else
//            return false;
        return true;
    }

    //makes sure an entity has enough mana to perform the skill
    protected boolean hasEnoughMana(Entity entity) {
        System.out.println(manaRequired);
        System.out.println(level);
        System.out.println(manaLevelMultiplier);
        System.out.println(entity.getStats().getStat(Stat.MANA));
        System.out.println("Required mana: " + (manaRequired + level * manaLevelMultiplier) + "\nMANA: " + entity.getStats().getStat(Stat.MANA));
        if (entity.getStats().getStat(Stat.MANA) > manaRequired + level * manaLevelMultiplier) return true;
        else return false;
    }

    //use the mana for the skill
    protected void useMana(Entity entity) {
        StatModificationList statMod = new StatModificationList(new StatModification(Stat.MANA,-(manaRequired + level * manaLevelMultiplier)));
        statMod.applyModifications(entity.getStats());
    }

    //Skill mechanic should be defined here
    protected void performSkill(Entity entity) {
        consequence.execute(entity);
    }

    @Override
    public Document save(Document doc, Element parentElement) {
        return null;
    }

    @Override
    public void load(Element data) {
        this.consequence.load((Element) data.getElementsByTagName("consequence"));
    }
}
