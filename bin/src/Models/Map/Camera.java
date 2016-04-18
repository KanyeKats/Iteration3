package Models.Map;

import javafx.geometry.Point3D;

/**
 * Created by Aidan on 4/14/2016.
 */
public class Camera{

    Point3D location;

    public Camera(Point3D location){
        this.location = location;
    }

    public void move(Direction direction){
        for(int i = 0; i < 5; i ++) {
            location = direction.getPointAdjacentTo(location);
        }
    }

    public Point3D getLocation() {
        return location;
    }

    public void setLocation(Point3D location) {
        this.location = location;
    }
}
