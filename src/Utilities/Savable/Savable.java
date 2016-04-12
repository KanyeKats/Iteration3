package Utilities.Savable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.print.Doc;

/**
 * Created by johnkaufmann on 3/31/16.
 */
public interface Savable {
    public Document save(Document doc, Element parentElement);
    public void load(Element data);
}
