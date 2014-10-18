 import java.util.ArrayList; 
 import java.awt.*;
 import javax.swing.*;

 public class Bullet extends MovingImage

   {
   private static boolean isFired;
   private ArrayList< Image > bulletFrames;
   private ArrayList< ImageIcon > bulletFrameIcons;
   private Rectangle bulletRectangle;
   private int bulletSpeed;
   private Direction direction; 
   
   
   /*# 
    * Finally learned how to use an enum instead of final constants that are prone
    * to being changed!
    */
   private enum Direction
      {
      LEFT, RIGHT;
      } //end enum  
   
   
   public Bullet( int x, int y, String direction )
      {
      bulletFrames = new ArrayList< Image >();
      bulletFrameIcons = new ArrayList< ImageIcon >();
      this.loadStates( this.createStates( "Samus_Bullet.png", "Samus_Bullet_Left.png", "Bullet_Explosion.gif" ), bulletFrames, bulletFrameIcons );
      this.setX( x );
      this.setY( y );
      isFired = false;
      this.direction = this.direction.valueOf( direction );
      bulletSpeed = 7;
      } //end Bullet()
      
   public void setDirection( String dir )
      {
      this.direction = this.direction.valueOf( dir );
      } //end setDirection
      
   public String getDirection( String enterNull ) //put null in parentheses to override super getDirection()
      {
      return this.direction.name();
      } //end getDirection()
      
   /**
    * Bullet object's version of move()
    */
   
   public void move()
      {
      if( this.direction == Direction.LEFT )
         {
         this.setX( this.getX() - bulletSpeed );
         bulletSpeed++;
         } //end if
         
      else
         if( this.direction == Direction.RIGHT )
            {
            this.setX( this.getX() + bulletSpeed );
            bulletSpeed++;
            } //end else if
      } //end move()
      
   /**
    * sets the fired state of the bullet
    * 
    * @param f  sets isFired to this
    */   
      
   public static void setIsFired( boolean f )
      {
      isFired = f;
      } //end setIsFired()
      
   /**
    * @return a boolean representing the state of isFired
    */
      
   public static boolean getFiredState()
      {
      return isFired;
      } //end getFiredState()
      
   public ArrayList< Image > getStates()
    {
    return bulletFrames;
    } //end getStates()
    
   public int getStateValue()
    {
    return bulletFrames.size();
    } //end getStateValue()
    
   public Rectangle getObjectRectangle()
      {   
      bulletRectangle = new Rectangle( 0, 0, this.getStates().get( 0 ).getWidth( null ) ,this.getStates().get( 0 ).getHeight( null ) );
         
      return null;
      } //end getObjectRectangle()
      
   public Rectangle getObjectRectangle( PlayableCharacter character )
      {
      bulletRectangle = new Rectangle( this.getX(), this.getY(),
                                         this.getStates().get( 0 ).getHeight( null ), this.getStates().get( 0 ).getWidth( null ) );
                                         
      return bulletRectangle;                                   
      } //end getObjectRectangle()
   } //end Bullet