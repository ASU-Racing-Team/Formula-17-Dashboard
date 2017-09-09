import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintStream;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class TPSBar
  extends JLabel
{
  public BufferedImage progressBar;
  public int counter = 0;
  public Boolean isEnd = Boolean.valueOf(false);
  public float ratio = 0.0F;
  public int maxBarWidth;
  public int maxBarHeight;
  public int[] progressWidth = new int['?'];
  
  public TPSBar(String path)
  {
    setOpaque(false);
    setLayout(null);
    setLocation(245, 310);
    setBackground(new Color(0, 0, 100, 100));
    try
    {
      this.progressBar = ImageIO.read(getClass().getResource(path));
    }
    catch (IOException e)
    {
      System.out.println("Unable to fetch image.");
      e.printStackTrace();
    }
    this.maxBarWidth = this.progressBar.getWidth();
    this.maxBarHeight = this.progressBar.getHeight();
    for (int i = 0; i <= this.maxBarWidth; i++) {
      this.progressWidth[i] = i;
    }
  }
  
  RenderingHints hints = new RenderingHints(
    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
  
  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    g2.drawImage(this.progressBar, 0, 0, null);
  }
}
