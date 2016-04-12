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
        public BufferedImage getImage() {
            return Assets.EARTH;
        }
    },
    SKY(){
        @Override
        public BufferedImage getImage() {
            return Assets.SKY;
        }
    },
    WATER(){
        @Override
        public BufferedImage getImage() {
            return Assets.WATER;
        }
    };

    public abstract BufferedImage getImage();

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
