 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import java.util.ArrayList;

 public class CreditScreenGUI extends JPanel implements MouseListener
   {
   private Image bg;
   private ImageIcon bgIcon; 
   private Font fontMain;
   private Font secondaryFont;
   private int startValue;
   private int addValue;
   private Image button;
   private Rectangle buttonBounds;
   private float alpha;
   
   private ArrayList< String > creditValues;
      
   public CreditScreenGUI()
      {
      this.addMouseListener( this );
      bgIcon = new ImageIcon( this.getClass().getResource( "Metroid_Smash_Background.png" ) );
      bg = bgIcon.getImage();
      button = new ImageIcon( this.getClass().getResource( "Metroid_Smash_Back_Button.png" ) ).getImage();
      fontMain = new Font( "Futura", Font.BOLD, 36 );
      secondaryFont = new Font( "Futura", Font.BOLD, 24 );
      startValue = 325; 
      alpha = 0.0f;
      buttonBounds = new Rectangle( this.getBackgroundImage().getWidth( null ) - 400, 
                                    700 , button.getWidth( null ), button.getHeight( null ) );
      
      creditValues = new ArrayList< String >();
      creditValues.add( "Credits" );
      creditValues.add( "Programmer" );
      creditValues.add( "Matas Empakeris" );
      creditValues.add( "Screen Graphics" );
      creditValues.add( creditValues.get( 2 ) );
      creditValues.add( "Game Graphics" );
      creditValues.add( "Nintendo" );
      creditValues.add( "Music" );
      creditValues.add( "Monday Paracetemol - Ulrich Schnauss" );
      creditValues.add( "Game Background" );
      creditValues.add( "Who knows?" );
      } //end CreditScreenGUI()
      
   final public Image getBackgroundImage()
      {
      return bg;
      } //end getBackgroundImage()
      
   public void paintComponent( Graphics g )
      {
      super.paintComponent( g );
      Graphics2D g2d = ( Graphics2D ) g;
      g2d.setColor( Color.WHITE );
      g2d.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER,
                                                    alpha ) );        
      addValue = 0;                                              
      g2d.setFont( fontMain );
      g2d.drawImage( this.getBackgroundImage(), 0, 0, null );             
      g2d.drawImage( button, this.getBackgroundImage().getWidth( null ) - 350, 
                     700, null );      
      
      for( int index = 0; index < creditValues.size(); index++ )
         {  
         if( index > 1 && index % 2 == 0 )
            {
            g2d.setFont( secondaryFont );
            } //end if
            
         else
            {
            g2d.setFont( fontMain );
            } //end else
            
         g2d.drawString( creditValues.get( index ), this.getBackgroundImage().getWidth( null )/30, 
                         startValue + addValue );
                         
         addValue += 50;               
         } //end for
         
      alpha += 0.01f;
      
      if( alpha >= 1.0f )
         {
         alpha = 1.0f;
         } //end if
         
      else
         {
         this.repaint();
         } //end else
      } //end paintComponent
      
   public void mouseClicked( MouseEvent e )
      { 
         
      if( buttonBounds.contains( e.getPoint() ) )
         {
         MainFrame.getFrame().remove( this );
         MainFrame.getFrame().add( new TitleScreenGUI() );
         MainFrame.getFrame().validate();
         } //end if
      } //end mouseClicked()
      
   public void mouseEntered( MouseEvent e )
      {
      /*
       * Present only for compiling
       */
      } //end mouseEntered
      
   public void mouseExited( MouseEvent e )
      {
      /*
       * Present only for compiling
       */
      } //end mouseExited()
      
   public void mouseReleased( MouseEvent e )
      {
      /*
       * Present only for compiling
       */
      } //end mouseReleased()
      
   public void mousePressed( MouseEvent e )
      {
      /*
       * Present only for compiling
       */
      } //end mousePressed()
   } //end CreditScreenGUI
   