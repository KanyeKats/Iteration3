package Models.Items.Takable.Equippable.OneHandedWeapons;

import Models.Entities.Requirements.StatRequirement;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Views.Graphics.Assets;

/**
 * Created by sergiopuleri on 4/8/16.
 */
public enum OneHandedWeaponFactory {
    BRONZE_SHORT_SWORD(6000) {
        @Override
        public OneHandedWeapon createInstance() {
            StatModification buff = new StatModification(Stat.WEAPON_MODIFIER, 5);
            StatRequirement req = new StatRequirement(Stat.STRENGTH, 0);
            return new OneHandedWeapon(Assets.PLACEHOLDER, new StatModificationList(buff), "Bronze short sword", "A basic bronze sword", req, 6000);
        }
    },
    IRON_LONG_SWORD(6001) {
        @Override
        public OneHandedWeapon createInstance() {
            StatModification buff = new StatModification(Stat.WEAPON_MODIFIER, 10);
            StatRequirement req = new StatRequirement(Stat.STRENGTH, 0);
            return new OneHandedWeapon(Assets.PLACEHOLDER, new StatModificationList(buff), "Iron longsword", "An iron longsword" ,req, 6001);
        }
    },
    DDS(6002) {
        @Override
        public OneHandedWeapon createInstance() {
            StatModification buff = new StatModification(Stat.WEAPON_MODIFIER, 15);
            StatRequirement req = new StatRequirement(Stat.STRENGTH, 0);
            return new OneHandedWeapon(Assets.PLACEHOLDER, new StatModificationList(buff), "Dragon Dagger Super-Poisened", "A super poisened dragon dagger. This weapons special attack is deadly.", req, 6002);
        }
    },
    ABBY_WHIP(6003) {
        @Override
        public OneHandedWeapon createInstance() {
            StatModification buff = new StatModification(Stat.WEAPON_MODIFIER, 20);
            StatRequirement req = new StatRequirement(Stat.STRENGTH, 0);
            return new OneHandedWeapon(Assets.PLACEHOLDER, new StatModificationList(buff), "Abyssal whip", "A super quick, super strong weapon.", req, 6003);
        }
    };

    // Properties
    private int ID;

    // Constructor
    OneHandedWeaponFactory(int ID) {
        this.ID = ID;
    }

    // Getter
    public int getID() {
        return ID;
    }

    // Abstract method each enum value needs to implement to instantiate the correct instance.
    public abstract OneHandedWeapon createInstance();


    // Method to instantiate by an ID, used when loading a map.
    // Loop through all enum values
    // Check if the enum's ID is equal to the passed in ID,
    // If so, return an instance of that enum
    // Otherwise, return null, an invalid ID was passed in
    public static OneHandedWeapon oneHandedWeaponFromID(int id) {
        for (OneHandedWeaponFactory weapon : values()) {
            if (weapon.getID() == id) {
                return weapon.createInstance();
            }
        }
        return null;
    }
}
