package Swing3D;

import java.awt.Color;


/**
 * 
 * Interface of every 3D object,
 * Defines methods that every 3D type has to have
 * 
 */

public interface SObject { 
    
    /**
     * 
     * @return All points in 3D space that define the object
     */
    public SPoint[] getPoints();

    /**
     * 
     * @param c sets the color of the object
     */
    public void setColor(Color c);
    
    public Color getColor();

    /**
     * 
     * @return returns Z coordinate of the object, useful for displaying several objects in the right order
     */
    public double getZ();

    /**
     * Every object is either a wall/rectangle or made out of rectangles
     * @param id this is the id of the wall, varies based on the object we interact with
     * @return returns the wall/rectangle that coresponds to that wall id
     */
    public SRectangle getWall(int id);

    /**
     * 
     * @return returns true if the object is translucent
     */
    public boolean isTranslucent();
    /**
     * 
     * @param t sets the object to translucent or not
    */
    public void setTranslucent(boolean t);

}
