package Models.Items.Takable.Equippable.TwoHandedWeapon;

import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Views.Graphics.Assets;

/**
 * Created by sergiopuleri on 4/8/16.
 */
public enum TwoHandedWeaponFactory {
    BRONZE_2H(7000) {
        @Override
        public TwoHandedWeapon createInstance() {
            StatModification buff = new StatModification(Stat.WEAPON_MODIFIER, 5);
            return new TwoHandedWeapon(Assets.PLACEHOLDER, new StatModificationList(buff), "Bronze Two-Handed Sword", "A basic two-handed bronze sword; stronger, but slower than its one-handed counterpart.");
        }
    },
    IRON_2H(7001) {
        @Override
        public TwoHandedWeapon createInstance() {
            StatModification buff = new StatModification(Stat.WEAPON_MODIFIER, 10);
            return new TwoHandedWeapon(Assets.PLACEHOLDER, new StatModificationList(buff), "Iron Two-Handed Sword", "A basic two-handed iron sword; stronger, but slower than its one-handed counterpart.");
        }
    },
    DRAGON_2H(7002) {
        @Override
        public TwoHandedWeapon createInstance() {
            StatModification buff = new StatModification(Stat.WEAPON_MODIFIER, 15);
            return new TwoHandedWeapon(Assets.PLACEHOLDER, new StatModificationList(buff), "Dragon Two-Handed Sword", "A heavy hitting dragon two-handed sword");
        }
    },
    GODSWORD(7003) {
        @Override
        public TwoHandedWeapon createInstance() {
            StatModification buff = new StatModification(Stat.WEAPON_MODIFIER, 20);
            return new TwoHandedWeapon(Assets.PLACEHOLDER, new StatModificationList(buff), "Armadyl Godsword", "An insanely powerful sword. Beware.");
        }
    };

    // Properties
    private int ID;

    // Constructor
    TwoHandedWeaponFactory(int ID) {
        this.ID = ID;
    }

    // Getter
    public int getID() {
        return ID;
    }

    // Abstract method each enum value needs to implement to instantiate the correct instance.
    public abstract TwoHandedWeapon createInstance();


    // Method to instantiate by an ID, used when loading a map.
    // Loop through all enum values
    // Check if the enum's ID is equal to the passed in ID,
    // If so, return an instance of that enum
    // Otherwise, return null, an invalid ID was passed in
    public static TwoHandedWeapon twoHandedWeaponFromID(int id) {
        for (TwoHandedWeaponFactory weapon : values()) {
            if (weapon.getID() == id) {
                return weapon.createInstance();
            }
        }
        return null;
    }
}

