import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class PageIcon
  extends JLabel
{
  private BufferedImage image;
  public JButton toggle;
  
  public PageIcon()
  {
    this.toggle = new JButton("");
    this.toggle.setLocation(707, 359);
    this.toggle.setSize(52, 52);
    this.toggle.setOpaque(false);
    this.toggle.setContentAreaFilled(false);
    this.toggle.setBorderPainted(false);
    try
    {
      this.image = ImageIO.read(getClass().getResource("/Images/Icon.png"));
      this.toggle.setIcon(new ImageIcon(this.image));
    }
    catch (IOException e)
    {
      System.out.println("Unable to fetch image.");
      e.printStackTrace();
    }
  }
  
  RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
  
  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    g.drawImage(this.image, 0, 0, this);
  }
}
