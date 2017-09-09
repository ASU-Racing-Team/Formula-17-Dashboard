import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintStream;
import javax.imageio.ImageIO;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

public class BackgroundPanel
  extends JLayeredPane
{
  private BufferedImage defaultBack;
  private BufferedImage gearShiftBack;
  private BufferedImage appliedBack;
  
  public BackgroundPanel(String defPath, String shiftPath)
  {
    try
    {
      this.defaultBack = ImageIO.read(getClass().getResource(defPath));
      this.gearShiftBack = ImageIO.read(getClass().getResource(shiftPath));
      this.appliedBack = this.defaultBack;
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
        if ((Integration.RPM >= 11000.0D) && (BackgroundPanel.this.appliedBack == BackgroundPanel.this.defaultBack)) {
          BackgroundPanel.this.appliedBack = BackgroundPanel.this.gearShiftBack;
        } else if ((Integration.RPM >= 11000.0D) && (BackgroundPanel.this.appliedBack == BackgroundPanel.this.gearShiftBack)) {
          BackgroundPanel.this.appliedBack = BackgroundPanel.this.defaultBack;
        } else if (Integration.RPM < 11000.0D) {
          BackgroundPanel.this.appliedBack = BackgroundPanel.this.defaultBack;
        }
      }
    };
    Timer timer = new Timer(500, listener);
    timer.start();
  }
  
  RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
  
  public Dimension getPreferredSize()
  {
    return this.appliedBack == null ? new Dimension(400, 300) : new Dimension(this.appliedBack.getWidth(), this.appliedBack.getHeight());
  }
  
  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    g.drawImage(this.appliedBack, 0, 0, this);
    repaint();
  }
}
