package Utilities.MapUtilities;

import Models.Entities.Entity;
import Models.Map.Direction;
import Models.Map.Map;
import Models.Map.Tile;
import Utilities.Constants;
import javafx.geometry.Point3D;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Aidan on 4/8/2016.
 */
public class MapNavigationUtilities {


    //finds tiles adjacent to entity
    public static ArrayList<Tile> findNeighbors(Point3D point, Map map){
        ArrayList<Tile> neighbors = new ArrayList<>();
        neighbors.add(map.getTile(point.add(1,-1,0)));
        neighbors.add(map.getTile(point.add(0,-1,0)));
        neighbors.add(map.getTile(point.add(-1,0,0)));
        neighbors.add(map.getTile(point.add(-1,1,0)));
        neighbors.add(map.getTile(point.add(0,1,0)));
        neighbors.add(map.getTile(point.add(1,0,0)));
        return neighbors;

    }

    public static Point3D findOpenTile(Entity entity, Map map){
        Point3D entityPoint = entity.getLocation();
        for(Direction direction: Direction.values()){
            Tile tile = map.getTile(direction.getPointAdjacentTo(entityPoint));
            if(tile !=null && !tile.preventsMovement(entity)){
                return direction.getPointAdjacentTo(entityPoint);
            }
        }
        return null;
    }

    public static ArrayList<ArrayList<Tile>> getRadialTiles(Point3D point, int range, Map map){

        double[] point4Dstart = convertAxialtoCuubic(point);
        double[] point4Dend = new double[4];
        ArrayList<ArrayList<Tile>> resultTiles = new ArrayList<>();
        for(int x = 0; x <= range; x++){
            ArrayList<Tile> tempTiles = new ArrayList<>();
            for(int i = -x; i <= x; i++) {
                for (int j = -x; j <= x; j++) {
                    for (int k = -x; k <= x; k++) {
                        point4Dend[0] = point4Dstart[0] - i;
                        point4Dend[1] = point4Dstart[1] - j;
                        point4Dend[2] = point4Dstart[2] - k;
                        point4Dend[3] = point.getZ();
                        if((point4Dend[0] + point4Dend[1] + point4Dend[2]) == 0){
                            Point3D newpoint = convertCubictoAxial(point4Dend);
                            Tile tile = map.getTile(newpoint);
                            if(tile != null && !point.equals(newpoint)) {
                                tempTiles.add(tile);
                            }
                        }
                    }
                }
            }
            resultTiles.add(tempTiles);
        }
        return resultTiles;
    }

    public static ArrayList<Tile> getTilesinPlane(Point3D point, int range, Map map){

        double[] point4Dstart = convertAxialtoCuubic(point);
        double[] point4Dend = new double[4];

        ArrayList<Tile> resultTiles = new ArrayList<>();
        for(int i = -range; i <= range; i++) {
            for (int j = -range; j <= range; j++) {
                for (int k = -range; k <= range; k++) {
                    point4Dend[0] = point4Dstart[0] - i;
                    point4Dend[1] = point4Dstart[1] - j;
                    point4Dend[2] = point4Dstart[2] - k;
                    point4Dend[3] = point.getZ();
                    if((point4Dend[0] + point4Dend[1] + point4Dend[2]) == 0){
                        Point3D newpoint = convertCubictoAxial(point4Dend);
                        Tile tile = map.getTile(newpoint);
                        if(tile != null && !point.equals(newpoint)) {
                            resultTiles.add(tile);
                        }
                    }
                }
            }
        }
        return resultTiles;
    }

    public static ArrayList<Tile> getTilesinPlane(Point3D point, int range, HashMap<Point3D,Tile> map){

        double[] point4Dstart = convertAxialtoCuubic(point);
        double[] point4Dend = new double[4];

        ArrayList<Tile> resultTiles = new ArrayList<>();
        for(int i = -range; i <= range; i++) {
            for (int j = -range; j <= range; j++) {
                for (int k = -range; k <= range; k++) {
                    point4Dend[0] = point4Dstart[0] - i;
                    point4Dend[1] = point4Dstart[1] - j;
                    point4Dend[2] = point4Dstart[2] - k;
                    point4Dend[3] = point.getZ();
                    if((point4Dend[0] + point4Dend[1] + point4Dend[2]) == 0){
                        Point3D newpoint = convertCubictoAxial(point4Dend);
                        Tile tile = map.get(newpoint);
                        if(tile != null) {
                            resultTiles.add(tile);
                        }
                    }
                }
            }
        }
        return resultTiles;
    }

    public static ArrayList<ArrayList<Tile>> getTilesInAngularPlane(Point3D point, int range, Map map, Direction direction) {
        Point3D pt = direction.getPointAdjacentTo(point);
        Direction leftDir = rotateEnum(4, direction);
        Direction rightDir = rotateEnum(2, direction);
        ArrayList<ArrayList<Tile>> resultTiles = new ArrayList<>();

        for (int i = 1; i < range; i++) {

            ArrayList<Tile> tempTiles = new ArrayList<>();
            tempTiles.add(map.getTile(pt));

            //set up for expansion on both sides
            Point3D leftSide = pt; //curr point + 4
            Point3D rightSide = pt; //curr point + 2

            //if even then expand out i/2 in both directions
            //if odd expand out i/2 (truncated) in both directions
            for (int j = 0; j < i/2; j++) {
                leftSide = leftDir.getPointAdjacentTo(leftSide);
                rightSide = rightDir.getPointAdjacentTo(rightSide);
                tempTiles.add(map.getTile(leftSide));
                tempTiles.add(map.getTile(rightSide));
            }

            //move to the next radius
            point = direction.getPointAdjacentTo(point);
            resultTiles.add(tempTiles);
        }
        return resultTiles;
    }

    public static ArrayList<ArrayList<Tile>> getTilesConicalTiles(Point3D point, int range, Map map, Direction direction) {
        Point3D pt = direction.getPointAdjacentTo(point);
        Direction leftDir = rotateEnum(4, direction);
        Direction rightDir = rotateEnum(2, direction);
        ArrayList<ArrayList<Tile>> resultTiles = new ArrayList<>();

        for (int i = 1; i < range; i++) {

            ArrayList<Tile> tempTiles = new ArrayList<>();
            tempTiles.add(map.getTile(pt));

            //set up for expansion on both sides
            Point3D leftSide = pt; //curr point + 4
            Point3D rightSide = pt; //curr point + 2

            //if even then expand out i/2 in both directions
            //if odd expand out i/2 (truncated) in both directions
            for (int j = 0; j < i/2; j++) {
                leftSide = leftDir.getPointAdjacentTo(leftSide);
                Point3D upLeft = leftSide;
                for (int k = 0; k < i/2; k++) {
                    upLeft = upLeft.add(0,0,1);
                    tempTiles.add(map.getTile(upLeft));
                }
                rightSide = rightDir.getPointAdjacentTo(rightSide);
                Point3D upRight = rightSide;
                for (int k = 0; k < i/2; k++) {
                    upRight = upRight.add(0,0,1);
                    tempTiles.add(map.getTile(upRight));
                }
                tempTiles.add(map.getTile(leftSide));
                tempTiles.add(map.getTile(rightSide));
            }

            //move to the next radius
            point = direction.getPointAdjacentTo(point);
            resultTiles.add(tempTiles);
        }
        return resultTiles;
    }

    public static ArrayList<Point3D> getConicalPoints(Point3D point, int range, Map map, Direction direction) {
        Point3D pt = direction.getPointAdjacentTo(point);
        Direction leftDir = rotateEnum(4, direction);
        Direction rightDir = rotateEnum(2, direction);
        ArrayList<Point3D> resultPoints = new ArrayList<>();

        for (int i = 1; i < range; i++) {

            resultPoints.add(pt);

            //set up for expansion on both sides
            Point3D leftSide = pt; //curr point + 4
            Point3D rightSide = pt; //curr point + 2

            //if even then expand out i/2 in both directions
            //if odd expand out i/2 (truncated) in both directions
            for (int j = 0; j < i/2; j++) {
                leftSide = leftDir.getPointAdjacentTo(leftSide);
                Point3D upLeft = leftSide;
                for (int k = 0; k < i/2; k++) {
                    upLeft = upLeft.add(0,0,1);
                    resultPoints.add(upLeft);
                }
                rightSide = rightDir.getPointAdjacentTo(rightSide);
                Point3D upRight = rightSide;
                for (int k = 0; k < i/2; k++) {
                    upRight = upRight.add(0,0,1);
                    resultPoints.add(upRight);
                }
                resultPoints.add(leftSide);
                resultPoints.add(rightSide);
            }

            //move to the next radius
            pt = direction.getPointAdjacentTo(pt);
        }
        return resultPoints;
    }

    public static ArrayList<ArrayList<Tile>> getLinearTilesInPlane(Point3D point, int range, Map map, Direction direction) {
        ArrayList<ArrayList<Tile>> resultTiles = new ArrayList<>();
        Point3D nextPoint = point;
        for (int i = 0; i < range; i++) {
            ArrayList<Tile> tempTiles = new ArrayList<>();
            tempTiles.add(map.getTile(nextPoint));
            nextPoint = direction.getPointAdjacentTo(nextPoint);
            resultTiles.add(tempTiles);
        }
        return resultTiles;
    }

    //rotate the enum given an integer
    private static Direction rotateEnum(int i, Direction direction) {
        Direction d = direction.values()[(direction.ordinal() + i) % 5];
        return d;
    }

    public static ArrayList<ArrayList<Tile>> getTilesinPrism(Point3D point, int rangeofRadius, int rangeofColumn, Map map){

        ArrayList<ArrayList<Tile>> tilesInRange = new ArrayList<>();

        //upper half of prism
        for(int i = 0; i <= rangeofColumn; i++){
             tilesInRange.add(getTilesinPlane(point.add(0,0,i),rangeofRadius,map));
        }

        //lower half of prism
        for(int i = -1; i <= -rangeofColumn; i--){
            tilesInRange.add(getTilesinPlane(point.add(0,0,i),rangeofRadius,map));

        }

        return tilesInRange;

    }

    public static ArrayList<Tile> getTilesinPrism(Point3D point, int rangeofRadius, HashMap<Point3D,Tile> map){

        ArrayList<Tile> tilesInRange = new ArrayList<>();


        //upper half of sphere
        for(int i = 0; i < Constants.COLUMN_HEIGHT; i++){
            ArrayList<Tile> tilesInPlane = getTilesinPlane(new Point3D(point.getX(),point.getY(),i),rangeofRadius,map);
            for(Tile tile: tilesInPlane) {
                tilesInRange.add(tile);
            }
        }
        return tilesInRange;

    }

    public static ArrayList<ArrayList<Tile>> getTilesinSphere(Point3D point, int range, Map map){

        ArrayList<ArrayList<Tile>> tilesInRange = new ArrayList<>();

        //upper half of sphere
        for(int i = -range, j = 0; i < 0; i++, j++){
            tilesInRange.add(getTilesinPlane(point.add(0,0,i),j,map));

        }
        //lower half of sphere
        for(int i = 0, j = range; i <= range; i++, j--){
             tilesInRange.add(getTilesinPlane(point.add(0,0,i),j,map));

        }
        return tilesInRange;
    }

    public static double[] convertAxialtoCuubic(Point3D axialPoint){

         double[] cubicPoint = new double[4];
        cubicPoint[0] = axialPoint.getX();
        cubicPoint[1] = -axialPoint.getY() - axialPoint.getX();
        cubicPoint[2] = axialPoint.getY();
        cubicPoint[3] = axialPoint.getZ();
        return cubicPoint;
    }

    public static Point3D convertCubictoAxial(double[] cubicPoint){
        return new Point3D(cubicPoint[0],cubicPoint[2],cubicPoint[3]);
    }

    public static double distanceBetweenPointsSamePlane(double a[], double b[]){
        double [] resultPoint = new double [4];
        double dx = Math.abs(a[0] - b[0]);
        double dy = Math.abs(a[1] - b[1]);
        double dz = Math.abs(a[2] - b[2]);
        return (dx + dy + dz)/2;
    }

    public static HashMap<Point3D, Tile> getTilesOnScreen(Point3D point, HashMap<Point3D,Tile> map) {
        HashMap<Point3D, Tile> tilesInRange = new HashMap<>();
        int screenWidth = Constants.SCREEN_WIDTH/Constants.TILE_WIDTH/2 + 2;
        int screenHeight = Constants.SCREEN_HEIGHT/Constants.TILE_HEIGHT/2 + 2;

        for(int i = -screenWidth; i <= screenWidth; i++){
            for(int j = - screenHeight; j <= screenHeight; j++){
                for(int k = 0; k < 10; k++) {
                    Point3D keyPoint = new Point3D(point.getX()+ i, point.getY() + j, k);
                    tilesInRange.put(keyPoint, map.get(keyPoint));
                }
            }
        }
        return tilesInRange;


    }

}
