package Models.Items.Takable.Equippable.TwoHandedWeapon;

import Models.Entities.Requirements.StatRequirement;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Views.Graphics.Assets;
import Views.Graphics.ImageLoader;

/**
 * Created by sergiopuleri on 4/8/16.
 */
public enum TwoHandedWeaponFactory {
    AXE_2H(7000) {
        @Override
        public TwoHandedWeapon createInstance() {
            StatModification buff = new StatModification(Stat.WEAPON_MODIFIER, 5);
            StatRequirement req = new StatRequirement(Stat.STRENGTH, 0);
            return new TwoHandedWeapon(ImageLoader.loadImage("./res/items/twoHandedAxe.png"), new StatModificationList(buff), "Two Handed Axe", "A basic two-handed axe; stronger, but slower than its one-handed counterpart.", req, 7000);
        }
    },
    IRON_2H(7001) {
        @Override
        public TwoHandedWeapon createInstance() {
            StatModification buff = new StatModification(Stat.WEAPON_MODIFIER, 10);
            StatRequirement req = new StatRequirement(Stat.STRENGTH, 0);
            return new TwoHandedWeapon(Assets.PLACEHOLDER, new StatModificationList(buff), "Iron 2H", "A basic two-handed iron sword; stronger, but slower than its one-handed counterpart.", req, 7001);
        }
    },
    DRAGON_2H(7002) {
        @Override
        public TwoHandedWeapon createInstance() {
            StatModification buff = new StatModification(Stat.WEAPON_MODIFIER, 15);
            StatRequirement req = new StatRequirement(Stat.STRENGTH, 0);
            return new TwoHandedWeapon(ImageLoader.loadImage("./res/items/dragon_sword.png"), new StatModificationList(buff), "Dragon 2H", "A heavy hitting dragon two-handed sword", req, 7002);
        }
    },
    GODSWORD(7003) {
        @Override
        public TwoHandedWeapon createInstance() {
            StatModification buff = new StatModification(Stat.WEAPON_MODIFIER, 20);
            StatRequirement req = new StatRequirement(Stat.STRENGTH, 0);
            return new TwoHandedWeapon(Assets.PLACEHOLDER, new StatModificationList(buff), "Armadyl Godsword", "An insanely powerful sword. Beware.", req, 7003);
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

