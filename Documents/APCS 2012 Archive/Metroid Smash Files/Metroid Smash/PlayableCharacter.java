 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.*;
 import java.applet.*;

 
 public abstract class PlayableCharacter extends Character
   {
   private static Keys movement;
   private Bullet bullet;
   private int changeYAssistantPC; //helps with changing Y direction in jumps/collisions
   private int jumpControl; //This counter controls the jumping mechanism of the character
   private boolean pauseControl; //This boolean controls when screen is paused and unpaused
   
   private final int LEFT_PRESSED = 0; 
   private final int RIGHT_PRESSED = 1;
   private final int SPACE_PRESSED = 2;
   private final int SHOOT_PRESSED = 3;
   private final int PAUSE_PRESSED = 4;
   private final int KEY_X = 120; //must use in order for keyTyped to work. Does not work with VK_X
   private final int KEY_P = 112; //must use in order for keyTyped to work. Does not work with VK_P
  
   private static boolean[] pressed = new boolean[ 5 ];
   
   private boolean isTouchingSurfaceObject;
   private boolean hasShot;
   private boolean isRunning; //indicates if game is running
   private boolean isDead;
   
   

   public PlayableCharacter()
      {
      isTouchingSurfaceObject = false;
      movement = new Keys(); //creates key object for use in getMovement()
      changeYAssistantPC = Character.movingUp();
      setX( 0 );
      hasShot = false;
      jumpControl = 0;
      pauseControl = false;
      isRunning = true;
      isDead = false;
      pressed[ PAUSE_PRESSED ] = false;
      
      } //end PlayableCharacter()
      
   
   private class Keys implements KeyListener
      {
      /*# BUG: Occasionally, one may run into a problem where the character's
       * direction gets stuck. I have seen this happen in professional games
       * such as Super Smash Bros. as well and do not know what causes this
       * glitch at the moment.
       */  
         
      public void keyTyped( KeyEvent k )
         {
         int key = k.getKeyChar();
         
         if( key == KEY_X )
            {
            if( TitleScreenGUI.getSound() == false )
               {
               MainFrame.getGameSounds().get( 0 ).play();
               } //end nested if
            pressed[ SHOOT_PRESSED ] = true;
            } //end if
               
         if( key == KEY_P )
            {
            pressed[ PAUSE_PRESSED ] = true;
            } //end if
         } //end keyTyped()    
         
      public void keyPressed( KeyEvent k ) //key press moves character left/right
         {
         int key = k.getKeyCode();
         int keyc = k.getKeyChar(); 
         
         switch( key )
            {
            case KeyEvent.VK_LEFT:
               pressed[ LEFT_PRESSED ] = true;
               break;
               
            case KeyEvent.VK_RIGHT:
               pressed[ RIGHT_PRESSED ] = true;
               break;
               
            } //end switch
            
         if( keyc == KeyEvent.VK_SPACE )
            {
            pressed[ SPACE_PRESSED ] = true;
            } //end if
         } //end keyPressed()
         
      public void keyReleased( KeyEvent k ) //event for key released
         {
         int key = k.getKeyCode();
         int keyc = k.getKeyChar();
         setChangeX( PlayableCharacter.notMoving() );
         
         if( key != KeyEvent.VK_SPACE )
            {
            setDirection( Character.facingDefault() );
            } //end if
            
         switch( key )
            {
            case KeyEvent.VK_LEFT:
               pressed[ LEFT_PRESSED ] = false;
               break;
            
            case KeyEvent.VK_RIGHT:
               pressed[ RIGHT_PRESSED ] = false;
               break;
            } //end switch
            
         if( keyc == KeyEvent.VK_SPACE )
            {
            pressed[ SPACE_PRESSED ] = false;
            } //end if
         } //end keyReleased()
      } //end Keys
      
   /**
    * @return the array which holds which keys are pressed
    */
      
   public static boolean[] keys()
      {
      return pressed;
      } //end keys()
   
   /**
    * @return Key object for KeyListener acces in Stage (Jpanel)
    */
      
   public static Keys getMovement() //returns a Key object for KeyListener in Stage (JPanel)
      {
      return movement;
      } //end getMovement()
      
   public void setHasShot( boolean shot )
      {
      hasShot = shot;
      } //end setHasShot()
      
   public boolean getHasShot()
      {
      return hasShot;
      } //end getHasShot()
      
   public void setRunState( boolean rs )
      {
      isRunning = rs;
      } //end setRunState()
      
   public boolean getRunState()
      {
      return isRunning;
      } //end
      
   public void setIsDead( boolean d )
      {
      isDead = d;
      } //end setCharacterState()
      
   public boolean isDead()
      {
      return isDead;
      } //end getCharacterState()
      
   /**
    * This method checks if the pause key has been pressed and sends the
    * messages required for pausing the game.
    * 
    * @param timers  an array of Timer objects in which this method controls
    *                in order to stop the spawn timers
    */
      
   public void checkPause( Timer...timers )
      {
      /* 
       * By making this method, instead of freezing all the keys and movements in every
       * class, I can clean up code by simply freezing all actions performed and only
       * having the pause method available for use.
       */   
      if( pressed[ PAUSE_PRESSED ] == true )
         {  
         if( pauseControl == true )
            {
            isRunning = false;
            pauseControl = false;
            
            for( Timer timer : timers )
               {
               timer.stop();
               } //end for
            } //end if
            
         else
            if( pauseControl == false )
               {
               isRunning = true;
               pauseControl = true;
               
               for( Timer timer : timers )
                  {
                  timer.start();
                  } //end for
               } //end if
               
         pressed[ PAUSE_PRESSED ] = false;
         } //end if
      } //end checkPause()
      
   /**
    * The unique Playable Character version of move()
    */
      
   public void move()
      {
      setX( getX() + getChangeX() );
      
      if( this.isDead == true && getRunState() == false )
         {
         this.setY( this.getY() - 10 );
         } //end if
      
      else
         if( this.isDead == false && this.getRunState() == true )
            {
            if( getYTrigger() == true )
               {
               jumpControl++;
               //changeYAssistantPC += Map.getGravity();
               setChangeY( getChangeY() - 2/*changeYAssistantPC*/ );
               setY( getY() + getChangeY() );
        
               if( jumpControl >= 7 )
                  {
                  setChangeY( getChangeY() + 3 );
                  setY( getY() + getChangeY() );
                  } 
            } //end if
        
            if( getYTrigger() == false )
               {
               changeYAssistantPC = Character.movingUp();
               jumpControl = 0;
               } //end if
         
            if( pressed[ LEFT_PRESSED ] == true )
               {
               setChangeX( PlayableCharacter.movingLeft() );
               setDirection( Character.facingLeft() );
               setLastFacing( lastFacedLeft() );
               } //end if
      
            if( pressed[ SPACE_PRESSED ] == true )
               {
               setYTrigger( true );
               Character.setJump( true );
               } //end if
                 
            if( pressed[ RIGHT_PRESSED ] == true )
               {
               setChangeX( PlayableCharacter.movingRight() );
               setDirection( Character.facingRight() );
               setLastFacing( lastFacedRight() );
               } //end if
         
            if( pressed[ SHOOT_PRESSED ] == true )
               {
               bullet.setIsFired( true );
               this.setHasShot( true );
               } //end if
            } //end else if
      } //end move()
      
   /**
    * the method which checks this character's collision with any stage objects
    * 
    * @param o  the level object in which the character may be touching
    * 
    * @param character  the current character which is playing
    */
      
   public void checkCollision( LevelObject o, Character character )
      {
      if( character.getObjectRectangle().intersectsLine( Map.getWorldBounds().getMinX(),
                                                         Map.getWorldBounds().getMaxY(),
                                                         Map.getWorldBounds().getMinX(),
                                                         Map.getWorldBounds().getMinY() ) )
         {
         character.setX( character.getX() + Character.movingRight() );
         } //end if
         
      else
         if( character.getObjectRectangle().intersectsLine( Map.getWorldBounds().getMaxX(),
                                                            Map.getWorldBounds().getMaxY(),
                                                            Map.getWorldBounds().getMaxX(),
                                                            Map.getWorldBounds().getMinY() ) )
            {
            character.setX( character.getX() - Character.movingRight() );
            } //end else if
         
      if( character.getObjectRectangle().intersectsLine( o.getObjectRectangle().getMinX(),
                                                         o.getObjectRectangle().getMaxY(),
                                                         o.getObjectRectangle().getMinX(),
                                                         o.getObjectRectangle().getMinY() + 25 ) )
         {
         character.setX( ( int ) o.getObjectRectangle().getMinX() );
         } //end if
         
      else
         if( character.getObjectRectangle().intersectsLine( o.getObjectRectangle().getMaxX(),
                                                            o.getObjectRectangle().getMaxY(),
                                                            o.getObjectRectangle().getMaxX(),
                                                            o.getObjectRectangle().getMinY() + 25 ) )
            {
            character.setX( ( int ) o.getObjectRectangle().getMaxX() );  
            } //end else if
      
      if( o != StageOne.getGround() )
         {
         if( character.getObjectRectangle().intersectsLine( o.getObjectRectangle().getMinX(),
                                                            o.getObjectRectangle().getMaxY(),
                                                            o.getObjectRectangle().getMaxX(),
                                                            o.getObjectRectangle().getMaxY() ) )
            {
            character.setY( ( int ) o.getObjectRectangle().getMaxY() );
            } //end nested if
         } //end if
         
      if( character.getObjectRectangle().intersectsLine( o.getObjectRectangle().getMinX(),
                                                         o.getObjectRectangle().getMinY(),
                                                         o.getObjectRectangle().getMaxX(),
                                                         o.getObjectRectangle().getMinY() ) )
         {
            
         if( o.getCollisionJumpControl() < 3 )
            {
            o.setCollisionJumpControl( o.getCollisionJumpControl() + 1 );
            } //end if
            
         if( o.getCollisionJumpControl() == 1 )
            {
            character.setJump( false );
            character.setYTrigger( false );
            setChangeY( 0 );
            character.setY( (int) ( o.getObjectRectangle().getMinY() - character.getObjectRectangle().getHeight() ) );
            setDirection( Character.facingDefault() );
            this.setHasShot( false );
            } //end if
            
         o.setIsTouchingSurfaceObject( true );
         } //end if
         
      else
         {
         o.setCollisionJumpControl( 0 );
         o.setIsTouchingSurfaceObject( false );
         } //end character
      } //end checkCollision
      
   /**
    * @ return the height of the character's gun in pixels to determine where
    *          the bullet should be drawn
    */
      
   public abstract int getGunHeight();
   } //end PlayableCharacter