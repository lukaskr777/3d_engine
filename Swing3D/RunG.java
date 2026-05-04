package Swing3D;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.*;
import java.awt.Graphics;




public class RunG extends JPanel{
    
    

    public RunG(){
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500,500);
        f.add(this);
        f.setVisible(true);
        
    }

    public static void print(Object obj){
        System.out.println(obj);
    }

    public static void main(String[] args){

        RunG win = new RunG();
        SObjectRotator.setCenter(250,250,-50);
        class MKL implements KeyListener{

            public void keyPressed(KeyEvent e){
                if (e.getKeyCode() == KeyEvent.VK_UP){
       
                    SObjectRotator.rotateObjX(obj, Math.PI/32);
                    win.repaint();
                }
                else if(e.getKeyCode() == KeyEvent.VK_LEFT){
                 
                    SObjectRotator.rotateObjY(obj, Math.PI/32);
                    win.repaint();
                }
                else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    SObjectRotator.rotateObjY(obj, -Math.PI/32);
                    win.repaint();
                }
                else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                 
                    SObjectRotator.rotateObjX(obj, -Math.PI/32);
                    win.repaint();
                }
                else if(e.getKeyCode() == KeyEvent.VK_R){
                    SObjectRotator.rotateObjZ(obj, Math.PI/32);
                    win.repaint();
                }
                else if(e.getKeyCode() == KeyEvent.VK_Z){
                    SObjectRotator.zoom(obj, 25);
                    win.repaint();
                }
                else if(e.getKeyCode() == KeyEvent.VK_X){
                    SObjectRotator.zoom(obj, -25);
                    win.repaint();
                }
                
                
            }
            public void keyReleased(KeyEvent e){}
            public void keyTyped(KeyEvent e){}
        }
        win.addKeyListener(new MKL());
        win.requestFocus();

     
    }
    @Override
    public void paintComponent(Graphics g ){
        Graphics2D g2d = (Graphics2D) g;
        g2d.clearRect(0, 0, 500, 500);
        new Graphics3D(new int[]{250,250,750},g2d).draw(cub);
    } 

    public static SRectangle rec=  new SRectangle(new SPoint(200,200,0),new SPoint(200,300,0),new SPoint(300,300,0),new SPoint(300, 200, 0));
    public static SCuboid cub = new SCuboid(200,200,-100, 100,100,100); 
    public static SObject obj = cub;
}