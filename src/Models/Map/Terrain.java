package Models.Map;

import Models.Items.Item;
import Utilities.Savable.Savable;
import Views.Graphics.Assets;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
            return Assets.FULLFOG;
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
            return Assets.FULLFOG;
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
