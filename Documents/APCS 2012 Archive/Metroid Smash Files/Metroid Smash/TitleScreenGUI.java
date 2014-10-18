 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import java.util.ArrayList;
 
@SuppressWarnings( "deprecation" ) 
 
public class TitleScreenGUI extends JPanel implements MouseListener, KeyListener
   {
   private Image bg;
   private ImageIcon bgIcon;
   private Image[] buttonImages = new Image[ 8 ];
   private String[] classpaths = { "Metroid_Smash_Play_Button.png", "Metroid_Smash_Instructions_Button.png",
                                   "Metroid_Smash_Credits_Button.png", "Metroid_Smash_Exit_Button.png",
                                   "Metroid_Smash_Music_Button.png", "Metroid_Smash_Sound_Button.png",
                                   "Metroid_Smash_No_Music_Button.png", "Metroid_Smash_No_Sound_Button.png" };
                                   
   private ArrayList< Rectangle > buttonBounds;                                
   private int positionCounter;
   private float alpha;
   private static boolean soundOff;
   private static boolean musicOff;
   
   public TitleScreenGUI()
      {
      this.addKeyListener( this );  
      bgIcon = new ImageIcon( this.getClass().getResource( "Metroid_Smash_Background.png" ) );
      bg = bgIcon.getImage();
      positionCounter = 250;
      buttonBounds = new ArrayList< Rectangle >();
      this.addMouseListener( this );
      alpha = 0.0f;
      } //end TitleScreenGUI()
      
   public static void setSound( boolean s )
      {
      soundOff = s;
      } //end setSound()
      
   public static boolean getSound()
      {
      return soundOff;
      } //end getSound()
      
   public static void setMusic( boolean m )
      {
      musicOff = m;
      } //end setMusic()
      
   public static boolean getMusic()
      {
      return musicOff;
      } //end getMusic()
      
   final public Image getBackgroundImage()
      {
      return bg;
      } //end getBackgroundImage()
      
   public void paintComponent( Graphics g )
      {
      super.paintComponent( g );
      this.setFocusable( true );
      this.requestFocus();
      
      Graphics2D g2d = ( Graphics2D ) g;
      g2d.setComposite(AlphaComposite.getInstance(
            AlphaComposite.SRC_OVER, alpha));
      
      g2d.drawImage( this.getBackgroundImage(), 0, 0, null );
      
      for( int index = 0; index < buttonImages.length; index++ )
         {
         buttonImages[ index ] = new ImageIcon( this.getClass().getResource( classpaths[ index ] ) ).getImage();
         } //end for
         
      for( int index = 0; index < buttonImages.length - 4; index++ )
         {
         buttonBounds.add( new Rectangle( this.getBackgroundImage().getWidth( null )/3, positionCounter,
                                  buttonImages[ index ].getWidth( null ), buttonImages[ index ].getHeight( null ) ) );
         
         positionCounter += 150;
         } //end for
         
        buttonBounds.add( new Rectangle( 650, 750, buttonImages[ 4 ].getWidth( null ),
                                                   buttonImages[ 4 ].getHeight( null ) ) );
                                                   
        buttonBounds.add( new Rectangle( 750, 750, buttonImages[ 5 ].getWidth( null ),
                                                 buttonImages[ 5 ].getHeight( null ) ) );
         
        positionCounter = 250;
         
      for( int index = 0; index < buttonImages.length - 4; index++ )
         {
         g2d.drawImage( buttonImages[ index ], this.getBackgroundImage().getWidth( null )/3, positionCounter, null );
         positionCounter += 150;
         } //end for
         
      if( musicOff == false )
         {
         g2d.drawImage( buttonImages[ 4 ], 650, 750, null );
         } //end if
         
      else
         {
         g2d.drawImage( buttonImages[ 6 ], 650, 750, null ); 
         } //end else
         
      if( soundOff == false )
         {
         g2d.drawImage( buttonImages[ 5 ], 750, 750, null );
         } //end if
         
      else
         {
         g2d.drawImage( buttonImages[ 7 ], 750, 750, null );
         } //end else
 
      alpha += 0.01f;
         
      if (alpha >= 1.0f ) 
         {
         alpha = 1.0f;
         } //end if

      this.repaint();
      } //end paintComponent
      
   public void keyTyped( KeyEvent e )
      {
      //present only for compiling
      } //end keyTyped()
      
   public void keyPressed( KeyEvent e )
      {
      /*
       * keyPressed() is used here because keyTyped() only recognizes
       * characters and the escape key is not considered to be a character
       */   
         
      int key = e.getKeyCode();
      
      if( key == KeyEvent.VK_ESCAPE )
         {
         System.exit( 0 );
         } //end if
      } //end keyPressed()
      
   public void keyReleased( KeyEvent e )
      {
      //only present for compiling
      } //end keyReleased()
      
   public void mouseClicked( MouseEvent e )
      {
      if( buttonBounds.get( 0 ).contains( e.getPoint() ) )
         {
         MainFrame.getFrame().remove( this );
         MainFrame.getFrame().add( new StageOne() );
         MainFrame.getFrame().validate();
         } //end if
      
      else   
         if( buttonBounds.get( 1 ).contains( e.getPoint() ) )
            {
            MainFrame.getFrame().remove( this );
            MainFrame.getFrame().add( new InstructionScreenGUI() );
            MainFrame.getFrame().validate();
            } //end if
      
      else
         if( buttonBounds.get( 2 ).contains( e.getPoint() ) )
            {
            MainFrame.getFrame().remove( this );
            MainFrame.getFrame().add( new CreditScreenGUI() );
            MainFrame.getFrame().validate();
            } //end if
      
      else      
         if( buttonBounds.get( 3 ).contains( e.getPoint() ) )
            {
            MainFrame.getMusicThread().stop();
            System.exit( 0 );
            } //end if
            
      else
         if( buttonBounds.get( 4 ).contains( e.getPoint() ) )
            {
            if( musicOff == false )
               {
               musicOff = true;
               MainFrame.getMusicThread().suspend();
               } //end if
               
            else
               {
               musicOff = false;
               MainFrame.getMusicThread().resume();
               } //end else
            } //end else if
            
      else
         if( buttonBounds.get( 5 ).contains( e.getPoint() ) )
            {
            if( soundOff == false )
               {
               soundOff = true;
               //Do stuff here;
               } //end if
               
            else
               {
               soundOff = false;
               //Do stuff here;
               } //end else
            } //end else if
       
      } //end mouseClicked
      
   public void mouseExited( MouseEvent e )
      {
      //Required for compiling  
      } //end mouseExited()
      
   public void mouseEntered( MouseEvent e )
      {
      //Required for compiling
      } //end mouseEntered()
      
   public void mouseReleased( MouseEvent e )
      {
      //Required for compiling  
      } //end mouseReleased()
      
   public void mousePressed( MouseEvent e )
      {
      //Required for compiling  
      } //end mousePressed()
   } //end TitleScreenGUI