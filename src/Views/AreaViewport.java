package Views;

import Models.Entities.Entity;
import Models.Map.Map;
import Utilities.MapUtilities.MapDrawingVisitor;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Bradley on 4/7/16.
 */
public class AreaViewport extends View {

    private Map map;
    private Entity avatar;

    public AreaViewport(int width, int height, Map map, Entity avatar) {
        super(width, height);

        this.map = map;
        this.avatar = avatar;
        map.addObserver(this);
        avatar.addObserver(this);

        repaint();
    }

    @Override
    protected void repaint() {

        Graphics g = viewContent.getGraphics();

        // Draw the background
        renderBackground(g);

        // Draw the map onto the graphics
        map.draw(viewContent, avatar.getLocation());

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
}
