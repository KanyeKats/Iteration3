package Models.Items.Takable.Equippable.Staves;

import Models.Entities.Requirements.StatRequirement;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Views.Graphics.Assets;

/**
 * Created by sergiopuleri on 4/8/16.
 */
public enum StaffFactory {
    AIR_STAFF(8000) {
        @Override
        public Staff createInstance() {
            StatModification buff = new StatModification(Stat.WEAPON_MODIFIER, 5);
            StatRequirement req = new StatRequirement(Stat.INTELLECT, 0);
            return new Staff(Assets.PLACEHOLDER, new StatModificationList(buff), "Air Staff", "A basic staff of air. Makes you do more magic damage", req);
        }
    },
    FIRE_STAFF(8001) {
        @Override
        public Staff createInstance() {
            StatModification buff = new StatModification(Stat.WEAPON_MODIFIER, 10);
            StatRequirement req = new StatRequirement(Stat.INTELLECT, 0);
            return new Staff(Assets.PLACEHOLDER, new StatModificationList(buff), "Fire Staff", "A basic staff of fire. Makes you do more magic damage", req);
        }
    },
    MYSTIC_STAFF(8002) {
        @Override
        public Staff createInstance() {
            StatModification buff = new StatModification(Stat.WEAPON_MODIFIER, 15);
            StatRequirement req = new StatRequirement(Stat.INTELLECT, 0);
            return new Staff(Assets.PLACEHOLDER, new StatModificationList(buff), "Mystic Staff", "A magical, mystic staff", req);
        }
    },
    LIGHT_STAFF(8003) {
        @Override
        public Staff createInstance() {
            StatModification buff = new StatModification(Stat.WEAPON_MODIFIER, 20);
            StatRequirement req = new StatRequirement(Stat.INTELLECT, 0);
            return new Staff(Assets.PLACEHOLDER, new StatModificationList(buff), "Staff of Light", "An insanely staff, harnessing the power of light. Beware.", req);
        }
    };

    // Properties
    private int ID;

    // Constructor
    StaffFactory(int ID) {
        this.ID = ID;
    }

    // Getter
    public int getID() {
        return ID;
    }

    // Abstract method each enum value needs to implement to instantiate the correct instance.
    public abstract Staff createInstance();


    // Method to instantiate by an ID, used when loading a map.
    // Loop through all enum values
    // Check if the enum's ID is equal to the passed in ID,
    // If so, return an instance of that enum
    // Otherwise, return null, an invalid ID was passed in
    public static Staff staffFromID(int id) {
        for (StaffFactory staff : values()) {
            if (staff.getID() == id) {
                return staff.createInstance();
            }
        }
        return null;
    }
}