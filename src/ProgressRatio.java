import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import javax.swing.JLabel;

public class ProgressRatio
  extends JLabel
{
  public int value;
  
  public ProgressRatio(int fontSize)
  {
    Font displayFont = new Font("digital-7", 1, fontSize);
    setFont(displayFont);
    setVerticalAlignment(1);
    setHorizontalAlignment(0);
    setOpaque(false);
    setLayout(null);
    setBackground(new Color(0, 0, 100, 100));
    setLocation(368, 370);
    setSize(70, 50);
  }
  
  RenderingHints hints = new RenderingHints(
    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
}
