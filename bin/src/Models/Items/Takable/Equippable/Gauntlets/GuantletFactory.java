package Models.Items.Takable.Equippable.Gauntlets;

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
public enum GuantletFactory {
    WOODEN(4000) {
        @Override
        public Guantlets createInstance() {
            StatModification buff = new StatModification(Stat.WEAPON_MODIFIER, 5);
            StatRequirement sReq = new StatRequirement(Stat.DEFENSIVE_RATING, 0);
            OccupationRequirement oReq = new OccupationRequirement(WeaponType.BRAWLING);
            RequirementList reqs = new RequirementList();
            reqs.addRequirement(sReq);
            reqs.addRequirement(oReq);
            return new Guantlets(ImageLoader.loadImage("./res/items/wooden_gauntlets.png"), new StatModificationList(buff), "Wooden Guantlets", "A pair of wooden guantlets", reqs, 4000, 20);
        }
    },
    BRASS_KNUCKLES(4001) {
        @Override
        public Guantlets createInstance() {
            StatModification buff = new StatModification(Stat.WEAPON_MODIFIER, 10);
            StatRequirement sReq = new StatRequirement(Stat.DEFENSIVE_RATING, 0);
            OccupationRequirement oReq = new OccupationRequirement(WeaponType.BRAWLING);
            RequirementList reqs = new RequirementList();
            reqs.addRequirement(sReq);
            reqs.addRequirement(oReq);
            return new Guantlets(ImageLoader.loadImage("./res/items/brass_knuckles.png"), new StatModificationList(buff), "Brass Knuckles", "A pair of dangerous brass knuckles", reqs, 4001, 30);
        }
    },
    JACKSON_GLOVES(4002) {
        @Override
        public Guantlets createInstance() {
            StatModification buff = new StatModification(Stat.WEAPON_MODIFIER, 15);
            StatRequirement sReq = new StatRequirement(Stat.DEFENSIVE_RATING, 0);
            OccupationRequirement oReq = new OccupationRequirement(WeaponType.BRAWLING);
            RequirementList reqs = new RequirementList();
            reqs.addRequirement(sReq);
            reqs.addRequirement(oReq);
            return new Guantlets(ImageLoader.loadImage("./res/items/jacksonglove.png"), new StatModificationList(buff), "Jackson Gloves", "A single white glove", reqs, 4002, 40);
        }
    },
    BLADE_FIST(4003) {
        @Override
        public Guantlets createInstance() {
            StatModification buff = new StatModification(Stat.WEAPON_MODIFIER, 20);
            StatRequirement sReq = new StatRequirement(Stat.DEFENSIVE_RATING, 0);
            OccupationRequirement oReq = new OccupationRequirement(WeaponType.BRAWLING);
            RequirementList reqs = new RequirementList();
            reqs.addRequirement(sReq);
            reqs.addRequirement(oReq);
            return new Guantlets(Assets.PLACEHOLDER, new StatModificationList(buff), "Blade Fists", "You'll have some sharp fists with these!", reqs, 4003, 50);
        }
    };

    // Properties
    private int ID;

    // Constructor
    GuantletFactory(int ID) {
        this.ID = ID;
    }

    // Getter
    public int getID() {
        return ID;
    }

    // Abstract method each enum value needs to implement to instantiate the correct instance.
    public abstract Guantlets createInstance();


    // Method to instantiate by an ID, used when loading a map.
    // Loop through all enum values
    // Check if the enum's ID is equal to the passed in ID,
    // If so, return an instance of that enum
    // Otherwise, return null, an invalid ID was passed in
    public static Guantlets guantletFromID(int id) {
        for (GuantletFactory guantlet : values()) {
            if (guantlet.getID() == id) {
                return guantlet.createInstance();
            }
        }
        return null;
    }
}
