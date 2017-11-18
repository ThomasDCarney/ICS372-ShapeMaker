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
 * This is a user interface with fairly straight forward means to create and
 * display circle and rectangle figures. The user can select from a set of colors, 
 * shapes and click in the left side panel to define size. The exit button will
 * save current figures prior to exit but hitting the X button will close the 
 * window without saving.
 * 
 * @author Thomas Carney
 * @version 1.0
 * @since 09/07/2015
 */

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class FigureFrame extends JFrame implements ActionListener {
    
    // To store figure data
    private ArrayList<Figure> figureArray;  
    
    // Create outer constructs
    private FigurePanel figurePanel = new FigurePanel();
    private JPanel buttonPanel = new JPanel();
    private JTextArea listArea = new JTextArea();
    private JScrollPane listPane = new JScrollPane(listArea, 
                                                   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
                                                   JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    
    // Create buttons for the button panel
    private JButton exitButton = new JButton("Exit");
    private JButton redButton = new JButton("Red");
    private JButton blueButton = new JButton("Blue");
    private JButton greenButton = new JButton("Green");
    private JButton circleButton = new JButton("Circle");
    private JButton rectangleButton = new JButton("Rectangle");
    
    // To specify type of figure to create
    public static enum Action { RECTANGLE, CIRCLE, EXIT };
    private Action action = Action.RECTANGLE;
    private Color color = Color.RED;
    
    private GregorianCalendar currentDate = new GregorianCalendar();
    private String formattedDate= currentDate.getTime().toString();
    
    
    /** FigureFrame(String frameName)
     * This will create a new FigureFrame which displays buttons, output dialog and most 
     * importantly the figures themselves.
     * 
     * @param frameName - A name for the windows title bar
     */
    public FigureFrame(String frameName) {
        
        super(frameName);
        this.setSize(1150, 400);
        this.setLayout(new GridLayout(1, 3));   // One row, three section
        
        initializeFigureArray();    // For storage
        initializeFigurePanel();    // Left hand side
        initializeButtonPanel();    // Center
        
        this.add(figurePanel);
        this.add(buttonPanel);
        this.add(listPane);

        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
    }// end FigureFrame constructor
    
    
    /** initializeFigureArray()
     * This method will attempt to retrieve figures from a previous session. If no data is
     * found then figureArray will be started from scratch.
     */
    private void initializeFigureArray() {
        
     // Restore previously saved data if any.
        
        try{
        
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("Figures.dat"));
            figureArray = (ArrayList<Figure>) input.readObject();
            updateListPane();
            input.close();
        
        } catch(FileNotFoundException e) {
            
            System.out.println("Stored data file not found.");
            
            // No stored data so start from scratch
            figureArray = new ArrayList<Figure>();
            
        } catch(IOException e) {
            
            System.out.println(e.getMessage());
            
        } catch (ClassNotFoundException e) {
            
            System.out.println(e.getMessage());
        }
        
        
        
    }// end initializeFigureArray
    
    
    /** initializeFigurePanel()
     * This method will setup the figure panel to accept mouse events and display figures.
     */
    private void initializeFigurePanel() {
        
        figurePanel.setBackground(Color.WHITE);
        figurePanel.addMouseListener(figurePanel);
        
    }// end initializeFigurePanel
    
    
    /** initializeButtonPanel()
     * This method will structure and initialize the central button panel.
     */
    private void initializeButtonPanel() {
        
        // Add listeners to the buttons
        exitButton.addActionListener(this);
        redButton.addActionListener(this);
        blueButton.addActionListener(this);
        greenButton.addActionListener(this);
        circleButton.addActionListener(this);
        rectangleButton.addActionListener(this);
        
        // We need two rows and three columns
        buttonPanel.setLayout(new GridLayout(2, 3));
        
        // Add buttons to the panel itself
        buttonPanel.add(redButton);
        buttonPanel.add(greenButton);
        buttonPanel.add(blueButton);
        buttonPanel.add(rectangleButton);
        buttonPanel.add(circleButton);
        buttonPanel.add(exitButton);
        
    }// end initializeButtonPanel

    
    /** actionPerformed(ActionEvent e)
     * This method defines what happens when each button is pushed.
     * 
     * @param e - An ActionEvent generated by the buttons.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource().equals(exitButton)) {
            
            System.out.println("Exit requested");
            
            // Save stored figures for another day.
            try {
                
                ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("Figures.dat"));
                output.writeObject(figureArray);
                output.close();
                System.exit(0);
                
            } catch (IOException ex) {

                System.out.println(ex.getMessage());
                
            }
            
        } else {
        
            if(e.getSource().equals(redButton)) {
            
                color = Color.RED;
                
            } else if(e.getSource().equals(blueButton)) {
                
                color = Color.BLUE;
                
            } else if(e.getSource().equals(greenButton)) {
                
                color = Color.GREEN;
                
            } else if(e.getSource().equals(circleButton)) {
                
                action = Action.CIRCLE;
                
            } else if(e.getSource().equals(rectangleButton)) {
                
                action = Action.RECTANGLE;
            
            }// end nested if-else block
            
        }// end outer if-else block
        
    }// end actionPerformed

    
    /** updateListPane()
     * This method will update the printed list of figures.
     */
    private void updateListPane() {
        
        String fullList = "";
        
        for(int i = 0 ; i < figureArray.size() ; i++) {
            
            fullList += figureArray.get(i) + "\n";
            
        }
        
        listArea.setText(fullList);
        
    }// end updateListPane
    

    /** This is an inside class of the FigureFrame, it extends JPanel and is where 
     * figures and date information will be drawn to the screen. This class also 
     * handles mouse events which are used to gather Point objects for creating 
     * figures.
     */
    private class FigurePanel extends JPanel implements MouseListener {

        private Point point1 = null;        // Stores the initial point
        private Boolean havePoint = false;  // Initial point stored yet?   

        
        /** paintComponent(Graphics g)
         * This method defines what is to be painted on the panel.
         */
        @Override
        protected void paintComponent(Graphics g) {
            
            super.paintComponent(g);

            // Need to traverse and draw each stored figure
            for(int i = 0 ; i < figureArray.size() ; i++) {
                
                figureArray.get(i).draw(g);
                    
            }
            
            g.setColor(Color.BLACK);
            g.drawString(formattedDate, 10, figurePanel.getHeight() - 10);
            
        }// end paintComponent
        
        
        /** mouseClicked(MouseEvent e)
         * This is used to extract the coordinates from mouse click events, 
         * create/store figures and initiate a redraw of the panel. 
         * 
         * @param e - An event generated by clicking a mouse in the panel.
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            
            System.out.println("Mouse Clicked " + e.getPoint());
            
            // This handles receiving an initial point.
            if(!havePoint) {
                
                System.out.println("First point");
                point1 = e.getPoint();
                havePoint = true;
                
            // The else clause handles a second mouse click, submitted point.
            } else {
                
                // Second click must be further right than first, if not then it the
                // new point will replace the stored one.
                if(point1.x > e.getX()) {
                   
                    System.out.println("Point is further left");
                    point1 = e.getPoint();
                    
                // Handles when a valid second point has been received.    
                } else {
                    
                    System.out.println("Second point");
                    
                    // This will create a new figure based on current settings.
                    switch(action) {
                    
                    case RECTANGLE:
                        figureArray.add(new Rectangle(point1, e.getPoint(), color));
                        break;
                        
                    case CIRCLE:
                        figureArray.add(new Circle(point1, e.getPoint(), color));
                        
                    }
                    
                    // Both points used, reset for next set
                    havePoint = false;
                    updateListPane();
                    
                    // This will actually draw the new figure on the panel.
                    this.repaint();
                    
                }// end nested if-else
                
            }// end outer if-else
            
        }// end mouseClicked

        
        /** Method is unused*/
        @Override
        public void mousePressed(MouseEvent e) {
            
            // unused method
            
        }// end mousePressed

        
        /** Method is unused*/
        @Override
        public void mouseReleased(MouseEvent e) {
            
            // unused method
            
        }// end mouseReleased

        
        /** Method is unused*/
        @Override
        public void mouseEntered(MouseEvent e) {

            // unused method
            
        }// end mouseEntered

        
        /** Method is unused*/
        @Override
        public void mouseExited(MouseEvent e) {

            // unused method
            
        }// end mouseExited
        
    }// end FigurePanel
    
}// end FigureFrame class

