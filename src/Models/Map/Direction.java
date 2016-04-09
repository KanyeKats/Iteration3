package Models.Map;

import javafx.geometry.Point3D;

/**
 * Created by johnkaufmann on 3/31/16.
 * TODO:
 */
public enum Direction {
    NORTH {
        public Point3D getPointAdjacentTo(Point3D point){
            Point3D p = new Point3D(point.getX(), point.getY(), point.getZ());
            return p.add(0, -1, 0);
        }
    },
    NORTH_EAST {
        public Point3D getPointAdjacentTo(Point3D point){
            Point3D p = new Point3D(point.getX(), point.getY(), point.getZ());
            return p.add(1, -1, 0);
        }
    },
    SOUTH_EAST {
        public Point3D getPointAdjacentTo(Point3D point){
            Point3D p = new Point3D(point.getX(), point.getY(), point.getZ());
            return p.add(1, 0, 0);
        }
    },
    SOUTH {
        public Point3D getPointAdjacentTo(Point3D point){
            Point3D p = new Point3D(point.getX(), point.getY(), point.getZ());
            return p.add(0, 1, 0);
        }
    },
    SOUTH_WEST {
        public Point3D getPointAdjacentTo(Point3D point){
            Point3D p = new Point3D(point.getX(), point.getY(), point.getZ());
            return p.add(-1, 1, 0);
        }
    },
    NORTH_WEST {
        public Point3D getPointAdjacentTo(Point3D point){
            Point3D p = new Point3D(point.getX(), point.getY(), point.getZ());
            return p.add(-1, 0, 0);
        }
    },
    UP {
        public Point3D getPointAdjacentTo(Point3D point){
            Point3D p = new Point3D(point.getX(), point.getY(), point.getZ());
            p = p.add(0, 0, 1);
            return p.getZ() < 10 ? p : null;
        }
    },
    DOWN {
        public Point3D getPointAdjacentTo(Point3D point){
            Point3D p = new Point3D(point.getX(), point.getY(), point.getZ());
            p = p.add(0, 0, -1);
            return p.getZ() >= 0 ? p : null;
        }
    };

    public abstract Point3D getPointAdjacentTo(Point3D point);
}
