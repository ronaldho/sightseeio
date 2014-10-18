 import java.util.ArrayList;
 import java.awt.Image;
 import javax.swing.ImageIcon;

 public abstract class GameImage implements StateProtocol
   {
   /*# Bug Warning: creates a Stack Overflow Exception if the following two ArrayLists are used #*/
   //private ArrayList< Image > frame;
   //private ArrayList< ImageIcon > frameIcon;
   
   private Image normalPosition;
   private ImageIcon normalPositionIcon;
   private Image normalPositionLeft;
   private ImageIcon normalPositionLeftIcon; 
   
   private int xPosition;
   private int yPosition;
   private int height;
   
   public GameImage()
      {
      this.setX( 0 );
      this.setY( 0 );
      } //end GameImage()
      
   public GameImage( int objectX, int objectY )
      {
      this.setX( objectX );
      this.setY( objectY );
      } //end GameImage()
    
   /** 
    * @param frames  Variable length argument list of strings that resemble each frame of the character
     * 
     * @return A String array that represents the classpaths for each frame
     */
      
   //##create each frame of character; place in array 
   final public String[] createStates( String... frames )
      {
      return frames;
      } //end createStates()
      
  
   /**
    * @param states  a String array of classpaths that will be loaded into an ImageIcon ArrayList
    * 
    * @param destination  this ArrayList will hold the game Images required for use in the game
    * 
    * @param iconDestination  holds the ImageIcons that are required to create an Image
    */
    
   //###loads images from the game folder and places them in arrays ready for use in the game###
   
   final public void loadStates( String[] states, ArrayList< Image > destination, ArrayList< ImageIcon > iconDestination  ) 
       {
       for( int index = 0; index < states.length; index++ )
          {
          iconDestination.add( new ImageIcon( this.getClass().getResource( states[ index ] ) ) );
          destination.add( iconDestination.get( index ).getImage() );
          } //end for
       } //end states
      

   /**
    * sets the x-axis position of the object
    */   
   public void setX( int x )
      {
      xPosition = x;
      } //end setX()
      
   /**
    * @return the x-axis position of the object
    */
      
   public int getX()
      {
      return xPosition;
      } //end getX()
      
   /**
    * sets the y-axis position of the object
    */
      
   public void setY( int y )
      {
      yPosition = y;
      } //end setY()
      
   /**
    * @return the y-axis position of the object
    */
      
   public int getY()
      {
      return yPosition;
      } //end getY()
      
   /**
    * sets the height of the character in pixels
    * 
    * @param h  sets height to this int
    */
      
   public void setHeight( int h )
      {
      height = h;
      } //end setHeight()
      
  /**
   * @return height
   */
      
  public int getHeight()
     {
     return height;
     } //end getHeight()

   } //end GameImage