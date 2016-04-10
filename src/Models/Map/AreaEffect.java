package Models.Map;

import Models.Entities.Entity;

import java.awt.Image;

/**
 * Created by johnkaufmann on 4/6/16.
 *
 */
public abstract class AreaEffect {


//    public abstract void onTouch(Entity entity);
//    protected Consequence consequence;
    protected Decal decal;
    protected boolean isVisible;
    protected boolean isRemovable;

    public abstract void activate(Entity entity);
    public void setVisibility(boolean visibility){ isVisible = visibility; }
    public boolean getVisibility(){ return this.isVisible; }
    public abstract Image getImage();
}
