 import java.awt.event.*; 
 import java.awt.*;
 import javax.swing.*;

 public class KammyKoopaListener implements ActionListener
   {
   private boolean koopaCalled; 
   
   public KammyKoopaListener()
      {
      koopaCalled = false;
      } //end KammyKoopaListener()
      
   /*
    * This setter and getter determine when Kammy Koopa should
    * be drawn to the screen.
    */
   
   public void setKoopaCall( boolean c )
      {
      koopaCalled = c;
      } //end setKoopaCalled();
   
   public boolean getKoopaCall()
      {
      return koopaCalled;
      } //end getKoopaCall()
      
   public void actionPerformed( ActionEvent e )
      {
      this.setKoopaCall( true );
      } //end actionPerformed()
   } //end KammyKoopaListener