package Models.Entities.Skills.ActiveSkills.SneakSkills;

import Models.Entities.Entity;
import Models.Entities.Skills.ActiveSkills.ActiveSkill;
import javafx.geometry.Point3D;

/**
 * Created by josh on 4/6/16.
 */
public class PickPocket extends ActiveSkill {

    public void activate(Entity entity) {

        Point3D pointCheck = entity.getOrientation().getPointAdjacentTo(entity.getPoint3D());

    }
}
