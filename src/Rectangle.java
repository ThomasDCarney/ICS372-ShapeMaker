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
 * This is a concrete class used to represent Rectangles, based on the 
 * abstract Figure.
 * 
 * @author Thomas Carney
 * @version 1.0
 * @since 09/07/2015
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Rectangle extends Figure {
    
    private Point topLeft;  // Holds the top left corners x and y values.
    private int width;
    private int height;
    
    
    /** Rectangle(Point topLeft, Point bottonRight, Color c)
     * This will create a new Rectangle figure defined by the x, y coordinates its 
     * top left corner, width, height and color.
     * 
     * @param topLeft - A point with the x, y coordinates of the top left corner.
     * 
     * @param bottonRight - A point with the x, y coordinates of the bottom right corner.
     * 
     * @param c - The color to fill the figure in with.
     */
    public Rectangle(Point topLeft, Point bottomRight, Color c) {
        
        // Bottom right point is simply used to calculate width and height
        
        this.topLeft = topLeft;
        width = bottomRight.x - topLeft.x;
        height = bottomRight.y - topLeft.y;
        color = c;
        
    }// end Rectangle constructor
    
    
    /** draw(Graphics graphics)
     * This method will draw the rectangle onto the desired "canvas".
     * 
     * @param graphics - a Graphics object belonging to a GUI "canvas" component.
     */
    @Override
    public void draw(Graphics graphics) {
        
        super.draw(graphics);
        graphics.fillRect(topLeft.x, topLeft.y, width, height);
        
    }// end draw
    
    
    /** toString()
     * This method will return a String representation of the Rectangle.
     */
    @Override
    public String toString() {
        
        String c;
        
        if(color == Color.RED) {
            
            c = "Red";
            
        } else if(color == Color.GREEN) {
            
            c = "Green";
            
        } else {
            
            c = "Blue";
            
        }
        
        return "  Rectangle [ x=" + topLeft.x + ", y=" + topLeft.y + ", width=" + width + ", height=" + height + ", color=" + c + " ]";
        
    }// end toString
    
}// end Rectangle class