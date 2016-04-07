package Models.Map;

import Models.Entities.Entity;
import javafx.geometry.Point3D;

/**
 * Created by Bradley on 4/6/2016.
 */
public class NavigationMediator {

    // TODO: Might not use this.
    private static NavigationMediator navigationMediator;
    private Map map;

    private NavigationMediator(Map map){
        this.map = map;
    }

    public static NavigationMediator getInstance(){ return navigationMediator; }

    public static void init(Map map){
        navigationMediator = new NavigationMediator(map);
    }

    // This function determines if an entity can move onto a particular location.
    public void moveEntity(Entity entity, Point3D point){

        // Determine if the point is in range of the map.
    }

    public void moveEntity(Entity entity, Direction direction){

        Point3D point = direction.getPointAdjacentTo(entity.getLocation());
        moveEntity(entity, point);
    }
}
