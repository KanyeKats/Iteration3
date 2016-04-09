package Models.Items.Takable.Equippable.RangedWeapons;

import Models.Entities.Requirements.StatRequirement;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Views.Graphics.Assets;

/**
 * Created by sergiopuleri on 4/8/16.
 */
public enum RangedWeaponFactory {
    THROWING_KNIVES(9000) {
        @Override
        public RangedWeapon createInstance() {
            StatModification buff = new StatModification(Stat.WEAPON_MODIFIER, 5);
            StatRequirement req = new StatRequirement(Stat.AGILITY, 0);
            return new RangedWeapon(Assets.PLACEHOLDER, new StatModificationList(buff), "Steel throwing knives", "Some sharp, steel throwing knives", req);
        }
    },
    NINJA_STARS(9001) {
        @Override
        public RangedWeapon createInstance() {
            StatModification buff = new StatModification(Stat.WEAPON_MODIFIER, 10);
            StatRequirement req = new StatRequirement(Stat.AGILITY, 0);
            return new RangedWeapon(Assets.PLACEHOLDER, new StatModificationList(buff), "Ninja Throwing Stars", "Sharp, quick ninja throwing stars", req);
        }
    },
    CROSSBOW(9002) {
        @Override
        public RangedWeapon createInstance() {
            StatModification buff = new StatModification(Stat.WEAPON_MODIFIER, 15);
            StatRequirement req = new StatRequirement(Stat.AGILITY, 0);
            return new RangedWeapon(Assets.PLACEHOLDER, new StatModificationList(buff), "Crossbow", "A heavy hitting crossbow", req);
        }
    },
    DARK_BOW(9003) {
        @Override
        public RangedWeapon createInstance() {
            StatModification buff = new StatModification(Stat.WEAPON_MODIFIER, 20);
            StatRequirement req = new StatRequirement(Stat.AGILITY, 0);
            return new RangedWeapon(Assets.PLACEHOLDER, new StatModificationList(buff), "Darkbow", "An insanely powerful, dark compound bow.", req);
        }
    };

    // Properties
    private int ID;

    // Constructor
    RangedWeaponFactory(int ID) {
        this.ID = ID;
    }

    // Getter
    public int getID() {
        return ID;
    }

    // Abstract method each enum value needs to implement to instantiate the correct instance.
    public abstract RangedWeapon createInstance();


    // Method to instantiate by an ID, used when loading a map.
    // Loop through all enum values
    // Check if the enum's ID is equal to the passed in ID,
    // If so, return an instance of that enum
    // Otherwise, return null, an invalid ID was passed in
    public static RangedWeapon rangedWeaponFromID(int id) {
        for (RangedWeaponFactory weapon : values()) {
            if (weapon.getID() == id) {
                return weapon.createInstance();
            }
        }
        return null;
    }
}