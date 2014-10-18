 import java.awt.*;
 import javax.swing.*;
 import java.util.*;

 public abstract class Character extends MovingImage
   {
   /*# 
    * Enum was not used as I could not implement it properly at the time.
    * Final constants are used instead (numbers are random, but large as
    * to not interfere with anything else)
    */
      
   final private static int FACING_UP = 103;
   final private static int FACING_RIGHT = 102;
   final private static int FACING_LEFT = 101;
   final private static int DEFAULT = 100;
   
   //protected in order to provide readability to other classes that may need Left and Right.
   //values are constant so no one can change the values outside of this class.
   final protected boolean LEFT = false;
   final protected boolean RIGHT = true;
   
   private boolean lastFacing;

   final private static int MOVE_UP = -10;
   final private static int MOVE_RIGHT = 7;
   final private static int MOVE_LEFT = -7;
   final private static int NO_MOVE = 0;
   
   private int changeYAssistant; //This is a counter for delta_y in order to assist jumpTrigger
   private static boolean hasJumped;
   
   public Character()
      {
      setYTrigger( false );
      lastFacing = true;

      } //end Character();
   
   /**
    * sets the jump value to indicate if the jump image should be painted
    * 
    * @param jump  set hasJumped to this value
    */
   final public static void setJump( boolean jump )
      {
      hasJumped = jump;
      } //end setJump()
      
   /**
    * @return hasJumped value to indicate if jump image should be painted
    */
      
   final public static boolean hasJumped()
      {
      return hasJumped;
      } //end boolean
      
   /**
    * @return int NO_MOVE to indicate the character is not moving
    */
      
   final public static int notMoving() //returns 0. Character is not moving.
      {
      return NO_MOVE;  
      } //end notMoving()
      
   /**
    * @return int MOVE_RIGHT to indicate the character is moving right
    */
      
   final public static int movingRight() //return positive one to show character moving right
      {
      return MOVE_RIGHT;
      } //end movingRight()
      
   /**
    * @return int MOVE_LEFT to indicate the character is moving left
    */
      
   final public static int movingLeft() //return negative one to show character moving right
      {
      return MOVE_LEFT;
      } //end movingLeft()
      
   /**
    * @return int MOVE_UP to indicate jump speed
    */
      
   final public static int movingUp()
      {
      return MOVE_UP;
      } //end movingUp()
      
   /**
    * @return int FACING_RIGHT to indicate that the character is facing right
    */
      
   final public static int facingRight()
      {
      return FACING_RIGHT;
      } //end facingRight()
      
    /**
    * @return int FACING_LEFT to indicate that the character is facing left
    */   
      
   final public static int facingLeft()
      {
      return FACING_LEFT;
      } //end facingLeft()
      
   /**
    * @return int FACING_UP to indicate that the character is jumping
    */
      
   final public static int facingUp()
      {
      return FACING_UP;
      } //end facingUp()
      
   /**
    * @return int DEFAULT to indicate that the character is in resting position
    */
      
   final public static int facingDefault()
      {
      return DEFAULT;
      } //end facingUp()
      
   /**
    * @return boolean LEFT to indicate that the character was last facing left
    */

   final public boolean lastFacedLeft()
     {
     return LEFT;
     } //end lastFacedLeft()
     
   /**
    * @return boolean RIGHT to indicate that the character was last facing right
    */
      
   final public boolean lastFacedRight()
     {
     return RIGHT;
     } //end lastFacedRight()
   
   final public void setLastFacing( boolean leftOrRight )
      {
      lastFacing = leftOrRight;
      } //end setLastFacing()
      
   final public boolean lastFacing()
      {
      return lastFacing;  
      } //end lastFacing
      
      
   /*# CHANGE WHEN ENEMY CLASS IS CREATED #*/
   /*   
   public void checkCollision( Enemy e )
      {
      if( this.getX() == e.getX() || this.getY() == e.getY() )
         {
         damageCounter--;
         } //end if
      } //end checkCollision
   */
   
      
   } //end Character