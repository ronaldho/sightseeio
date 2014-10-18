 public abstract class MovingImage extends GameImage
   {
   private int changeX;
   private int changeY;
   private int direction;
   private boolean yTriggered;
   
   
   /**
    * Abstract method that requires all characters to have a unique movement
    */
   abstract public void move();
      
   public void setDirection( int d )
      {
      direction = d;
      } //end setDirection()
      
   public int getDirection()
      {
      return direction;
      } //end getDirection()
      
   public void setChangeX( int x )
      {
      changeX = x;
      }
      
   public int getChangeX()
      {
      return changeX;
      } //end ChangeX()
      
   public void setChangeY( int y )
      {
      changeY = y;
      } //end setChangeY()
      
   public int getChangeY()
      {
      return changeY;
      } //end ChangeY()
      
   public void setYTrigger( boolean yt )
      {
      yTriggered = yt;
      } //end setJumpTrigger
      
   public boolean getYTrigger()
      {
      return yTriggered;
      } //end getJumpTrigger()
   } //end MovingImage