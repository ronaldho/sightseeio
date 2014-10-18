 import java.util.ArrayList;
 import java.awt.*;
 import javax.swing.*;
 
 public class StageOneGround extends LevelObject
    {
    private ArrayList< Image > blockFrames;
    private ArrayList< ImageIcon > blockFrameIcons;
    private Rectangle groundRectangle;
    private int collisionJumpCounter;
    private boolean isTouchingSurfaceObject;
    
    public StageOneGround( int objectWidth, int objectHeight )
        {
        super( objectWidth, objectHeight );
        blockFrames = new ArrayList< Image >();
        blockFrameIcons = new ArrayList< ImageIcon >();
        this.loadStates( this.createStates( "Stage_One_Level_Object_GROUND.png" ),
                         blockFrames,
                         blockFrameIcons );
        
        groundRectangle = new Rectangle( this.getX(), this.getY(),
                                               this.getStates().get( 0 ).getWidth( null ) + 50,
                                               this.getStates().get( 0 ).getHeight( null ) );
        
        isTouchingSurfaceObject = true;
        } //end StageOneGround()
    
    public ArrayList< Image > getStates()
        {
        return blockFrames;
        } //end getStates()
        
    public int getStateValue()
        {
        return blockFrames.size();  
        } //end ArrayList
        
    public Rectangle getObjectRectangle()
      {
      return groundRectangle;
      } //end getObjectRectangle
      
       public void setCollisionJumpControl( int cjc )
      {
      collisionJumpCounter = cjc;
      } //end setCollisionJumpCounter()
      
   public int getCollisionJumpControl()
      {
      return collisionJumpCounter;
      } //end getcollisionJumpCounter()
      
   public void setIsTouchingSurfaceObject( boolean itso )
      {
      isTouchingSurfaceObject = itso;
      } //end setIsTouchingSurfaceObject()
      
   public boolean getIsTouchingSurfaceObject()
      {
      return isTouchingSurfaceObject;
      } //end getIsTouchingSurfaceObject();
    } //end Block