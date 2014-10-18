 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import java.util.ArrayList;
 
public class GameOverScreenGUI extends JPanel implements MouseListener, ActionListener
   {
   private Image bg;
   private ImageIcon bgIcon;
   private Image[] buttonImages = new Image[ 2 ];
   private String[] classpaths = { "Metroid_Smash_Restart_Button.png" ,"Metroid_Smash_Exit_Button.png" };
                                   
   private ArrayList< Rectangle > buttonBounds;                                
   private int positionCounter;
   private int xPosition;
   private Font font;
   private Timer textWrap;
   private float alpha;
   
   public GameOverScreenGUI()
      {
      bgIcon = new ImageIcon( this.getClass().getResource( "Metroid_Smash_Background.png" ) );
      bg = bgIcon.getImage();
      positionCounter = 50;
      xPosition = -800;
      buttonBounds = new ArrayList< Rectangle >();
      this.addMouseListener( this );
      font = new Font( "Impact", Font.BOLD, 72 );
      textWrap = new Timer( 27, this );
      textWrap.start();
      alpha = 0.0f;
      } //end TitleScreenGUI()
      
   final public Image getBackgroundImage()
      {
      return bg;
      } //end getBackgroundImage()
      
   private void setStringPosition( int x )
      {
      xPosition = x;
      } //end setX()
      
   private int getStringPosition()
      {
      return xPosition; 
      } //end getX()
      
   private void move()
      {
      this.setStringPosition( this.getStringPosition() - 5 );
      
      if( this.getStringPosition() < -800 )
         {
         this.setStringPosition( 1389 );
         } //end if
      } //end move()
      
   public void paintComponent( Graphics g )
      {
      super.paintComponent( g );
      Graphics2D g2d = ( Graphics2D ) g;
      g2d.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 
                                                   alpha ) );
      g2d.drawImage( this.getBackgroundImage(), 0, 0, null );
      g2d.setFont( font );
      g2d.setColor( Color.WHITE );
      
      g2d.drawString( "Game Over!", this.getStringPosition(), 300 );
      g2d.drawString( String.format( LogonScreenGUI.getPlayerName() + "'s Score is: %d", Map.getScore() ), this.getStringPosition() - 50, 400 );
      
      for( int index = 0; index < buttonImages.length; index++ )
         {
         buttonImages[ index ] = new ImageIcon( this.getClass().getResource( classpaths[ index ] ) ).getImage();
         } //end for
         
       for( int index = 0; index < buttonImages.length; index++ )
         {
         buttonBounds.add( new Rectangle( positionCounter, 500, buttonImages[ index ].getWidth( null ), 
                                          buttonImages[ index ].getHeight( null ) ) );
         
         positionCounter += 450;
         } //end for
         
      positionCounter = 50;
         
      for( Image image : buttonImages )
         {
         g2d.drawImage( image, positionCounter, 500, null );
         positionCounter += 450;
         } //end for
         
      alpha += .01f;
      
      if( alpha >= 1.0f )
         {
         alpha = 1.0f;
         } //end if
         
      this.repaint();
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
         MainFrame.getFrame().remove( this );
         MainFrame.getFrame().add( new TitleScreenGUI() );
         MainFrame.getFrame().validate();
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
      
   public void actionPerformed( ActionEvent e )
      {
      this.move();
      } //end actionPerformed()
   } //end TitleScreenGUI