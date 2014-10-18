 import javax.swing.*;
 import java.awt.*;
 import java.util.ArrayList;
 
 public class Samus extends PlayableCharacter
   {
   private ArrayList< Image > samusFrames;
   private ArrayList< ImageIcon > samusFrameIcons;
   private Rectangle samusRectangle;
   private int gunHeight; //height of Samus' gun for bullet
   
   public Samus( int characterHeight )
      {
      this.gunHeight = 36;
      this.setHeight( characterHeight );
      
      samusFrames = new ArrayList< Image >();
      samusFrameIcons = new ArrayList< ImageIcon >() ;
      this.loadStates( this.createStates( "samus_RightMove.gif", "samus_LeftMove.gif",
                         "samus_jump.png", "samus_jump_left.png", "samus_Jump_SHOOTING.png",
                         "samus_Jump_SHOOTING_Left.png", "Samus_Normal_NORMAL.png", "Samus_Normal_NORMAL_LEFT.png"  ), 
                         samusFrames, samusFrameIcons);
      } //end Samus()
      
   public ArrayList< Image > getStates()
    {
    return samusFrames;
    } //end getState()
    
   public int getStateValue()
    {
    return samusFrames.size();
    } //end getStateValue()
    
   public int getGunHeight()
      {
      return gunHeight;
      } //end getGunHeight()
    
   public Rectangle getObjectRectangle()
      {
      
      samusRectangle = new Rectangle( this.getX() + 6, this.getY(),
                                      this.getStates().get( 0 ).getWidth( null ) - 12,
                                      this.getStates().get( 0 ).getHeight( null ) - 5);   
         
      return samusRectangle;
      } //end getCharacterRectangle()
   
   } //end Samus