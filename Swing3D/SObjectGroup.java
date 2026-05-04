package Swing3D;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

// TODO: FOV causes walls that have negative normal vector to be positive, thus should be dipsplayed
// temporary fix: increase E value

/**
 * This type provides optimalization in case we want to display large amount of 3D objects that will have the same center of rotation
 */

public class SObjectGroup<T extends SObject> {

    /**
     * 
     * @param center center of rotation of all Objects
     */
    public SObjectGroup(int[] center){
        this.space_matrix = new SPoint[]{new SPoint(1, 0, 0),new SPoint(0, 1, 0),new SPoint(0, 0, 1)};
        this.center = center;
    }
    /**
     *  Object group is one 3D space consisting of 3D cuboids or other 3D shapes 
     * @param obj space that consists of the 3D objects
     */
    public void setSpace(T[][][] obj){
        this.object_space = obj;
        x_range = new int[]{0,obj.length};
        y_range = new int[]{0,obj[0].length};
        z_range = new int[]{0,obj[0][0].length};
        buildStack();
    }

    /**
     * Since objects need to be diplayed in a way, they overlap eachother correctly, they are returned in an sorted List
     * @return List of walls that will be displayed given instant
     */
    public ArrayList<SRectangle> getSortedObjects(){ 
        ArrayList<SRectangle> filtered_stack = filterStack();
        Collections.sort(filtered_stack, (a,b) -> { 
            
            if(a.getZ() == b.getZ()){
                if (a.getPoints()[1].getZ() > b.getPoints()[1].getZ()) return 1;
                if (a.getPoints()[1].getZ() < b.getPoints()[1].getZ()) return -1;
                return 0;

            }
            return (a.getZ() > b.getZ()) ? 1 : -1;
         } );
        
        return filtered_stack;
    }

    /**
     * 
     * @return the space of the objects
     */
    public T[][][] getObjectSpace(){ return this.object_space;}

    /**
     * 
     * @param yes_no if true, then this object group will al so diplay translucent blocks
     */
    public void displayTranslucent(boolean yes_no){
        this.display_translucent = yes_no;
        buildStack();
    }
    
    
    /**
     *  rotation around X axis
     * @param angle angle by which this group will be rotated
     */
    public void rotateX(double angle){
        SObjectRotator.setCenter(center[0], center[1], center[2]);
        for(int x = 0; x != object_space.length; ++x){
            for(int y = 0; y != object_space[0].length; ++y){
                for(int z = 0 ;z != object_space[0][0].length; ++z){
                    SObjectRotator.rotateObjX(object_space[x][y][z],angle);
                }
            }
        }
        rotateSpaceX(angle);
    }

    /**
     *  rotation around Y axis
     * @param angle angle by which this group will be rotated
     */
    public void rotateY(double angle){
        SObjectRotator.setCenter(center[0], center[1], center[2]);
        for(int x = 0; x != object_space.length; ++x){
            for(int y = 0; y != object_space[0].length; ++y){
                for(int z = 0 ;z != object_space[0][0].length; ++z){
                    SObjectRotator.rotateObjY(object_space[x][y][z],angle);
                }
            }
        }
        rotateSpaceY(angle);
    }

    /**
     *  rotation around Z axis
     * @param angle angle by which this group will be rotated
     */
    public void rotateZ(double angle){
        SObjectRotator.setCenter(center[0], center[1], center[2]);
        for(int x = 0; x != object_space.length; ++x){
            for(int y = 0; y != object_space[0].length; ++y){
                for(int z = 0 ;z != object_space[0][0].length; ++z){
                    SObjectRotator.rotateObjZ(object_space[x][y][z],angle);
                }
            }
        }
        rotateSpaceZ(angle);
    }

    /**
     * 
     * @param distance distance by which this group will be zoomed
     */
    public void zoom(double distance){
        for(int x = 0; x != object_space.length; ++x){
            for(int y = 0; y != object_space[0].length; ++y){
                for(int z = 0 ;z != object_space[0][0].length; ++z){
                    SObjectRotator.zoom(object_space[x][y][z],distance);
                }
            }
        }
        center[2] += distance;
    }

    private void rotateSpaceX(double angle){
       
        SRotator.setCenter(0, 0, 0);
        for(SPoint p : space_matrix){
            SRotator.rotatePointX(p, angle);
        }
    }
    
    private void rotateSpaceY(double angle){
       
        SRotator.setCenter(0, 0, 0);
        for(SPoint p : space_matrix){
            SRotator.rotatePointY(p, angle);
        }
    }
    
    private void rotateSpaceZ(double angle){
      
        SRotator.setCenter(0, 0, 0);
        for(SPoint p : space_matrix){
            SRotator.rotatePointZ(p, angle);
        }
    }

    // determines which wall will the viewer see and only those will be displayed
    private HashSet<Integer> getDisplayedWalls(){
        final double E = 0.3;

        HashSet<Integer> walls = new HashSet<Integer>();
        if(space_matrix[0].getZ() > E){
            walls.add(SCuboid.RIGHT);
        }  
        else if (space_matrix[0].getZ() < -E){ // x coord
            walls.add(SCuboid.LEFT); 
        }
        else{ 
            walls.add(SCuboid.RIGHT);
            walls.add(SCuboid.LEFT); 
        }

        if(space_matrix[1].getZ() > E) { // y coord
            walls.add(SCuboid.TOP); 
        }
        else if(space_matrix[1].getZ() < -E){
            walls.add(SCuboid.BOT); 
        } 
        else{
            walls.add(SCuboid.TOP); 
            walls.add(SCuboid.BOT); 
        }

        if(space_matrix[2].getZ() > E){ // z coord
             walls.add(SCuboid.FRONT); 
        }
        else if(space_matrix[2].getZ() < -E){
                walls.add(SCuboid.BACK); 
        }
        else{
            walls.add(SCuboid.FRONT); 
            walls.add(SCuboid.BACK); 
        }

        return walls;
    }
    

    // build stack of cubes that are to be displayed - are not hindered by other walls
    private synchronized void buildStack(){
        object_stack = new ArrayList<SRectangle>((x_range[1] - x_range[0]) * (y_range[1] - y_range[1])  * (z_range[1] - z_range[1]));
    
        for(int x = x_range[0]; x != x_range[1]; ++x){
            for(int y = y_range[0]; y != y_range[1]; ++y){
                for(int z = z_range[0]; z != z_range[1]; ++z){
                    T obj = object_space[x][y][z];
                    if(obj.isTranslucent() && !display_translucent) continue;
                
                    for(int[] move : moves){
                        int new_x  = x + move[1];
                        int new_y  = y + move[2];
                        int new_z  = z + move[3];

                        if(validMove(new_x, new_y,new_z)){ // if the neighbor either does not exist or it is translucent we add the corresponding wall
                            if(object_space[new_x][new_y][new_z].isTranslucent()){
                                object_stack.add(obj.getWall(move[0]));
            
                            }
                        }else{
                            object_stack.add(obj.getWall(move[0]));
                    
                        }
                    }
                    
                }
            }
        }
    }

    private boolean validMove(int x, int y, int z){
        if(x < x_range[0] || x >= x_range[1]) return false;
        if(y < y_range[0] || y >= y_range[1]) return false;
        if(z < z_range[0] || z >= z_range[1]) return false;
        return true;
    }

    // removes walls that are not in the view of the camera
    private synchronized ArrayList<SRectangle> filterStack(){ 
        HashSet<Integer> d_walls = getDisplayedWalls();
        ArrayList<SRectangle> filtered_stack = new ArrayList<SRectangle>(object_stack.size());
        for(SRectangle r : object_stack){
            if(d_walls.contains(r.getWallID())){
                filtered_stack.add(r);
            }
        }
        return filtered_stack;
    }

    private boolean display_translucent = true;
    private SPoint[] space_matrix;
    private ArrayList<SRectangle> object_stack;
    private T[][][] object_space;
    private int[] center;
    private int[] x_range, y_range, z_range;
    private static int[][] moves = { {SCuboid.FRONT, 0,0,1}, {SCuboid.BACK, 0,0,-1},{SCuboid.TOP,0,1,0}, {SCuboid.BOT,0,-1,0}, {SCuboid.RIGHT,1,0,0}, {SCuboid.LEFT,-1,0,0} };
    
}
