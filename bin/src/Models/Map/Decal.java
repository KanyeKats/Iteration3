package Models.Map;

import Utilities.Savable.Savable;
import Views.Graphics.Assets;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;

/**
 * Created by Bradley on 4/5/2016.
 */
public class Decal implements Savable {

    // Initializers for diff types of decals
    public static Decal trap(){
        return new Decal(Assets.TRAP);
    }
    public static Decal teleport() {
        return new Decal(Assets.TELEPORT);
    }
    public static Decal damage() {
        return new Decal(Assets.DAMAGE);
    }
    public static Decal death() {
        return new Decal(Assets.DEATH);
    }
    public static Decal heal() {
        return new Decal(Assets.HEAL);
    }
    public static Decal level() {
        return new Decal(Assets.LEVEL);
    }
    public static Decal river() {
        return new Decal(Assets.WATER);
    }

    private Image decalImage;

    public Decal(Image decalImage) {
        this.decalImage = decalImage;
    }

    public Image getDecalImage() {
        return decalImage;
    }

    public void setDecalImage(Image decalImage) {
        this.decalImage = decalImage;
    }

    @Override
    public Document save(Document doc, Element parentElement) {
        return null;
    }

    @Override
    public void load(Element data) {

    }
}
