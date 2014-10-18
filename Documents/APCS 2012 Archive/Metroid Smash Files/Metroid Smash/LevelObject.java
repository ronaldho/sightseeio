 public abstract class LevelObject extends GameImage
   {
   public LevelObject( int objectX, int objectY )
      {
      super( objectX, objectY );
      } //end LevelObject
      
   /*
    * First group controls which objects have been touch to see
    * if gravity should take effect
    */   
      
   abstract public void setCollisionJumpControl( int cjc );
   abstract public int getCollisionJumpControl();
   
   /*
    * This group determines if the character is touching a platform
    * object
    */
   
   abstract public void setIsTouchingSurfaceObject( boolean itso );
   abstract public boolean getIsTouchingSurfaceObject();
   } //end CollisionHelper