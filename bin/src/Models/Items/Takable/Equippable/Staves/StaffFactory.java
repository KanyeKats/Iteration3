package Models.Items.Takable.Equippable.Staves;

import Models.Entities.Requirements.OccupationRequirement;
import Models.Entities.Requirements.RequirementList;
import Models.Entities.Requirements.StatRequirement;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Models.Items.Takable.Equippable.WeaponType;
import Views.Graphics.Assets;
import Views.Graphics.ImageLoader;

/**
 * Created by sergiopuleri on 4/8/16.
 */
public enum StaffFactory {
    AIR_STAFF(8000) {
        @Override
        public Staff createInstance() {
            StatModification buff = new StatModification(Stat.WEAPON_MODIFIER, 5);
            StatRequirement sReq = new StatRequirement(Stat.INTELLECT, 0);
            OccupationRequirement oReq = new OccupationRequirement(WeaponType.STAFF);
            RequirementList reqs = new RequirementList();
            reqs.addRequirement(sReq);
            reqs.addRequirement(oReq);
            return new Staff(ImageLoader.loadImage("./res/items/airstaff.png"), new StatModificationList(buff), "Air Staff", "A staff of air. Increases your magic damage", reqs, 8000, 30);
        }
    },
    FIRE_STAFF(8001) {
        @Override
        public Staff createInstance() {
            StatModification buff = new StatModification(Stat.WEAPON_MODIFIER, 10);
            StatRequirement sReq = new StatRequirement(Stat.INTELLECT, 0);
            OccupationRequirement oReq = new OccupationRequirement(WeaponType.STAFF);
            RequirementList reqs = new RequirementList();
            reqs.addRequirement(sReq);
            reqs.addRequirement(oReq);
            return new Staff(ImageLoader.loadImage("./res/items/firestaff.png"), new StatModificationList(buff), "Fire Staff", "A staff of fire. Increases your magic damage", reqs, 8001, 45);
        }
    },
    WATER_STAFF(8002) {
        @Override
        public Staff createInstance() {
            StatModification buff = new StatModification(Stat.WEAPON_MODIFIER, 15);
            StatRequirement sReq = new StatRequirement(Stat.INTELLECT, 0);
            OccupationRequirement oReq = new OccupationRequirement(WeaponType.STAFF);
            RequirementList reqs = new RequirementList();
            reqs.addRequirement(sReq);
            reqs.addRequirement(oReq);
            return new Staff(ImageLoader.loadImage("./res/items/waterstaff.png"), new StatModificationList(buff), "Water Staff", "A staff of water. Increases your magic damage", reqs, 8002, 60);
        }
    },
    LIGHT_STAFF(8003) {
        @Override
        public Staff createInstance() {
            StatModification buff = new StatModification(Stat.WEAPON_MODIFIER, 20);
            StatRequirement sReq = new StatRequirement(Stat.INTELLECT, 0);
            OccupationRequirement oReq = new OccupationRequirement(WeaponType.STAFF);
            RequirementList reqs = new RequirementList();
            reqs.addRequirement(sReq);
            reqs.addRequirement(oReq);
            return new Staff(Assets.PLACEHOLDER, new StatModificationList(buff), "Staff of Light", "An insanely staff, harnessing the power of light. Beware.", reqs, 8003, 75);
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