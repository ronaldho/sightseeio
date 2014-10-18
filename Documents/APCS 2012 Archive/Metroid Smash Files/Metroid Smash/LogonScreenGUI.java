 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import java.util.ArrayList;
 
 public class LogonScreenGUI extends JPanel implements MouseListener, KeyListener
   {
   private Image bg;
   private ImageIcon bgIcon;
   private Font fontMain;
   private Font secondaryFont;
   private Image button;
   private Rectangle buttonBounds;
   private static ArrayList< String > letterValues;
   private ArrayList< Integer > positions;
   private float alpha;
   final private int KEY_DELETE = 8;
   final private int KEY_ENTER = 10;
   //private String playerName;
   
   public LogonScreenGUI()
      {
      /* 
       * A little proof to show that I COULD have used JOptionPane, but decided to make a custom screen instead :)
       * Fully swappable in a moment's notice at one's request if need be
       */
      //playerName = JOptionPane.showInputDialog( "Enter your name: " );
         
      this.addMouseListener( this );
      this.addKeyListener( this );
      
      bgIcon = new ImageIcon( this.getClass().getResource( "Metroid_Smash_Login_Background.png" ) );
      bg = bgIcon.getImage();
      button = new ImageIcon( this.getClass().getResource( "Metroid_Smash_OK_Button.png" ) ).getImage();
      fontMain = new Font( "Monaco", Font.BOLD, 72 );
      secondaryFont = new Font( "Futura", Font.BOLD, 60 );
      buttonBounds = new Rectangle( this.getBackgroundImage().getWidth( null )/2 - 80, 
                                      700 , button.getWidth( null ), button.getHeight( null ) );
      letterValues = new ArrayList< String >();
        
      positions = new ArrayList< Integer >();
      positions.add( 233 );
      alpha = 0.0f;
      } //end InstructionScreenGUI()
      
   final public Image getBackgroundImage()
      {
      return bg;  
      } //end getBackroundImage()
      
   public void paintComponent( Graphics g )
      {
      super.paintComponent( g );
      this.setFocusable( true );
      this.requestFocus();
      Graphics2D g2d = ( Graphics2D ) g;
      g2d.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER,
                                                    alpha ) );
                                                    
      g2d.drawImage( this.getBackgroundImage(), 0, 0, null );
      g2d.drawImage( button, this.getBackgroundImage().getWidth( null )/2 - 80, 
                     700, null );              
      
      for( int index = 0; index < letterValues.size(); index++ )
         {
         g2d.setColor( Color.WHITE );
         g2d.setFont( fontMain );
         g2d.drawString( letterValues.get( index ), positions.get( index ), 575 );
         } //end for
         
      g2d.setColor( Color.LIGHT_GRAY );
      g2d.setFont( secondaryFont );
      g2d.drawString( "Enter Your Name", 233, 450 );
      
      alpha += 0.1f;
      
      if( alpha >= 1.0f )
         {
         alpha = 1.0f;
         } //end if
         
      this.repaint();
      } //end paintComponent()
   
   public void keyTyped( KeyEvent e )
      {
      char key = e.getKeyChar();
      int iKey = e.getKeyChar();
      
      if( letterValues.size() < 9 && iKey != KEY_DELETE )
         {
         letterValues.add( String.valueOf( key ) ); 
         positions.add( positions.get( positions.size() - 1 ) + 50 );
         } //end if
      
      if( iKey == KEY_DELETE )
         {
         if( letterValues.size() > 0 )
            {
            letterValues.remove( letterValues.size() - 1 );
            positions.remove( positions.size() - 1 );
            } //end nested if
         } //end if
         
      else
         if( iKey == KEY_ENTER )
            {
            
            MainFrame.getFrame().remove( this );
            MainFrame.getFrame().add( new TitleScreenGUI() );
            MainFrame.getFrame().validate();
            } //end else if
      }//end keyTyped()
      
   final public static String getPlayerName()
      {
      String playerName = "";
         
      for( String string: letterValues )
         {
         playerName = playerName + string;
         } //end for
         
      return playerName;
      } //end getPlayerName()
      
   public void keyPressed( KeyEvent e )
      {
      //only present for compiling  
      } //end keyPressed()
      
   public void keyReleased( KeyEvent e )
      {
      //only present for compiling   
      } //end keyReleased
      
   public void mouseClicked( MouseEvent e )
      {
      if( buttonBounds.contains( e.getPoint() ) )
         {
         MainFrame.getFrame().remove( this );
         MainFrame.getFrame().add( new TitleScreenGUI() );
         MainFrame.getFrame().validate();
         } //end if
      } //end MousePressed()
      
   public void mouseEntered( MouseEvent e )
      {
      //only present for compiling  
      } //end MouseEntered()
      
   public void mouseExited( MouseEvent e )
      {
      //only present for compiling  
      } //end MouseExited()
      
   public void mousePressed( MouseEvent e )
      {
      //only present for compiling 
      } //end MousePressed()
      
   public void mouseReleased( MouseEvent e )
      {
      //only present for compiling  
      } //end MouseReleased()
   }