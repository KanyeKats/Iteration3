package Models.Entities.Requirements;

import Models.Entities.Entity;
import Models.Items.Takable.Equippable.WeaponType;

/**
 * Created by Magic_Buddha on 4/16/2016.
 */
public class OccupationRequirement implements Requirement {

    private WeaponType weaponTypeRequired;
    public OccupationRequirement(WeaponType weaponType) {
        this.weaponTypeRequired = weaponType;
    }
    @Override
    public boolean isFullfilled(Entity entity) {
        return entity.getOccupation().compatibleWeapon(weaponTypeRequired);
    }
}
