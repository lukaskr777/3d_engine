package Swing3D;


/**
 *  rotator of 3D points/vectors
 */
public class SRotator {
    
    /**
     * this method sets the center point around which, the rotation will happen
     * @param x 
     * @param y
     * @param z
     */
    public static void setCenter(int x, int y, int z){
        center[0] = x;
        center[1] = y;
        center[2] = z;

    }

    /**
     * besides rotations, this class also zooms an object
     * @param point point to zoom
     * @param distance amount of z points to zoom
     */
    public static void zoom(SPoint point, double distance){    
        point.setZ(point.getZ()+ distance);
    }

    /**
     * rotates point around X axis
     * @param point
     * @param radian_angle an angle by which we want to rotate
     */
    public static void rotatePointX(SPoint point, double radian_angle){
        angle = radian_angle;
        leftRotateAroundX(point);

    }

    /**
     * rotates point around Y axis
     * @param point
     * @param radian_angle an angle by which we want to rotate
     */
    public static void rotatePointY(SPoint point, double radian_angle){
        angle = radian_angle;
        leftRotateAroundY(point);
    }

    /**
     * rotates point around Z axis
     * @param point
     * @param radian_angle an angle by which we want to rotate
     */
    public static void rotatePointZ(SPoint point, double radian_angle){
        angle = radian_angle;
        leftRotateAroundZ(point);
    }


    private static void leftRotateAroundX(SPoint point){

        double y = point.getY() - center[1]; 
        double z = point.getZ() - center[2];

        //double diagonal = Math.sqrt(y*y + z*z); // to get the diegonal line of the rotation circle

        double new_y = y * Math.cos(angle) - z *Math.sin(angle);
        double new_z = y * Math.sin(angle) + z *Math.cos(angle);

        point.setY(new_y + center[1]);
        point.setZ(new_z + center[2]);

    }
    private static void leftRotateAroundY(SPoint point){

        double x = point.getX() - center[0]; 
        double z = point.getZ() - center[2];

        //double diagonal = Math.sqrt(y*y + z*z); // to get the diegonal line of the rotation circle

        double new_x = x * Math.cos(angle) - z *Math.sin(angle);
        double new_z = x *Math.sin(angle) + z * Math.cos(angle);

        point.setX(new_x + center[0]);
        point.setZ(new_z + center[2]);

    }
    private static void leftRotateAroundZ(SPoint point){

        double x = point.getX() - center[0]; 
        double y = point.getY() - center[1];

        //double diagonal = Math.sqrt(y*y + z*z); // to get the diegonal line of the rotation circle

        double new_x = x * Math.cos(angle) - y *Math.sin(angle);
        double new_y = x *Math.sin(angle) + y * Math.cos(angle);

        point.setX(new_x + center[0]);
        point.setY(new_y + center[1]);

    }

    protected static int[] center = new int[3];
    protected static double angle;
}
