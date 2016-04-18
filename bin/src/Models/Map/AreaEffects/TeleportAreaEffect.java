package Models.Map.AreaEffects;

import Models.Entities.Entity;
import Models.Map.AreaEffect;
import Models.Map.Decal;
import javafx.geometry.Point3D;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;

/**
 * Created by johnkaufmann on 4/6/16.
 *
 */
public class TeleportAreaEffect extends AreaEffect {
    private Point3D targetLocation;

    public TeleportAreaEffect(Point3D targetLocation) {
        // Set target location
        this.targetLocation = targetLocation;

        // Set decal
        this.decal = Decal.teleport();

        // Set visibility and removability
        this.isVisible = true;
        this.isRemovable = false;
    }

    @Override
    public void activate(Entity entity) {
        // Move the entity to the target point.
        entity.move(targetLocation);
    }

    @Override
    public Document save(Document doc, Element parentElement) {
        Element areaEffect = doc.createElement("area-effect");
        areaEffect.setAttribute("type", "TELEPORT");
        String value = Integer.toString((int)targetLocation.getX()) + ",";
        value += Integer.toString((int)targetLocation.getY()) + ",";
        value += Integer.toString((int)targetLocation.getZ());
        areaEffect.setAttribute("value", value);
        parentElement.appendChild(areaEffect);

        return null;
    }

}
