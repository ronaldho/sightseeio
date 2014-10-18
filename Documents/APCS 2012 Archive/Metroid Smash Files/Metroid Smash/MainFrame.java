 import javax.swing.*;
 import java.awt.*;
 import java.util.ArrayList;
 import java.applet.*;
 import java.io.*;
 import javazoom.jl.player.advanced.AdvancedPlayer;
 
 public class MainFrame implements Runnable
   {
   private static JFrame ts;
   private static JPanel panel;
   private static ArrayList< AudioClip > sounds;
   private static Thread thread;
   private AdvancedPlayer player;
   private boolean isRunning;
   
   
   public static void main( String[] args )
      {
      new MainFrame();
      } //end main
      
   public MainFrame()
      {
      ts = new JFrame();
      
      ts.setSize( 889, 889 );
      ts.setResizable( false );
      ts.setLayout( new GridLayout( 1, 1 ) );
      ts.setTitle( "Metroid Smash" );
      ts.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      
      ts.add( new LogonScreenGUI() );
      ts.setVisible( true );
      
      ts.setFocusable( true );
      
      sounds = new ArrayList< AudioClip >();
      sounds.add( Applet.newAudioClip( this.getClass().getResource( "Samus_Shooting_Sound.wav" ) ) );
      sounds.add( Applet.newAudioClip( this.getClass().getResource( "Character_Hit.wav" ) ) );
      sounds.add( Applet.newAudioClip( this.getClass().getResource( "Kill_Sound.wav" ) ) );
      
      isRunning = true;
      thread = new Thread( this );
      thread.start();
      } //end TitleScreen()
      
   public void run()
      {
      try
         { 
         while( isRunning )
            {
            player = new AdvancedPlayer( new BufferedInputStream( new FileInputStream( new File( this.getClass().getResource( "Title_Screen_Music.mp3" ).toURI() ) ) ) );  
            player.play();
            player.close();
            thread.sleep( 2000 );
            } //end while
         } //end try
         
      catch( Exception ex )
         {
         ex.printStackTrace();
         } //end catch  
      } //end run()
      
   /*
    * This setter and getter returns the Frame's music thread which controls
    * when the background music should be played
    */
      
   final public static Thread getMusicThread()
      {
      return thread;
      } //end getMusicThread()
      
   /*
    * This returns the game's sound effects
    */
      
   final public static ArrayList< AudioClip > getGameSounds()
      {
      return sounds;
      } //end getGameSounds()
      
   /*
    * This returns the game's frame
    */
      
   final public static JFrame getFrame()
      {
      return ts;
      } //end getFrame()
   } //end TitleScreen