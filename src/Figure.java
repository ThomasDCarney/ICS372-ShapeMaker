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
 * This is an abstract class to represent the "Figures" which are drawn to 
 * screen. This was provided to us so short of this javadoc, no changes have
 * been made.
 * 
 * @author Thomas Carney
 * @version 1.0
 * @since 09/07/2015
 */

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
	
/**
 * Serves as a type for all figures in the simple
 * drawing program. Some implementation might be added 
 * at a future date.
 * @author
 *
 */
public abstract class Figure implements Serializable {
	protected Color color;
	/**
	 * Draws the figure using the given Graphics parameter
	 * @param graphics the Graphics object for drawing the figure
	 */
	public void draw(Graphics graphics) {
	    
		graphics.setColor(color);
		
	}
}
