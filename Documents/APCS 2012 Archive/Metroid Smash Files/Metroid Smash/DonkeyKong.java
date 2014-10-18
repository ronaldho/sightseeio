 import javax.swing.*;
 import java.awt.*;
 import java.util.ArrayList;

 public class DonkeyKong extends Enemy
   {   
      
   private ArrayList< Image > dkFrames;
   private ArrayList< ImageIcon > dkFrameIcons;
   private Rectangle donkeyKongRectangle;
   final private String name = "DonkeyKong";
   
   public DonkeyKong( int characterHeight )
      {
      super( true );   
         
      this.setHeight( characterHeight );
      dkFrames = new ArrayList< Image >();
      dkFrameIcons = new ArrayList< ImageIcon >();
      this.loadStates( this.createStates( "Donkey_Kong_Normal.gif",
                                          "Donkey_Kong_Normal_Left.gif",
                                          "Death_Explosion.gif" ),
                       dkFrames, dkFrameIcons );
                       
      this.setY( ( int ) StageOne.getGround().getObjectRectangle().getMinY()
                  - this.getHeight() );
      } //end DonkeyKong()
      
   public ArrayList< Image > getStates()
      {
      return dkFrames;
      } //end getStates()
      
   public int getStateValue()
      {
      return dkFrames.size();
      } //end getStateValue()
      
   public void move()
      {
      super.move();
      
      } //end move()
      
   public Rectangle getObjectRectangle()
      {
           donkeyKongRectangle = new Rectangle( this.getX(), this.getY(),
                                           this.getStates().get( 0 ).getWidth( null ),
                                           this.getStates().get( 0 ).getHeight( null ) - 3 );  
                                           
      return donkeyKongRectangle;                                     
                                           
                                         
      } //end getObjectRectangle()
      
   public String getName()
      {
      return name;
      } //end getName()
   } //end DonkeyKong