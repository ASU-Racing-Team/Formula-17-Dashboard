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

public class Gear
  extends JLabel
{
  private BufferedImage[] imageArr = new BufferedImage[5];
  private int counter = 0;
  
  public Gear()
  {
    setOpaque(false);
    setLayout(null);
    setLocation(348, 101);
    setBackground(new Color(0, 0, 100, 100));
    try
    {
      for (int i = 0; i <= 4; i++) {
        switch (i)
        {
        case 0: 
          this.imageArr[i] = ImageIO.read(getClass().getResource("/Images/Gear/N.png"));
          break;
        case 1: 
          this.imageArr[i] = ImageIO.read(getClass().getResource("/Images/Gear/1.png"));
          break;
        case 2: 
          this.imageArr[i] = ImageIO.read(getClass().getResource("/Images/Gear/2.png"));
          break;
        case 3: 
          this.imageArr[i] = ImageIO.read(getClass().getResource("/Images/Gear/3.png"));
          break;
        case 4: 
          this.imageArr[i] = ImageIO.read(getClass().getResource("/Images/Gear/4.png"));
          break;
        case 5: 
          this.imageArr[i] = ImageIO.read(getClass().getResource("/Images/Gear/5.png"));
        }
      }
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
        Gear.this.counter = Integration.gear;
        Gear.this.repaint();
      }
    };
    Timer timer = new Timer(750, listener);
    timer.start();
    
    setSize(new Dimension(this.imageArr[4].getWidth(), this.imageArr[4].getHeight()));
  }
  
  RenderingHints hints = new RenderingHints(
    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
  
  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    g2.drawImage(this.imageArr[this.counter], 0, 0, null);
  }
}
