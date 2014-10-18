 import javax.swing.*;
 import java.awt.*;
 import java.util.ArrayList;

 public class KammyKoopa extends Enemy
   {
   private ArrayList< Image > kkFrames;
   private ArrayList< ImageIcon > kkFrameIcons;
   private Rectangle kammyKoopaRectangle;
   private int movementControl;
   private static int initialXPosition;
   private static int initialYPosition;
   final private String name = "Kammy Koopa";
   
   public KammyKoopa( int characterHeight )
      {
      initialXPosition = 1337;
      this.setHeight( characterHeight );
      this.setDirection( Character.facingRight() );
      kkFrames = new ArrayList< Image >();
      kkFrameIcons = new ArrayList< ImageIcon >();
      this.loadStates( this.createStates( "kammy_koopa_normal.png", "kammy_koopa_normal_LEFT.png" ),
                       kkFrames, kkFrameIcons );
                       
      this.setX( initialXPosition );
      //this.setY( 100 );
      this.setDirection( Character.facingLeft() );
      } //end DonkeyKong()
    
   /**
    * @return an integer that indicates the initial x-axis position 
    *         of Kammy Koopa
    */   
      
   final public static int getInitialXPosition()
      {
      return initialXPosition;
      } //end getInitialPosition()
      
   /**
    * @return an integer that indicates the initial y-axis position
    *         of Kammy Koopa
    */
      
   final public static int getInitialYPosition()
      {
      return initialYPosition;
      } //end getInitialYPosition()
      
   public ArrayList< Image > getStates()
      {
      return kkFrames;
      } //end getStates()
      
   public int getStateValue()
      {
      return kkFrames.size();
      } //end getStateValue()
      
   public void move()
      {
      //a parabolic algorithm used to determine the path of Kammy Koopa   
         
      movementControl = ( int ) ( .001 * Math.pow( ( this.getX() - 400 ), 2 ) - 125 );//( int ) Math.abs( 2 * Math.sin( Math.PI/180 * ( .125 * this.getX() ) ) * 100 );
      this.setY( -movementControl );
      
      if( this.getX() < -500 )
         {
         this.setDirection( Character.facingRight() );
         } //end if
         
      if( this.getDirection() == Character.facingRight() )
         {
         this.setX( this.getX() + 9 );
         } //end if
      
      else   
         if( this.getDirection() == Character.facingLeft() )
            {
            this.setX( this.getX() - 9 );
            } //end if
      } //end move()
      
   public Rectangle getObjectRectangle()
      {
           kammyKoopaRectangle = new Rectangle( this.getX(), this.getY(),
                                           this.getStates().get( 0 ).getWidth( null ),
                                           this.getStates().get( 0 ).getHeight( null ) - 3 );  
                                           
      return kammyKoopaRectangle;                                     
                                           
                                         
      } //end getObjectRectangle()
      
   public String getName()
      {
      return name;
      } //end getName()
   } //end KammyKoopa