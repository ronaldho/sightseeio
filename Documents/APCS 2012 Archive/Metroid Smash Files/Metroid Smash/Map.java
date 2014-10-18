 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.*;
 import java.util.ArrayList;
 import java.util.Iterator;
 import java.util.ConcurrentModificationException;
 import java.applet.*;
 
 @SuppressWarnings( "deprecation" )

 public class Map extends JPanel implements ActionListener, MapFrame, MouseListener
   {
   private float alpha = 0.0f;   
      
   final protected Samus samus = new Samus( 55 ); //creates an instance of samus
   final protected KammyKoopa kammy = new KammyKoopa( 55 ); //creates an instance of Kammy Koopa
   private Image bg; //This image resembles the background image
   private static int gravity = 1; //This resembles the gravity of all stages
   private ImageIcon bgIcon;
   private static int minimumY;
   private Dimension dimension;
   private static Rectangle mapRectangle;
   private Font font;
   private Font pauseFont;
   private boolean drawCharacter;
   private ArrayList< Enemy > enemies;
   private ArrayList< TingleBomb > tingleBombs;
   private boolean gameOver;
   private static int score;
   private int spawnDirection;
   private int bombTimer; //controls period between each bomb drop
   private int difficultyTimer; //controls spawn time of enemies
   private int characterNumber; //how many enemies are spawned at one time
   private Image[] pauseImages;
   private Rectangle[] pauseImageBounds;
   final private String[] classpaths = { "Metroid_Smash_Music_Button.png",
                                         "Metroid_Smash_No_Music_Button.png",
                                         "Metroid_Smash_Sound_Button.png",
                                         "Metroid_Smash_No_Sound_Button.png",
                                         "Metroid_Smash_Exit_Button.png" };
                                                    
   
   public int timeCounter;
   private CharacterListener cl;
   private KammyKoopaListener kkl;
   private int lives;
   
   private int spaceInterval; //the interval between life sprites drawn
   private boolean immortality;
   private long startImmortality;
   private long stopImmortality;
   
   protected ArrayList< Bullet > bullets;
   
   private Timer drawTimer;
   private Timer kammyKoopaTimer;
   
   public Map()
      {
      font = new Font("Brush Script MT", Font.BOLD, 72); 
      pauseFont = new Font( "Impact", Font.BOLD, 72 );
      timeCounter = 0;
      spaceInterval = 50;
      lives = 3;
      score = 0;
      gameOver = false;
      bombTimer = 889;
      difficultyTimer = 10000;
      
      bullets = new ArrayList< Bullet >();
      
      this.addKeyListener( PlayableCharacter.getMovement() );
      this.addMouseListener( this );
      dimension = new Dimension( 889, 889 );
      mapRectangle = new Rectangle( 0, 0, this.dimension.height, this.dimension.width );
      enemies = new ArrayList< Enemy >();
      tingleBombs = new ArrayList< TingleBomb >();
      
      cl = new CharacterListener( this );
      kkl = new KammyKoopaListener();
      drawCharacter = false;
      
      drawTimer = new Timer( difficultyTimer, cl );
      kammyKoopaTimer = new Timer( 20000, kkl );
      
      kammyKoopaTimer.start();
      drawTimer.start();
      
      characterNumber = 1;
      
      pauseImages = new Image[ 5 ];
      pauseImageBounds = new Rectangle[ 3 ];
      
      for( int index = 0; index < pauseImages.length; index++ )
         {
         pauseImages[ index ] = new ImageIcon( this.getClass().getResource( classpaths[ index ] ) ).getImage();
         } //end for
      } //end Map()
      
   public Map getMap( )
      {
      return this;
      } //end getMap()
      
   /*
    * This setter and getter determine when the character should become immortal
    */
   
   public void setImmortality( boolean i )
      {
      immortality = i;
      } //end setImmortality
      
   public boolean getImmortality()
      {
      return immortality;
      } //end getImmortality()
      
   /*
    * This setter and getter determine the minimumY position of the stage
    */
      
   public void setMinimumY( int y ) //sets the y coordinates of the stage ground (which is the minimum Y point)
      {
      minimumY = y;
      } //end setMinimumY()
    
   public static int getMinimumY()
      {
      return minimumY;
      } //end getMinimumY()
      
   /*
    * This setter and getter set the background image of the JPanel and return it
    */
      
   final public void setBackgroundImage( String classpath ) 
      {
      bgIcon = new ImageIcon( this.getClass().getResource( classpath ) );
      bg = bgIcon.getImage();
      } //end setBackground()
      
   final public Image getBackgroundImage()
      {
      return bg;
      } //end getBackgroundImage()
      
   /*
    * This setter and getter set and get the gravity of the level
    */
      
   public static void setGravity( int g )
      {
      gravity = g;
      } //end setGravity()
      
   public static int getGravity()
      {  
      return gravity;
      } //end callStageGravity()
      
   /*
    * This getter and setter determine when to add enemies to the enemy ArrayList
    */
      
   public void setDrawActivity( boolean draw ) //sets drawing enemy onto screen
      {
      drawCharacter = draw;
      } //end setDrawActivity()
      
   public boolean getDrawActivity()
      {
      return drawCharacter;
      } //end getDrawActivity()
      
   /*
    * returns the score
    */
      
   public static int getScore()
      {
      return score;
      } //end getScore()
      
   /*
    * Paints the generic graphics which would appear at this stage. Each individual stage
    * paints its own unique objects and enemies.
    */
      
   public void paintComponent( Graphics g )
      {   
      Graphics2D g2d = ( Graphics2D ) g;
      g2d.setFont( font );
      g2d.setColor( Color.WHITE );
      g2d.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER ,alpha ) );
      
      //if( samus.getRunState() )
         //{
      
         g2d.drawImage( this.getBackgroundImage(), 0, 0, null );
      
         if( kkl.getKoopaCall() == true )
            {
               
            if( kammy.getX() > getWorldBounds().getMinX() && kammy.getX() < getWorldBounds().getMaxX() )
               {
               if( kammy.getDirection() == Character.facingLeft() )
                  {
                  g2d.drawImage( kammy.getStates().get( 1 ), kammy.getX(), kammy.getY(), null );
               
                  if( kammy.getX() < bombTimer )
                     {
                     tingleBombs.add( new TingleBomb( kammy.getX(), kammy.getY() ) );
                     bombTimer = kammy.getX() - ( int ) ( Math.random() * 11 + 90 );
                     } //end if 
               } //end if
         
            else
               if( kammy.getDirection() == Character.facingRight() )
                  {
                  g2d.drawImage( kammy.getStates().get( 0 ), kammy.getX(), kammy.getY(), null );
                  
                  if( kammy.getX() > bombTimer )
                     {
                     tingleBombs.add( new TingleBomb( kammy.getX(), kammy.getY() ) );
                     bombTimer = kammy.getX() + 100;
                     } //end if
                  } //end else if    
               } //end if
            } //end if
         
         for( Iterator< TingleBomb > iterator = tingleBombs.iterator(); iterator.hasNext(); )
            {
            /*#
             * BUG: One bomb is always left in the list. For whatever reason, the bomb is not
             * colliding with the ground therefore, it is not removed from the array.
             */   
            TingleBomb tb = iterator.next();
         
            if( tb.getObjectRectangle().intersects( StageOne.getGround().getObjectRectangle() ) )
               {
               g2d.drawImage( tb.getStates().get( 1 ), tb.getX(), tb.getY(), null );
               iterator.remove();
               } //end if
            
            else
               if( tb.getObjectRectangle().intersects( samus.getObjectRectangle() ) && immortality == false )
                  {
                  lives--;
                  g2d.drawImage( tb.getStates().get( 1 ), tb.getX(), tb.getY(), null );
                  if( TitleScreenGUI.getSound() == false )  
                     {
                     MainFrame.getGameSounds().get( 1 ).play();
                     } //end if
                  iterator.remove();
               
                  stopImmortality = System.currentTimeMillis() + ( 3 * 1000 );
                  immortality = true;
                  } //end else if
            } //end for
      
         for( int index = 0; index < lives; index++ )
            { 
            g2d.drawImage( new ImageIcon( this.getClass().getResource( "LifeSprite.png" ) ).getImage(),
                                          spaceInterval, 30, null );
            spaceInterval += 50;                   
         } //end for
         
         spaceInterval = 50;
         
         if( tingleBombs.size() > 0 )
            {
            for( TingleBomb tb : tingleBombs )
               {
               g2d.drawImage( tb.getStates().get( 0 ), tb.getX(), tb.getY(), null );
               } //end for
            } //end if
      
         g2d.drawString( Integer.toString( score ), 600, 80 );
         
         if( samus.getRunState() == false && gameOver == true ) /*#GAME OVER*/
            {
            MainFrame.getFrame().remove( this );
            MainFrame.getFrame().add( new GameOverScreenGUI() );
            MainFrame.getFrame().validate();
            } //end if
         
         if( bullets.size() > 0 )
            {
            for( Bullet bullet : bullets )
               {  
              
              if( bullet.getDirection( null ).equals( "RIGHT" ) )
                 {
                 g2d.drawImage( bullet.getStates().get( 0 ), bullet.getX(), bullet.getY(), null );
                 } //end
              
              else
                 if( bullet.getDirection( null ).equals( "LEFT" ) )
                    {
                    g2d.drawImage( bullet.getStates().get( 1 ), bullet.getX(), bullet.getY(), null );
                    } //end else if
                } //end for
            } //end if
        
         if( enemies.size() > 0 )
            {
            for( Enemy enemy : enemies )
               {
               if( enemy.getDirection() == Character.facingRight() )
                  {
                  g2d.drawImage( enemy.getStates().get( 0 ), enemy.getX(), enemy.getY(), null );
                  } //end if
               
               else
                  if( enemy.getDirection() == Character.facingLeft() )
                     {
                     g2d.drawImage( enemy.getStates().get( 1 ), enemy.getX(), enemy.getY(), null );
                     } //end else if
               } //end for
            } //end if
        
         if( getDrawActivity() == true )
            {
            for( int index = 0; index < characterNumber; index++ )
               {
               enemies.add( Enemy.getEnemy( cl.getEnemyNumber() ) );
               } //end for 
            setDrawActivity( false );
            } //end if
            
         if( samus.lastFacing() == samus.lastFacedLeft() && Character.hasJumped() == true && samus.getHasShot() == true )
            {
            g2d.drawImage( samus.getStates().get( 5 ), samus.getX() + 3, samus.getY(), null );
            } //end if
       
         else     
            if( samus.lastFacing() == samus.lastFacedRight() && Character.hasJumped() == true && samus.getHasShot() == true )
               {
               g2d.drawImage( samus.getStates().get( 4 ), samus.getX(), samus.getY(), null );
               } //end if
        
         else      
            if( samus.lastFacing() == samus.lastFacedLeft() && Character.hasJumped() == true )
               {
               g2d.drawImage( samus.getStates().get( 3 ), samus.getX(), samus.getY(), null );    
               } //end if   
           
         else      
            if( samus.lastFacing() == samus.lastFacedRight() && Character.hasJumped() == true )
               {
               g2d.drawImage( samus.getStates().get( 2 ), samus.getX(), samus.getY(), null );
               } //end if
            
         if( samus.getDirection() == Character.facingRight() && Character.hasJumped() == false )
            {
            g2d.drawImage( samus.getStates().get( 0 ), samus.getX(), samus.getY(), null );
            } //end if
            
         if( samus.getDirection() == Character.facingLeft() && Character.hasJumped() == false )
            {
            g2d.drawImage( samus.getStates().get( 1 ), samus.getX(), samus.getY(), null );
            } //end if
         
         if( samus.getDirection() == Character.facingDefault() && Character.hasJumped() == false )
            {
            if( samus.lastFacing() == samus.lastFacedRight() )
               {
               g2d.drawImage( samus.getStates().get( 6 ), samus.getX(), samus.getY(), null );
               } //end nested if
               
            if( samus.lastFacing() == samus.lastFacedLeft() )
               {
               g2d.drawImage( samus.getStates().get( 7 ), samus.getX(), samus.getY(), null );
               } //end if
            } //end nested if
         
         if( Bullet.getFiredState() == true )
            {
            if( samus.lastFacing() == samus.lastFacedRight() )
               {
               bullets.add( new Bullet( ( int ) ( ( samus.getObjectRectangle().getMinX() + samus.getObjectRectangle().getMaxX() )/2 ),
                                      ( int ) samus.getObjectRectangle().getMaxY() - samus.getGunHeight(), "RIGHT" ) );        
               } //end if
              
            else
               if( samus.lastFacing() == samus.lastFacedLeft() )
                  {
                  bullets.add( new Bullet( ( int ) ( ( samus.getObjectRectangle().getMinX() + samus.getObjectRectangle().getMaxX() )/2 ),
                                           ( int ) samus.getObjectRectangle().getMaxY() - samus.getGunHeight(), "LEFT" ) );  
                  } //end else if
                 
            PlayableCharacter.keys()[ 3 ] = false; /*# change number! */
            Bullet.setIsFired( false ); 
            } //end if
           
         try
            {
            for( Iterator< Bullet > iterator = bullets.iterator(); iterator.hasNext(); ) /*using an iterator here prevents a concurrentmodificationexception. 
                                                                                     Basically just foreach loop code in a more complex manner*/
               {
               Bullet bullet = iterator.next();   
               if( bullet.getX() > getWorldBounds().getMaxX() - 30 )
                  {
                  g2d.drawImage( bullet.getStates().get( 2 ), (int) getWorldBounds().getMaxX() - 17, bullet.getY(), null );
                  iterator.remove();
                  } //end if
            
               else
                  if( bullet.getX() < getWorldBounds().getMinX() )
                     {
                     g2d.drawImage( bullet.getStates().get( 2 ), (int) getWorldBounds().getMinX(), bullet.getY(), null );
                     iterator.remove();   
                     } //end if
               } //end for
            } //end try
         
         catch( ConcurrentModificationException e )
            {
            System.err.print( e );
            } //end catch
         
         try
            {
            for( Iterator< Bullet > bulletIterator = bullets.iterator(); bulletIterator.hasNext(); )
               {
               Bullet bullet = bulletIterator.next();
            
               for( Iterator< Enemy > enemyIterator = enemies.iterator(); enemyIterator.hasNext(); )
                  {
                  Enemy enemy = enemyIterator.next();
               
                  if( enemy.enemyCollision( bullet, samus ) == true )
                     {
                     g2d.drawImage( bullet.getStates().get( 2 ), enemy.getX(), enemy.getY(), null );
                     g2d.drawImage( enemy.getStates().get( 2 ),  ( int ) enemy.getObjectRectangle().getX(), ( int ) enemy.getObjectRectangle().getY(), null );
                     
                     if( TitleScreenGUI.getSound() == false )
                        {
                        MainFrame.getGameSounds().get( 2 ).play();
                        } //end if
                     
                     score += 100;
                  
                     bulletIterator.remove();
                     enemyIterator.remove();
                     } //end if
                  } //end for
               } //end for
            } //end try
         
         catch( ConcurrentModificationException e )
            {
            System.err.print( e );
            } //end catch
         
         catch( IllegalStateException ex )
            {
            //ignore the exception
            } //end catch
         
         try
            {
            for( Iterator< Enemy > enemyIterator = enemies.iterator(); enemyIterator.hasNext(); )
               {
               Enemy enemy = enemyIterator.next();  
            
               if( enemy.enemyCollision( samus ) == true && immortality == false )
                  {
                  lives--;
                  
                  if( TitleScreenGUI.getSound() == false )
                     {
                     MainFrame.getGameSounds().get( 1 ).play();
                     } //end if
               
                  if( samus.getX() > enemy.getX() )
                     {
                     samus.setX( ( int ) enemy.getObjectRectangle().getMaxX() + 30 );
                     } //end if
                  
                  else
                     if( samus.getX() < enemy.getX() )
                        {
                        samus.setX( ( int ) enemy.getObjectRectangle().getMinX() - 30 );
                        } //end else if
                     
                  Enemy.setCollision( false );
                  stopImmortality = System.currentTimeMillis() + ( 3 * 1000 );
                  immortality = true;
                  } //end if
               } //end for
            } //end try
         
         catch( ConcurrentModificationException e )
            {
            System.err.print( e );
            } //end catch
         
               
         if( immortality == true )
            {
            g2d.drawImage( new ImageIcon( getClass().getResource( "invincible.gif" ) ).getImage(), 280, 38, null );
            } //end if
         
         alpha+= .01f;
         
         if( alpha >= 1.0f )
            {
            alpha = 1.0f;
            } //end if 
      //} //end if
         
      if( samus.getRunState() == false && gameOver == false && samus.isDead() == false )
         {
         /*#
          * BUG: The pause button does not work 100% of the time. Unfortunately, I could
          * not find out the reason either, but I suspect it has something to do with the
          * system running into small stutters when painting.
          */ 
            
         g2d.setFont( pauseFont );
         g2d.drawString( "Game is Paused", ( int ) this.getWorldBounds().getWidth()/4 - 10,
                                          ( int ) this.getWorldBounds().getHeight()/2 - 50 );
         
         pauseImageBounds[ 0 ] = new Rectangle( ( int ) this.getWorldBounds().getWidth()/4 - 60,
                                                ( int ) this.getWorldBounds().getHeight()/2 + 60,
                                                pauseImages[ 0 ].getWidth( null ), pauseImages[ 0 ].getHeight( null ) );
                                                
         pauseImageBounds[ 1 ] = new Rectangle( ( int ) this.getWorldBounds().getWidth()/4 + 400,
                                                ( int ) this.getWorldBounds().getHeight()/2 + 60,
                                                pauseImages[ 1 ].getWidth( null ), pauseImages[ 1 ].getHeight( null ) );
                                                
         pauseImageBounds[ 2 ] = new Rectangle( ( int ) this.getWorldBounds().getWidth()/4 + 60,
                                                ( int ) this.getWorldBounds().getWidth()/2 + 50,
                                                pauseImages[ 4 ].getWidth( null ), pauseImages[ 4 ].getHeight( null ) );                                      
                                                
         if( TitleScreenGUI.getMusic() == false )
            {
            g2d.drawImage( pauseImages[ 0 ], ( int ) this.getWorldBounds().getWidth()/4 - 60,
                                             ( int ) this.getWorldBounds().getHeight()/2 + 60, null ); 
            } //end if
            
         else
            {
            g2d.drawImage( pauseImages[ 1 ], ( int ) this.getWorldBounds().getWidth()/4 - 60,
                                             ( int ) this.getWorldBounds().getHeight()/2 + 60, null );
            } //end else
            
        if( TitleScreenGUI.getSound() == false )
           {
           g2d.drawImage( pauseImages[ 2 ], ( int ) this.getWorldBounds().getWidth()/4 + 400,
                                            ( int ) this.getWorldBounds().getHeight()/2 + 60, null );
           } //end if
           
        else
           {
           g2d.drawImage( pauseImages[ 3 ], ( int ) this.getWorldBounds().getWidth()/4 + 400,
                                            ( int ) this.getWorldBounds().getHeight()/2 + 60, null );
           } //end else
           
        g2d.drawImage( pauseImages[ 4 ], ( int ) this.getWorldBounds().getWidth()/4 + 60,
                                         ( int ) this.getWorldBounds().getWidth()/2 + 50, null ); 
        } //end if
      } //end paintComponent()
      
   /*
    * returns the frame bounds
    */
   
   public static Rectangle getWorldBounds()
      {
      return mapRectangle;
      } //end getWorldBounds()
     
   public void actionPerformed( ActionEvent e )
      {
      this.repaint();
      this.setFocusable( true );
      this.requestFocus();    
                          
      if( lives <= 0 )
         {
         samus.setIsDead( true );
         samus.setRunState( false );
         kammyKoopaTimer.stop();
         drawTimer.stop();
         
         if( samus.getY() < getWorldBounds().getY() )
            {
            samus.setIsDead( false );
            samus.setRunState( false );
            gameOver = true;
            } //end if
         } //end if
      
      if( gameOver == false )
         {
         samus.move();
         } //end if
         
      if( samus.getRunState() == true )
         {
         if( score % 500 == 0 && score != 0 )
            {
            characterNumber++;
            score += 100;
            difficultyTimer -= 500;
            drawTimer.stop();
            drawTimer = new Timer( difficultyTimer, cl );
            drawTimer.start();
            } //end if
            
         if( immortality == true )
            {
            startImmortality = System.currentTimeMillis();
            
            if( startImmortality < stopImmortality )
               {
               Enemy.setCollision( false );
               } //end if
               
            else
               {
               immortality = false;
               } //end else
            } //end if
         
         for( Enemy enemy: enemies )
            {
            if( enemy.getName().equals( "Ghost" ) )
               {
               Ghost g = ( Ghost ) enemy;
               g.move( samus );
               } //end if
               
            else
               {
               enemy.move();
               } //end else 
            } //end for

         if( bullets.size() > 0 )
            {
            for( Bullet bullet : bullets )
               {
               bullet.move();
               } //end for
            } //end if
         
         if( kkl.getKoopaCall() == true )
            {
            if( kammy.getX() > 1400 )
               {
               kkl.setKoopaCall( false );
               kammy.setX( KammyKoopa.getInitialXPosition() ); 
               kammy.setY( KammyKoopa.getInitialYPosition() );
               kammy.setDirection( Character.facingLeft() );
               } //end if
               
            else
               {
               kammy.move();
               }
            } //end if
            
         if( tingleBombs.size() > 0 )
            {
            for( TingleBomb tb : tingleBombs )
               {
               tb.move(); 
               } //end for
            } //end if
      } //end if
         
      samus.checkPause( drawTimer, kammyKoopaTimer );
      } //end actionPerformed()
      
   public void mouseClicked( MouseEvent e )
      {
      if( pauseImageBounds[ 0 ].contains( e.getPoint() ) )
         {
         if( TitleScreenGUI.getMusic() == false )
            {
            MainFrame.getMusicThread().suspend();
            TitleScreenGUI.setMusic( true );
            } //end if
            
         else
            if( TitleScreenGUI.getMusic() == true )
               {
               MainFrame.getMusicThread().resume();
               TitleScreenGUI.setMusic( false );
               } //end else
         } //end if
         
      else   
         if( pauseImageBounds[ 1 ].contains( e.getPoint() ) )
            {
            if( TitleScreenGUI.getSound() == false )
               {
               TitleScreenGUI.setSound( true );
               } //end if
               
            else
               if( TitleScreenGUI.getSound() == true )
                  {
                  TitleScreenGUI.setSound( false );
                  } //end else if
            } //end else if
            
      else
         if( pauseImageBounds[ 2 ].contains( e.getPoint() ) )
            {
            MainFrame.getFrame().remove( this );
            MainFrame.getFrame().add( new TitleScreenGUI() );
            MainFrame.getFrame().validate();
            } //end else if
      } //end mouseClicked()
      
   public void mouseEntered( MouseEvent e )
      {
      //only present for compiling   
      } //end mouseEntered()
      
   public void mouseExited( MouseEvent e )
      {
      //only present for compiling   
      } //end mouseExited()
      
   public void mouseReleased( MouseEvent e )
      {
      //only present for compiling  
      } //end mouseReleased()
      
   public void mousePressed( MouseEvent e )
      {
      //only present for compiling   
      } //end mousePressed()
   } //end Map