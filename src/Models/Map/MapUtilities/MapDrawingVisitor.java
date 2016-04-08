package Models.Map.MapUtilities;

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

    private Graphics g;
    private int viewportWidth;
    private int viewportHeight;
    private Point3D center;

    public MapDrawingVisitor(BufferedImage image, Point3D center){

        this.g = image.getGraphics();
        this.center = center;
        this.viewportHeight = image.getHeight();
        this.viewportWidth = image.getWidth();
    }

    public void accept(HashMap<Point3D, Tile> tile){

        // Set up some useful variables
        int tileWidth = Constants.TILE_WIDTH;
        int tileHeight = Constants.TILE_HEIGHT;
        int tileDepth = tileHeight / 4;

        int tileImageWidth = tileWidth;
        int tileImageHeight = tileHeight + tileDepth;

        System.out.println(tileImageWidth + ", " + tileImageHeight);

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

//            System.out.println(center.toString());
            if(currentPoint.getZ() == center.getZ() && currentPoint.getX() == center.getX() && currentPoint.getY() == center.getY() -1){
                System.out.println(currentPoint.toString());
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
            pixelY += (currentPoint.getX() - center.getX()) * (vertDistanceBtwnAdjTiles/2);
            pixelX += (currentPoint.getX() - center.getX()) * horizDistanceBtwnAdjTiles;


            // Adjust the pixel cooredinates to the top left corner
            pixelX -= tileImageWidth/2;
            pixelY -= tileImageHeight/2;

            g.drawImage(tileImage, pixelX, pixelY, null);





        }

        g.dispose();

        // TODO DRAW THE MAP AND RETURN AN IMAGE
    }
}
