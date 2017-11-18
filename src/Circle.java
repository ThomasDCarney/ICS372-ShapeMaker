/**
 * Class: ICS 372 - Object Oriented Design and Implementation <br>
 * Instructor: Habtamu Bogale <br>
 * Name: Thomas Carney <br>
 * Description: Program #2, used to learn/illustrate java's serialization
 *              features to store/retrieve objects, binary io, etc. We are to 
 *              program an interface to create/draw figure/shape objects and 
 *              save/restore them upon program close and start.<br>
 * Due: 09/11/2015 <br><br>
 * 
 * This is a concrete class used to represent Circles, based on the 
 * abstract Figure.
 * 
 * @author Thomas Carney
 * @version 1.0
 * @since 09/07/2015
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Circle extends Figure {
    
    private Point center;
    private int radius;
    
    
    /** Circle(Point point1, Point point2, Color c)
     * This will create a new Rectangle figure defined by the x, y coordinates its 
     * top left corner, width, height and color.
     * 
     * @param topLeft - A point with the x, y coordinates of the top left corner.
     * 
     * @param bottonRight - A point with the x, y coordinates of the bottom right corner.
     * 
     * @param c - The color to fill the figure in with.
     */
    public Circle(Point point1, Point point2, Color c) {
        
        radius = (int)point1.distance(point2);
        center = point1;
        color = c;
        
    }// end Circle constructor
    
    
    /** draw(Graphics graphics)
     * This method will draw the circle onto the desired "canvas".
     * 
     * @param graphics - a Graphics object belonging to a GUI "canvas" component.
     */
    @Override
    public void draw(Graphics graphics) {
        
        super.draw(graphics);
        graphics.fillOval(center.x - radius, center.y - radius, radius * 2, radius * 2);
        
    }// end draw
    
    
    /** toString()
     * This method will return a String representation of the Circle.
     */
    @Override
    public String toString() {
        
        String c;
        
        if(color.equals(Color.RED)) {
            
            c = "Red";
            
        } else if(color.equals(Color.GREEN)) {
            
            c = "Green";
            
        } else {
            
            c = "Blue";
            
        }
        
        return "  Circle [ x=" + center.x + ", y=" + center.y + ", radius=" + radius + ", color=" + c + " ]";
        
    }// end toString
    
}// end Circle class