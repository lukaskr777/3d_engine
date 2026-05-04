package Swing3D;

/**
 * Child of SRotator,
 * rotates more complicated 3D shapes
 */
public class SObjectRotator extends SRotator {
    
    /**
     * rotates object around X axis
     * @param obj the object to rotate
     * @param radian_angle an angle by which we want to rotate 
     */
    public static void rotateObjX(SObject obj, double radian_angle){

        SPoint[] points = obj.getPoints();
        for(int i = 0; i != points.length; ++i){
            rotatePointX(points[i], radian_angle);
        }
    }
    
    /**
     * rotates object around Y axis
     * @param obj the object to rotate
     * @param radian_angle an angle by which we want to rotate 
     */
    public static void rotateObjY(SObject obj, double radian_angle){

        SPoint[] points = obj.getPoints();
        for(int i = 0; i != points.length; ++i){
            rotatePointY(points[i], radian_angle);
        } 
    }
    
    /**
     * rotates object around Z axis
     * @param obj the object to rotate
     * @param radian_angle an angle by which we want to rotate 
     */
    public static void rotateObjZ(SObject obj, double radian_angle){

        SPoint[] points = obj.getPoints();
        for(int i = 0; i != points.length; ++i){
            rotatePointZ(points[i], radian_angle);
        }
    }

    /**
     * zooms the object
     * @param obj the object to be zoomed
     * @param distance zoom by a chosen distance of z points
     */
    public static void zoom(SObject obj ,double distance){
        SPoint[] points = obj.getPoints();
        for(SPoint s : points){
            zoom(s,distance);
        }
    }

    /**
     * the object may be zoomed but the rotation point stays the same if do not change center of rotation for which is this method
     * @param distance move the center point of rotation by a chosen distance
     */
    public static void zoomRotation(double distance){
        center[2] += distance; 
    }

}
