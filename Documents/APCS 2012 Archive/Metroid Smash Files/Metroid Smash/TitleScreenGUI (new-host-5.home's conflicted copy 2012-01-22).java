 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import java.util.ArrayList;
 import java.io.*;
 import javazoom.jl.player.advanced.AdvancedPlayer;
 
public class TitleScreenGUI extends JPanel implements MouseListener, Runnable
   {
   private Image bg;
   private ImageIcon bgIcon;
   private Image[] buttonImages = new Image[ 8 ];
   private String[] classpaths = { "Metroid_Smash_Play_Button.png", "Metroid_Smash_Instructions_Button.png",
                                   "Metroid_Smash_Credits_Button.png", "Metroid_Smash_Exit_Button.png",
                                   "Metroid_Smash_Music_Button.png", "Metroid_Smash_No_Music_Button.png",
                                   "Metroid_Smash_Sound_Button.png", "Metroid_Smash_No_Sound_Button.png" };
                                   
   private ArrayList< Rectangle > buttonBounds;                                
   private int positionCounter;
   private float alpha;
   private AdvancedPlayer player;
   private Thread thread;
   private boolean isRunning;
   private boolean panelRunning;
   
   public TitleScreenGUI()
      {
      panelRunning = true;
      isRunning = true;
      bgIcon = new ImageIcon( this.getClass().getResource( "Metroid_Smash_Background.png" ) );
      bg = bgIcon.getImage();
      positionCounter = 250;
      buttonBounds = new ArrayList< Rectangle >();
      this.addMouseListener( this );
      alpha = 0.0f;
      thread = new Thread( this );
      thread.start();
      
      } //end TitleScreenGUI()
      
   final public Image getBackgroundImage()
      {
      return bg;
      } //end getBackgroundImage()
      
   public void run()
      {
      try
         {
         player = new AdvancedPlayer( new BufferedInputStream( new FileInputStream( new File( this.getClass().getResource( "Title_Screen_Music.mp3" ).toURI() ) ) ) );   
         //player.play();   
         
         while( isRunning )
            {
            player.play();
            } //end while
         } //end try
         
      catch( Exception ex )
         {
         ex.printStackTrace();
         } //end catch  
      } //end run()
      
   public void paintComponent( Graphics g )
      {
      if( panelRunning == true )
         {
      super.paintComponent( g );
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
         
      positionCounter = 250;
         
      for( Image image : buttonImages )
         {
         g2d.drawImage( image, this.getBackgroundImage().getWidth( null )/3, positionCounter, null );
         positionCounter += 150;
         } //end for        

      alpha += 0.01f;
         
      if (alpha >= 1.0f ) 
         {
         alpha = 1.0f;
         } //end if
         
      else 
         {
         this.repaint();
         } //end else
      } //end if

      } //end paintComponent
      
   public void mouseClicked( MouseEvent e )
      {
      if( buttonBounds.get( 0 ).contains( e.getPoint() ) )
         {
         MainFrame.getFrame().remove( this );
         MainFrame.getFrame().add( new StageOne() );
         MainFrame.getFrame().validate();
         } //end if
         
      if( buttonBounds.get( 1 ).contains( e.getPoint() ) )
         {
         player.close();
         } //end if
      
      if( buttonBounds.get( 2 ).contains( e.getPoint() ) )
         {
         MainFrame.getFrame().remove( this );
         MainFrame.getFrame().add( new CreditScreenGUI() );
         MainFrame.getFrame().validate();
         } //end if
        
      if( buttonBounds.get( 3 ).contains( e.getPoint() ) )
         {
         System.exit( 0 );
         } //end if
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