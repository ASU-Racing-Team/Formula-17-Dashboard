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

public class Indicator
  extends JLabel
{
  private BufferedImage Safe;
  private BufferedImage Warning;
  private BufferedImage Manual;
  private String safePath;
  private String warningPath;
  private String manualPath;
  private Boolean isSafe = Boolean.valueOf(false);
  private Boolean isMan = Boolean.valueOf(false);
  private String indicatorType;
  public int rTemp = 0;
  public int rFuel = 0;
  public int rADK = 0;
  
  public Indicator(String mfinePath, String mWarningPath, String mManualPath, final String type)
  {
    this.safePath = mfinePath;
    this.warningPath = mWarningPath;
    this.manualPath = mManualPath;
    this.indicatorType = type;
    
    setOpaque(false);
    setLayout(null);
    setBackground(new Color(0, 0, 100, 100));
    try
    {
      this.Safe = ImageIO.read(getClass().getResource(this.safePath));
      this.Warning = ImageIO.read(getClass().getResource(this.warningPath));
      this.Manual = ImageIO.read(getClass().getResource(this.manualPath));
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
        Indicator.this.isSafe = Boolean.valueOf(!Indicator.this.isSafe.booleanValue());
        String str;
        switch ((str = type).hashCode())
        {
        case 96424: 
          if (str.equals("adk")) {}
          break;
        case 3154358: 
          if (str.equals("fuel")) {
            break;
          }
        case 3556308: 
          if ((goto 243) && (str.equals("temp")))
          {
            if (Integration.clt > 92.0D)
            {
              Indicator.this.isSafe = Boolean.valueOf(false);
              break label254;
            }
            Indicator.this.isSafe = Boolean.valueOf(true);
            break label254;
            if (Integration.ffuel < 10)
            {
              Indicator.this.isSafe = Boolean.valueOf(false);
              break label254;
            }
            Indicator.this.isSafe = Boolean.valueOf(true);
            break label254;
            if (Integration.adk == 0)
            {
              Indicator.this.isMan = Boolean.valueOf(false);Indicator.this.isSafe = Boolean.valueOf(false);
            }
            else if (Integration.adk == 21)
            {
              Indicator.this.isMan = Boolean.valueOf(false);Indicator.this.isSafe = Boolean.valueOf(true);
            }
          }
          break;
        }
        Indicator.this.isSafe = Boolean.valueOf(true);
        label254:
        Indicator.this.repaint();
      }
    };
    Timer timer = new Timer(500, listener);
    timer.start();
    
    setSize(new Dimension(this.Safe.getWidth(), this.Safe.getHeight()));
  }
  
  RenderingHints hints = new RenderingHints(
    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
  
  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    if ((this.isSafe.booleanValue()) && (!this.isMan.booleanValue())) {
      g2.drawImage(this.Safe, 0, 0, null);
    } else if ((!this.isSafe.booleanValue()) && (!this.isMan.booleanValue())) {
      g2.drawImage(this.Warning, 0, 0, null);
    } else if ((!this.isSafe.booleanValue()) && (this.isMan.booleanValue())) {
      g2.drawImage(this.Manual, 0, 0, null);
    }
  }
}
