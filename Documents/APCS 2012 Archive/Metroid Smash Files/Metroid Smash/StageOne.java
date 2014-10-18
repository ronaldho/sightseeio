 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.*;
 import java.util.ArrayList;
 import java.util.Iterator;

 public class StageOne extends Map
   {
   private static StageOneGround ground;
   
   private StageOnePlatformGeneric platformGenericBottom;
   private static StageOnePlatformGeneric platformGenericTop;
   private static StageOnePlatformLeftBottom platformLeftBottom;
   private static StageOnePlatformLeftTop platformLeftTop;
   
   private ArrayList< LevelObject > levelObjects; //contains all objects in the level. Used for collision detection
   private ArrayList< LevelObject > notTouching;
   
   private Timer timer; //refresh rate set in constructor
   private int notColliding;
   
   
   public StageOne()
      {
      ground = new StageOneGround( -5, 837 );
      this.setMinimumY( ground.getY() );
      
      platformGenericBottom = new StageOnePlatformGeneric( 532, 649 );
      platformGenericTop = new StageOnePlatformGeneric( ( int ) 300, 400 );
      platformLeftBottom = new StageOnePlatformLeftBottom( 132, 649 );
      platformLeftTop = new StageOnePlatformLeftTop( 100, 586 );
      
      samus.setY( ground.getY() - samus.getHeight() );
      
      levelObjects = new ArrayList< LevelObject >();
      notTouching = new ArrayList< LevelObject >();
      levelObjects.add( platformGenericBottom );
      levelObjects.add( platformGenericTop );
      levelObjects.add( platformLeftBottom );
      levelObjects.add( platformLeftTop );
      levelObjects.add( ground );
      
      this.setBackgroundImage( "level_background_1.png" );
      
      timer = new Timer( 27, this ); //default: 27
      timer.start();
      } //end Stage_One()
      
   final public static StageOneGround getGround()
      {
      return ground;
      } //end getGround()
      
   public void paintComponent( Graphics g )
      {
      Graphics2D g2d = ( Graphics2D ) g;

      super.paintComponent( g2d );
      
      //draw platform rectangle
      //g2d.draw( platformGeneric.getObjectRectangle() );
      //g2d.draw( platformLeftBottom.getObjectRectangle() );
      //g2d.draw( platformLeftTop.getObjectRectangle() );
      //g2d.draw( ground.getObjectRectangle() );
      
      g2d.drawImage( platformGenericBottom.getStates().get( 0 ), platformGenericBottom.getX(), platformGenericBottom.getY(), null );
      g2d.drawImage( platformGenericTop.getStates().get( 0 ), platformGenericTop.getX(), platformGenericTop.getY(), null );
      g2d.drawImage( platformLeftBottom.getStates().get( 0 ), platformLeftBottom.getX(), platformLeftBottom.getY(), null );
      g2d.drawImage( platformLeftTop.getStates().get( 0 ), platformLeftTop.getX(), platformLeftTop.getY(), null );
      
      g2d.drawImage( ground.getStates().get( 0 ), 0, 837, null ); //0,837
      } //end paintComponent()
      
   public void actionPerformed( ActionEvent e )
      {
      super.actionPerformed( e );
      
      //paratroopa.enemyCollision( samus );
      
      //System.out.println( paratroopa.getDirection() );
      /* samus.checkCollision( platformLeftTop, this, samus );
      samus.checkCollision( platformLeftBottom, this, samus );
      samus.checkCollision( platformGeneric, this, samus ); */
      
      if( samus.isDead() == false )
         {
         for( LevelObject o: levelObjects )
            {
            samus.checkCollision( o, samus );
        
            if( o.getIsTouchingSurfaceObject() == false && samus.getYTrigger() == false )
              {
              notTouching.add( o );
              } //end if
            } //end for
        
         if( notTouching.size() == levelObjects.size() && samus.isDead() == false )
            {
            samus.setChangeY( samus.getChangeY() + 3 );
            samus.setY( samus.getChangeY() + samus.getY() );
            notTouching.clear();
            } //end if
         
           else
              {
              notTouching.clear();
              } //end else
         } //end if
      } //end actionPerformed()
   } //end Stage_One