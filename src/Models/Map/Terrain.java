package Models.Map;

import Views.Graphics.Assets;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by johnkaufmann on 3/31/16.
 */
public enum Terrain {
    EARTH(){
        @Override
        public BufferedImage getImage(boolean isInSight, boolean wasVisited) {
            if(isInSight){
                return Assets.EARTH;
            }
            else if(wasVisited){
                return Assets.EARTHSHROUDED;
            }
            return null;
        }
    },
    SKY(){
        @Override
        public BufferedImage getImage(boolean isInSight, boolean wasVisited) {

            return Assets.SKY;
        }
    },
    WATER(){
        @Override
        public BufferedImage getImage(boolean isInSight, boolean wasVisited)
        {
            if(isInSight){
                return Assets.WATER;
            }
            else if(wasVisited){
                return Assets.WATERSHROUDED;
            }
            return null;
        }
    };

    public abstract BufferedImage getImage(boolean isInSight, boolean wasVisited);

    // Checks if this current terrain is in a passed in list of terrains
    public boolean inList(ArrayList<Terrain> terrains) {
        for (Terrain t: terrains) {
            if (t == this) {
                return true;
            }
        }
        return false;
    }
}
