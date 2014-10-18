 import java.awt.event.*;
 
 public class CharacterListener implements ActionListener
   {
   private Map map;   
      
   public CharacterListener(  Map m )
      {
      map = m;
      } //end CharacterListener()
      
   /**
    * @return an int that will determine which enemy will be drawn to
    *         screen at the specified time
    */
      
   public int getEnemyNumber()
      {
      return (int) ( Math.random() * 3 );
      } //end getEnemyNumber()
      
   public void actionPerformed( ActionEvent e )
      {
      map.setDrawActivity( true );
      } //end actionPerformed()
   } //end TestClass