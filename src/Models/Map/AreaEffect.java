package Models.Map;

import Models.Entities.Entity;

import java.awt.*;

/**
 * Created by johnkaufmann on 4/6/16.
 *
 */
public abstract class AreaEffect {
    protected Decal decal;
    protected boolean isVisible;
    protected boolean isRemovable;

    public abstract void activate(Entity entity);
    public void setVisibility(boolean visibility){ isVisible = visibility; }
    public abstract Image getImage();
}