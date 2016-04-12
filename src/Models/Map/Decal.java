package Models.Map;

import Utilities.Savable.Savable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by Bradley on 4/5/2016.
 */
public class Decal implements Savable {
    public static Decal trap;
    public static Decal teleport;
    public static Decal damage;
    public static Decal death;
    public static Decal heal;
    public static Decal level;
    public static Decal river;

    @Override
    public Document save(Document doc, Element parentElement) {
        return null;
    }

    @Override
    public void load(Element data) {

    }
}
