 import java.util.ArrayList; 
 import java.awt.*;
 import javax.swing.*;

 public class TingleBomb extends MovingImage
   {
   private ArrayList< Image > tbFrames;
   private ArrayList< ImageIcon > tbFrameIcons;
   private Rectangle tbRectangle;
   private int bombSpeed;
      
   public TingleBomb( int x, int y )
      {
      bombSpeed = 0;
      tbFrames = new ArrayList< Image >();
      tbFrameIcons = new ArrayList< ImageIcon >();   
      this.loadStates( this.createStates( "Tingle_Bomb.png", "Death_Explosion.gif" ), tbFrames,
                       tbFrameIcons );
                       
      this.setX( x );
      this.setY( y );
      } //end TingleBomb()
   
   public void move()
      {
      /*
       * Do not use this method because the bomb belongs to a flying enemy
       * and is not used on its own
       */
      this.setY( this.getY() + 5 ); 
      } //end move()
      
   /**
    * @param character  refers to a flying character such as Kammy Koopa that
    *                   drops the bomb from the character's location
    */
      
   public void move( Character character )
      {
      this.setY( character.getY() + 5 );
      } //end move()
      
   public Rectangle getObjectRectangle()
      {
      tbRectangle = new Rectangle( this.getX(), this.getY(), this.getStates().get( 0 ).getHeight( null ), 
                                   this.getStates().get( 0 ).getWidth( null ) ); 
                                   
      return tbRectangle;
      } //end getRectangleObject()
      
   public ArrayList< Image > getStates()
      {
      return tbFrames;
      } //end getStates();
      
   public int getStateValue()
      {
      return tbFrames.size(); 
      } //end getStateValue()
   } //end Bullet