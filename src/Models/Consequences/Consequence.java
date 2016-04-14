package Models.Consequences;

import Models.Entities.Entity;
import Utilities.Savable.Savable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by josh on 4/6/16.
 */
public abstract class Consequence implements Savable {
    public abstract void execute(Entity entity);

    public abstract void remove(Entity entity);

    @Override
    public Document save(Document doc, Element parentElement) {
        return null;
    }

    @Override
    public void load(Element data) {

    }
}
