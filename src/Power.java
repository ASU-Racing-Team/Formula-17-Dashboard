import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintStream;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Power
  extends JLabel
{
  private BufferedImage ON;
  private BufferedImage OFF;
  private String onPath;
  private String offPath;
  private Boolean powerState = Boolean.valueOf(false);
  
  public Power(String mfinePath, String mWarningPath)
  {
    this.onPath = mfinePath;
    this.offPath = mWarningPath;
    
    setOpaque(false);
    setLayout(null);
    setBackground(new Color(0, 0, 100, 100));
    setLocation(10, 353);
    setSize(52, 52);
    try
    {
      this.ON = ImageIO.read(getClass().getResource(this.onPath));
      this.OFF = ImageIO.read(getClass().getResource(this.offPath));
    }
    catch (IOException e)
    {
      System.out.println("Unable to fetch image.");
      e.printStackTrace();
    }
    ActionListener listener = new ActionListener()
    {
      public void actionPerformed(ActionEvent ae)
      {
        if (Integration.power.booleanValue()) {
          Power.this.powerState = Boolean.valueOf(true);
        } else if (!Integration.power.booleanValue()) {
          Power.this.powerState = Boolean.valueOf(false);
        }
        Power.this.repaint();
      }
    };
    Timer timer = new Timer(500, listener);
    timer.start();
    
    setSize(new Dimension(this.ON.getWidth(), this.ON.getHeight()));
  }
  
  RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
  
  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    if (this.powerState.booleanValue()) {
      g2.drawImage(this.ON, 0, 0, null);
    } else if (!this.powerState.booleanValue()) {
      g2.drawImage(this.OFF, 0, 0, null);
    }
  }
}
