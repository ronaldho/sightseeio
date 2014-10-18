 import javax.swing.*;
 import java.awt.*;
 import java.util.ArrayList;
 
 public class Ghost extends Enemy
   {
   private Character character;
   private ArrayList< Image > ghostFrames;
   private ArrayList< ImageIcon > ghostFrameIcons;
   private Rectangle ghostRectangle;
   private int randomHeight;
   final private String name = "Ghost";
   
   public Ghost( int characterHeight )
      {
      super( true );
      this.setHeight( 40 );
      randomHeight = ( int ) ( Math.random() * 446 );
      
      ghostFrames = new ArrayList< Image >();
      ghostFrameIcons = new ArrayList< ImageIcon >();
      
      this.loadStates( this.createStates( "Ghost_Right.png", "Ghost_Left.png",
                                          "Death_Explosion.gif" ),
                       ghostFrames, ghostFrameIcons );
                       
      this.setY( randomHeight );
      } //end Ghost
      
   public ArrayList< Image > getStates()
      {
      return ghostFrames;
      } //end getStates();
      
   public int getStateValue()
      {
      return ghostFrames.size();
      } //end getStateValue()
      
   /**
    * Special version of move()
    * 
    * @param character  the character in which Ghost follows
    */
      
   public void move( Character character )
      {  
         
      if( character.getX() < this.getX() )
         {
         this.setDirection( Character.facingLeft() );
         this.setLastFacing( LEFT );
         this.setX( this.getX() - 3 );
         } //end if
         
      else
         if( character.getX() > this.getX() )
            {
            this.setDirection( Character.facingRight() );
            this.setLastFacing( RIGHT );
            this.setX( this.getX() + 3 );
            } //end else if
            
      else
         {
         if( this.lastFacing() == RIGHT )
            {
            this.setDirection( Character.facingRight() );
            } //end if
            
         else
            {
            this.setDirection( Character.facingLeft() );
            } //end nested else
            
         this.setX( character.getX() );
         } //end else
            
      if( character.getY() < this.getY() )
         {
         this.setY( this.getY() - 3 );
         } //end if
         
      else
         if( character.getY() > this.getY() )
            {
            this.setY( this.getY() + 3 );
            } //end else if
            
      else
         {
         if( this.lastFacing() == RIGHT )
            {
            this.setDirection( Character.facingRight() );
            } //end if
            
         else
            {
            this.setDirection( Character.facingLeft() );
            } //end else
            
         this.setY( character.getY() );
         } //end else
      } //end move()
      
   public Rectangle getObjectRectangle()
      {
      ghostRectangle = new Rectangle( this.getX(), this.getY(),
                                      this.getStates().get( 0 ).getWidth( null ),
                                      this.getStates().get( 0 ).getHeight( null ) );
                        
      return ghostRectangle;
      } //end getObjectRectangle()
    
   public String getName()
      {
      return name;
      } //end getName()
   } //end Ghost