 import javax.swing.*;
 import java.awt.*;
 import java.util.ArrayList;
 
 public class Paratroopa extends Enemy
   {
   private ArrayList< Image > paratroopaFrames;
   private ArrayList< ImageIcon > paratroopaFrameIcons;
   private Rectangle paratroopaRectangle;
   private double movementControl = 0;
   final private String name = "Paratroopa";
   private int jumpHeight;
   
   public Paratroopa( int characterHeight )
      {
      super( true );   
         
      jumpHeight = ( int ) ( Math.random() * 4 ) + 2;
      this.setHeight( characterHeight );
      paratroopaFrames = new ArrayList< Image >();
      paratroopaFrameIcons = new ArrayList< ImageIcon >();
      this.loadStates( this.createStates( "Paratroopa_Normal.gif", 
                                          "Paratroopa_Normal_REVERSE.gif",
                                          "Death_Explosion.gif" ),
                       paratroopaFrames, paratroopaFrameIcons );
            
      this.setY( ( int ) StageOne.getGround().getObjectRectangle().getMinY() );
      } //end Paratroopa
      
   public ArrayList< Image > getStates()
      {
      return paratroopaFrames;
      } //end getStates()
      
   public int getStateValue()
      {
      return paratroopaFrames.size();
      } //end getStateValue()
      
   public void move()
      {      
      //uses a sine algorithm to determine the paratroopa's bouncing path   
         
      movementControl = Math.abs( ( jumpHeight*Math.sin( ( ( .5 * Math.PI/180 ) * this.getX() ) ) ) * 100 );
      this.setY( (int) ( -movementControl + ( StageOne.getGround().getObjectRectangle().getMinY() - this.getHeight() ) ) );   
          
      super.move();
      } //end move()
      
   public Rectangle getObjectRectangle()
      {
      paratroopaRectangle = new Rectangle( this.getX(), this.getY(),
                                           this.getStates().get( 0 ).getWidth( null ),
                                           this.getStates().get( 0 ).getHeight( null ) );
                                           
      return paratroopaRectangle;
      } //end getObjectRectangle()
      
   public String getName()
      {
      return name;
      } //end getName()
   } //end Paratroopa