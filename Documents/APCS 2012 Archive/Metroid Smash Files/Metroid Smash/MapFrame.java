import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import javax.swing.JPanel;

 public interface MapFrame
   {
   public void paintComponent( Graphics g );
   public void actionPerformed( ActionEvent e );
   } //end interface MapFrame