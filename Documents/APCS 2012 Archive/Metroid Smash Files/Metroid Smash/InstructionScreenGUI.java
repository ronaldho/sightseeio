 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import java.util.ArrayList;
 public class InstructionScreenGUI extends JPanel implements MouseListener
   {
   private Image bg;
   private ImageIcon bgIcon;
   private Font fontMain;
   private Font secondaryFont;
   private Image button;
   private Rectangle buttonBounds;
   private ArrayList< String > instructionValues;
   private double xPosition;
   private int position;
   private float alpha;

   
   public InstructionScreenGUI()
      {
      this.addMouseListener( this );
      bgIcon = new ImageIcon( this.getClass().getResource( "Metroid_Smash_Background.png" ) );
      bg = bgIcon.getImage();
      button = new ImageIcon( this.getClass().getResource( "Metroid_Smash_Back_Button.png" ) ).getImage();
      fontMain = new Font( "Futura", Font.BOLD, 48 );
      secondaryFont = new Font( "Futura", Font.BOLD, 36 );
      buttonBounds = new Rectangle( this.getBackgroundImage().getWidth( null ) - 400, 
                                      700 , button.getWidth( null ), button.getHeight( null ) );
      instructionValues = new ArrayList< String >();
      
      instructionValues.add( "How to Play" );
      instructionValues.add( "Left and Right Arrow Keys: Move left and right" );
      instructionValues.add( "X: Shoot" );
      instructionValues.add( "Space Bar: Jump" );
      instructionValues.add( "P: Pause the game, Option toggles, and Back to menu" );
        
      xPosition = 1000;
      alpha = 0.0f;                                

      
      } //end InstructionScreenGUI()
      
   final public Image getBackgroundImage()
      {
      return bg;  
      } //end getBackroundImage()
      
   public void paintComponent( Graphics g )
      {
      super.paintComponent( g );
      Graphics2D g2d = ( Graphics2D ) g;
      g2d.setColor( Color.WHITE );
      g2d.setFont( fontMain );
      g2d.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER,
                                                    alpha ) );
                                                    
      g2d.drawImage( this.getBackgroundImage(), 0, 0, null );
      g2d.drawImage( button, this.getBackgroundImage().getWidth( null ) - 350, 
                     700, null ); 
      position = 300;               
      
      for( int index = 0; index < instructionValues.size(); index++ )
         {
         if( index < 1 )
            {
            g2d.setFont( fontMain );
            } //end if
           
         else
            {
            g2d.setFont( secondaryFont );
            } //end else
         
         position += 50;
         
         g2d.drawString( instructionValues.get( index ), ( int ) xPosition, position );
         } //end for
      
      alpha += 0.1f;
      
      if( alpha >= 1.0f )
         {
         alpha = 1.0f;
         } //end if
      
      this.setStringPosition( this.getStringPosition() - .40 );
      
      if( this.getStringPosition() < -900.00 )
         {
         this.setStringPosition( 1100 );
         } //end if
      this.repaint();
      } //end paintComponent()
   
   private void setStringPosition( double sp )
      {
      xPosition = sp;
      } //end getStringPosition()
      
   private double getStringPosition()
      {
      return xPosition;
      } //end getStringPosition()
      
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