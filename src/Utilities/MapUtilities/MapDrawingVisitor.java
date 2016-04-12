package Utilities.MapUtilities;

import Models.Entities.Entity;
import Models.Map.Tile;
import Utilities.Constants;
import javafx.geometry.Point3D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Created by Bradley on 4/7/16.
 */
public class MapDrawingVisitor {

    private static int viewportWidth;
    private static int viewportHeight;
    private static Point3D center;

    public static void setViewportWidth(int w) {
        viewportWidth = w;
    }

    public static void setViewportHeight(int h) {
        viewportHeight = h;
    }

    public static void setCenter(Point3D c) {
        center = c;
    }

    public static void accept(HashMap<Point3D, Tile> tile, BufferedImage viewContent, Point3D avatarCenter){

        // Set center, height, and width
        if (center == null) setCenter(avatarCenter);
        setViewportHeight(viewContent.getHeight());
        setViewportWidth(viewContent.getWidth());

        // Get graphics of GameView's viewContent.
        Graphics g = viewContent.getGraphics();

        // Get distance between AreaViewPort's current center and the Avatar's location
        int distance = MapUtilities.distanceBetweenPoints(MapUtilities.to2DPoint(center), MapUtilities.to2DPoint(avatarCenter));

        // Re-center on avatar if necessary.
        if (distance > 4) {
            setCenter(avatarCenter);
        }

        // Set up some useful variables
        int tileWidth = Constants.TILE_WIDTH;
        int tileHeight = Constants.TILE_HEIGHT;
        int tileDepth = tileHeight / 4;

        int tileImageWidth = tileWidth;
        int tileImageHeight = tileHeight + tileDepth;

        int horizDistanceBtwnAdjTiles = tileWidth * 3 / 4;
        int vertDistanceBtwnAdjTiles = tileHeight;

        // Put all the points into a priority queue based upon the order in which they should be rendered.
        PriorityQueue<Point3D> priorityQueue = new PriorityQueue<>(new TileComparator());

        for(Point3D point : tile.keySet()){
            priorityQueue.offer(point);
        }

        while(!priorityQueue.isEmpty()){

            Point3D currentPoint = priorityQueue.poll();

            // Get the next tile to be rendered.
            Tile currentTile = tile.get(currentPoint);

            // Get the image from this tile.
            Image tileImage = currentTile.acceptDrawingVisitor(new TileDrawingVisitor());

            // Figure out where to put it!
            // X and Y will start at the center of the screen.
            int pixelX = viewportWidth/2;
            int pixelY = viewportHeight/2;

            // Calculate the result of Z on the position.
            pixelY -= (currentPoint.getZ() - center.getZ()) * tileDepth;

            // Calculate the result of Y on the position
            pixelY += (currentPoint.getY() - center.getY()) * vertDistanceBtwnAdjTiles;

            // Calculate the result of X on the position.
            pixelY += (currentPoint.getX() - center.getX()) * (vertDistanceBtwnAdjTiles/2);
            pixelX += (currentPoint.getX() - center.getX()) * horizDistanceBtwnAdjTiles;

            // Adjust the pixel cooredinates to the top left corner
            pixelX -= tileImageWidth/2;
            pixelY -= tileImageHeight/2;

            Point pixelPoint = new Point(pixelX, pixelY);

            // Set the tiles pixel point (where it is being drawn on the screen)
            currentTile.setPixelPoint(pixelPoint);

            g.drawImage(tileImage, pixelX, pixelY, null);

            // If any entity is on the tile, init its pixel location and draw him!
            if (currentTile.containsEntity()) {
                Entity currentEntity = currentTile.getEntity();
                currentEntity.initPixelLocation(pixelPoint);
                EntityDrawer.drawEntity(currentEntity, g);
            }
        }
        g.dispose();
    }
}
