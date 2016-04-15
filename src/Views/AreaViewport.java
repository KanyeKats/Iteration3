package Views;

import Models.Entities.Entity;
import Models.Entities.Stats.Stat;
import Models.Map.Camera;
import Models.Map.Direction;
import Models.Map.Map;
import Utilities.MapUtilities.MapDrawingVisitor;
import javafx.geometry.Point3D;

import javax.xml.stream.Location;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Bradley on 4/7/16.
 */
public class AreaViewport extends View {

    private Map map;
    private Entity avatar;
    private Boolean isMoving;
    private Point3D renderLocation;
    private Camera camera;

    public AreaViewport(int width, int height, Map map, Entity avatar) {
        super(width, height);

        this.map = map;
        this.avatar = avatar;
        this.camera = new Camera(avatar.getLocation());
        map.addObserver(this);
        avatar.addObserver(this);
        camera.addObserver(this);
        this.isMoving = false;

        repaint();
    }

    @Override
    protected void repaint() {

        Graphics g = viewContent.getGraphics();

        // Draw the background
        renderBackground(g);

        // Draw the map onto the graphics
        if (!isMoving){
            renderLocation = avatar.getLocation();
            camera.setLocation(avatar.getLocation());
         }
        else {
            renderLocation = camera.getLocation();
        }
        map.draw(viewContent, renderLocation, avatar.getLocation(), avatar.getStats().getStat(Stat.RADIUS_OF_VISIBILITY),isMoving);
        // Notify the observers of this view (GameView) that it changed.
        this.setChanged();
        this.notifyObservers();

        // Cleanup
        //g.dispose();
    }

    private void renderBackground(Graphics g){

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
    }

    //Moving the Camera
    public void move(Direction direction){
        camera.move(direction);
    }

    public void setMoving(boolean isMoving){
        this.isMoving = isMoving;
    }

    public boolean isMoving(){
        return this.isMoving;
    }
}
