package Models.Map;

import Models.Entities.Entity;
import Utilities.Savable.Savable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.Image;

/**
 * Created by johnkaufmann on 4/6/16.
 *
 */
public abstract class AreaEffect implements Savable {
    protected Decal decal;
    protected boolean isVisible;
    protected boolean isRemovable;

    public abstract void activate(Entity entity);
    public void setVisibility(boolean visibility){ isVisible = visibility; }
    public abstract Image getImage();

    @Override
    public Document save(Document doc, Element parentElement) {
        return null;
    }

    @Override
    public void load(Element data) {

    }
}