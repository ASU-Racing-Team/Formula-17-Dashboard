import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import javax.swing.JLabel;

class secondProgressRatio
  extends JLabel
{
  public int value;
  private Font displayFont = new Font("digital-7", 1, 30);
  
  public secondProgressRatio()
  {
    setFont(this.displayFont);
    setVerticalAlignment(1);
    setHorizontalAlignment(0);
    setOpaque(false);
    setLayout(null);
    setBackground(new Color(0, 0, 100, 100));
    setLocation(121, 150);
    setSize(50, 50);
  }
  
  RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
}
