/*# MUST BE CREATED IN ORDER FOR ALL FRAMES TO FUNCTION PROPERLY.
 * DOING THESE ACTIONS WITH A COMMON IMAGE AND IMAGEICON IN GAMEIMAGE RESULTS IN 
 * A STACK OVERFLOW EXCEPTION.#*/ 

 import java.util.ArrayList;
 import java.awt.*;

 public interface StateProtocol
    {
    /**
     * @return an ArrayList of Image objects
     */   
       
    public ArrayList< Image > getStates(); 
    
    /**
     * @return an int that holds the size of the Image ArrayList
     */
    
    public int getStateValue();
    
    /**
     * @return a Rectangle object that contains the bounds of the object
     *         implementing this method
     */
    
    public Rectangle getObjectRectangle();
            
    } //end StateProtocol