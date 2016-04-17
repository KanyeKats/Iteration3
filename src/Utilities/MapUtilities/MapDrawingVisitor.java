package Utilities.MapUtilities;

import Models.Entities.Entity;
import Models.Map.MapUtilities.EntityDrawer;
import Models.Map.MapUtilities.MapUtilities;
import Models.Map.MapUtilities.ShadowDrawer;
import Models.Map.Terrain;
import Models.Map.Tile;
import Utilities.Constants;
import javafx.geometry.Point3D;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Created by Bradley on 4/7/16.
 */
public class MapDrawingVisitor  {

    private static int viewportWidth;
    private static int viewportHeight;
    private static Point3D center;
    private static HashMap<Point3D, Tile> tilesOnScreen;

    public static void setViewportWidth(int w) {
        viewportWidth = w;
    }

    public static void setViewportHeight(int h) {
        viewportHeight = h;
    }

    public static void setCenter(Point3D c) {
        center = c;
    }

    // The master drawer
    public static void accept(HashMap<Point3D, Tile> tile,
                              BufferedImage viewContent,
                              Point3D drawingCenter,
                              Point3D avatarLocation,
                              int rangeofVisibility,
                              boolean cameraMoving,
                              boolean isDebug) {

        boolean justRecentered = false;

        if(tilesOnScreen == null)
            tilesOnScreen = MapNavigationUtilities.getTilesOnScreen(drawingCenter, tile);

        // Set center, height, and width
        if (center == null) setCenter(drawingCenter);
        setViewportHeight(viewContent.getHeight());
        setViewportWidth(viewContent.getWidth());

        // Get graphics of GameView's viewContent.
        Graphics g = viewContent.getGraphics();

        // Get distance between AreaViewPort's current center and the Avatar's location
        int distance = MapUtilities.distanceBetweenPoints(MapUtilities.to2DPoint(center), MapUtilities.to2DPoint(drawingCenter));

        // Re-center on avatar if necessary.
        if (distance > 4) {
            setCenter(drawingCenter);
            justRecentered = true;
            tilesOnScreen = MapNavigationUtilities.getTilesOnScreen(drawingCenter, tile);
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
        ArrayList<Tile> tilesinSight = MapNavigationUtilities.getTilesinPrism(avatarLocation, rangeofVisibility, tile);

        for(Point3D point : tile.keySet()){
            if(tilesOnScreen.containsKey(point))
                priorityQueue.offer(point);
        }

        while(!priorityQueue.isEmpty()){

            Point3D currentPoint = priorityQueue.poll();
            // Get the next tile to be rendered.
            Tile currentTile = tile.get(currentPoint);
            // Get the image from this tile.
            Image tileImage;
            if(isDebug){
                tileImage = currentTile.acceptDrawingVisitor(new TileDrawingVisitor(), true);
                currentTile.setVisited();
            }
            else {
                if (tilesinSight.contains(currentTile)) {
                    tileImage = currentTile.acceptDrawingVisitor(new TileDrawingVisitor(), true);
                    currentTile.setVisited();
                } else {
                    tileImage = currentTile.acceptDrawingVisitor(new TileDrawingVisitor(), false);
                }
            }



            // Figure out where to put it!
            // X and Y will start at the center of the screen.
            int pixelX = viewportWidth/2;
            int pixelY = viewportHeight/2;

            // Calculate the result of Z on the position.
            pixelY -= (currentPoint.getZ() - center.getZ()) * tileDepth;

            // Calculate the result of Y on the position
            pixelY += (currentPoint.getY() - center.getY()) * vertDistanceBtwnAdjTiles;

            // Calculate the result of X on the position.
            pixelY += (currentPoint.getX() - center.getX()) * (vertDistanceBtwnAdjTiles / 2);
            pixelX += (currentPoint.getX() - center.getX()) * horizDistanceBtwnAdjTiles;

            // Adjust the pixel cooredinates to the top left corner
            pixelX -= tileImageWidth / 2;
            pixelY -= tileImageHeight / 2;

            Point pixelPoint = new Point(pixelX, pixelY);

            // Set the tiles pixel point (where it is being drawn on the screen)
            currentTile.setPixelPoint(pixelPoint);

            g.drawImage(tileImage, pixelX, pixelY, null);

            // Draw debug text if in debug mode
            if (isDebug) drawDebugText(g, currentTile, currentPoint, pixelX, pixelY);

            // If any entity is on the tile, init its pixel location and draw him!
            if (currentTile.containsEntity()) {
                Entity currentEntity = currentTile.getEntity();

                if(justRecentered){
                    currentEntity.setPixelLocation(pixelPoint);
                }

                if(!cameraMoving) {
                    currentEntity.initPixelLocation(pixelPoint);
                }
                else{
                    currentEntity.setPixelLocation(pixelPoint);
                }
                if (tilesinSight.contains(currentTile)) {
                    EntityDrawer.drawEntity(currentEntity, g);
                }
            }

            if(MapNavigationUtilities.isEntityaboveTile(currentPoint,tile)){
                ShadowDrawer.drawShadow(currentTile,g);
            }
        }
        g.dispose();
    }


    public static void drawDebugText(Graphics gg, Tile tile, Point3D currentPoint, int pixelX, int pixelY) {

        // Only draw if not sky
        if (tile.getTerrain() != Terrain.SKY) {
            Graphics2D g = (Graphics2D)gg;
            RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHints(rh);

            // For debugging, draw the axial point on the tile
            String tilePointString = Integer.toString((int) currentPoint.getX()) +
                    ", " + Integer.toString((int) currentPoint.getY()) +
                    ", " + Integer.toString((int) currentPoint.getZ());

            // For debugging, draw point
            Font pointFont = new Font("SansSerif", 1, 8);
            g.setFont(pointFont);
            g.setColor(Color.white);
            FontMetrics fm = g.getFontMetrics(pointFont);
            Rectangle2D rect = fm.getStringBounds(tilePointString, g);
            int x =  pixelX + (Constants.TILE_WIDTH - (int)rect.getWidth())/2;


            int y = pixelY + (Constants.TILE_HEIGHT - (int)rect.getHeight())/2;
//            y = pixelY + Constants.TILE_HEIGHT/2;

            g.drawString(tilePointString, x, y );
        }

    }
}
