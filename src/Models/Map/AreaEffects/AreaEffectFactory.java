package Models.Map.AreaEffects;

import Models.Map.AreaEffect;
import Models.Map.Direction;
import javafx.geometry.Point3D;

/**
 * Created by sergiopuleri on 4/12/16.
 */

// Enum used to create AoE's from the map XML
// River wont be in here as we will just add it in on any WATER terrain tiles
public enum AreaEffectFactory {
    DAMAGE() {
        @Override
        public AreaEffect createInstance(String value) {
            int intValue = Integer.parseInt(value);
            return new DamageAreaEffect(intValue);
        }
    } ,
    HEAL() {
        @Override
        public AreaEffect createInstance(String value) {
            int intValue = Integer.parseInt(value);
            return new HealDamageAreaEffect(intValue);
        }
    },
    LEVEL() {
        @Override
        public AreaEffect createInstance(String value) {
            return new LevelUpAreaEffect();
        }
    },
    DEATH() {
        @Override
        public AreaEffect createInstance(String value) {
            return new InstantDeathAreaEffect();
        }
    },
    TELEPORT() {
        @Override
        public AreaEffect createInstance(String value) {
            String[] pointValue = value.split(",");
            int targetX = Integer.parseInt(pointValue[0]);
            int targetY = Integer.parseInt(pointValue[1]);
            int targetZ = Integer.parseInt(pointValue[2]);
            return new TeleportAreaEffect(new Point3D(targetX, targetY, targetZ));
        }
    },
    RIVER() {
        @Override
        public AreaEffect createInstance(String value) {
            int intValue = Integer.parseInt(value);
            return new RiverAreaEffect(Direction.SOUTH_EAST, intValue);
        }
    },
    TRAP() {
        @Override
        public AreaEffect createInstance(String value) {
            int intValue = Integer.parseInt(value);
            return new TrapAreaEffect(intValue);
        }
    };

    public abstract AreaEffect createInstance(String value);

}

