import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;

class Circle
  extends JLabel
{
  Dimension dim = new Dimension(19, 19);
  
  public Circle()
  {
    setOpaque(false);
    setLayout(null);
    
    setSize(this.dim);
  }
  
  RenderingHints hints = new RenderingHints(
    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
  
  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    g2.setColor(Color.YELLOW);
    g2.setRenderingHints(this.hints);
    g2.fillOval(0, 0, (int)this.dim.getWidth(), (int)this.dim.getHeight());
  }
}
