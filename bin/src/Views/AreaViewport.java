package Views;

import Models.Entities.Entity;
import Models.Entities.Stats.Stat;
import Models.Map.Camera;
import Models.Map.Direction;
import Models.Map.Map;
import javafx.geometry.Point3D;

import java.awt.*;

/**
 * Created by Bradley on 4/7/16.
 */
public class AreaViewport extends View {

    private Map map;
    private Entity avatar;
    private Boolean isMoving;
    private boolean debugMode;
    private Point3D renderLocation;
    private Camera camera;

    public AreaViewport(int width, int height, Map map, Entity avatar) {
        super(width, height);

        this.map = map;
        this.avatar = avatar;
        this.camera = new Camera(avatar.getLocation());
        this.isMoving = false;
        this.debugMode = false;

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
            camera.setLocation(renderLocation);
        }
        else {
            renderLocation = camera.getLocation();
        }
        map.draw(viewContent, renderLocation, avatar.getLocation(), avatar.getStats().getStat(Stat.RADIUS_OF_VISIBILITY),isMoving, debugMode);

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

    public void toggleDebugMode() {
        this.debugMode = !this.debugMode;
    }

    public boolean isMoving(){
        return this.isMoving;
    }
}
