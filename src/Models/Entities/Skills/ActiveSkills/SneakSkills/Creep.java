package Models.Entities.Skills.ActiveSkills.SneakSkills;

import Models.Entities.Entity;
import Models.Entities.Skills.ActiveSkills.ActiveSkill;

import java.util.TimerTask;

/**
 * Created by josh on 4/6/16.
 */
public class Creep extends ActiveSkill {

    public final int BASE_COOLDOWN_TIME = 30000;    //30 seconds
    public final int BASE_EFFECT_TIME = 10000;      //10 seconds
    private Entity entity;
    protected int manaRequired = 0;
    protected int manaLevelMultiplier = 0;
    private final int BASE_MANA_REQUIRED = 5;
    private final int MANA_LEVEL_MULTIPLIER = 1;


    public Creep(){
        cooldownTime = BASE_COOLDOWN_TIME;
        manaRequired = BASE_MANA_REQUIRED;
        manaLevelMultiplier = MANA_LEVEL_MULTIPLIER;
    }

    @Override
    protected void performSkill(Entity entity) {
        entity.setVisible(false);
        this.entity = entity;

        effectTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                entity.setVisible(true);
            }
        }, BASE_EFFECT_TIME);
    }

    @Override
    public String toString(){
        return "Creep";
    }
}
