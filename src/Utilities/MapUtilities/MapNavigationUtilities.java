package Utilities.MapUtilities;

import Models.Entities.Entity;
import Models.Map.Direction;
import Models.Map.Map;
import Models.Map.Tile;
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



    public static ArrayList<Tile> getTilesinPlane(Point3D point, int range, Map map){

        double[] point4Dstart = convertAxialtoCuubic(point);
        double[] point4Dend = new double[4];

        ArrayList<Tile> tilesInRange = new ArrayList<>();
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
                        //System.out.println(newpoint.toString());
                        if(tile != null) {
                            tilesInRange.add(tile);
                        }
                    }
                }
            }
        }
        return tilesInRange;
    }

    public static ArrayList<Tile> getTilesinPlane(Point3D point, int range, HashMap<Point3D, Tile> map){

        double[] point4Dstart = convertAxialtoCuubic(point);
        double[] point4Dend = new double[4];

        ArrayList<Tile> tilesInRange = new ArrayList<>();
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
                            tilesInRange.add(tile);
                        }
                    }
                }
            }
        }
        return tilesInRange;
    }

    public static ArrayList<Tile> getTilesinPrism(Point3D point, int rangeofRadius, int rangeofColumn, Map map){

        ArrayList<Tile> tilesInRange = new ArrayList<>();

        //upper half of sphere
        for(int i = -rangeofColumn; i < 0; i++){
            ArrayList<Tile> tilesInPlane = getTilesinPlane(point.add(0,0,i),rangeofRadius,map);
            for(Tile tile: tilesInPlane) {
                tilesInRange.add(tile);
            }
        }
        //lower half of sphere
        for(int i = 0; i <= rangeofColumn; i++){
            ArrayList<Tile> tilesInPlane = getTilesinPlane(point.add(0,0,i),rangeofRadius,map);
            for(Tile tile: tilesInPlane) {
                tilesInRange.add(tile);
            }
        }
        return tilesInRange;

    }

    public static ArrayList<Tile> getTilesinPrism(Point3D point, int rangeofRadius, int rangeofColumn, HashMap<Point3D,Tile> map){

        ArrayList<Tile> tilesInRange = new ArrayList<>();

        //upper half of sphere
        for(int i = -rangeofColumn; i < 0; i++){
            ArrayList<Tile> tilesInPlane = getTilesinPlane(point.add(0,0,i),rangeofRadius,map);
            for(Tile tile: tilesInPlane) {
                tilesInRange.add(tile);
            }
        }
        //lower half of sphere
        for(int i = 0; i <= rangeofColumn; i++){
            ArrayList<Tile> tilesInPlane = getTilesinPlane(point.add(0,0,i),rangeofRadius,map);
            for(Tile tile: tilesInPlane) {
                tilesInRange.add(tile);
            }
        }
        return tilesInRange;

    }

    public static ArrayList<Tile> getTilesinSphere(Point3D point, int range, Map map){

        ArrayList<Tile> tilesInRange = new ArrayList<>();

        //upper half of sphere
        for(int i = -range, j = 0; i < 0; i++, j++){
            ArrayList<Tile> tilesInPlane = getTilesinPlane(point.add(0,0,i),j,map);
            for(Tile tile: tilesInPlane) {
                    tilesInRange.add(tile);
            }
        }
        //lower half of sphere
        for(int i = 0, j = range; i <= range; i++, j--){
            ArrayList<Tile> tilesInPlane = getTilesinPlane(point.add(0,0,i),j,map);
            for(Tile tile: tilesInPlane) {
                tilesInRange.add(tile);
            }
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



}
