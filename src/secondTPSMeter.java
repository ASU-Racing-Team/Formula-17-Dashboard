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
import javax.swing.JLayeredPane;
import javax.swing.Timer;

class secondTPSMeter
  extends JLayeredPane
{
  private TPSBar throttleBar = new TPSBar("/SecondPage/Progress Bar.png");
  private ProgressRatio throttleRatio = new ProgressRatio(30);
  
  public secondTPSMeter()
  {
    setSize(500, 500);
    setLocation(121, 335);
    setOpaque(false);
    setBackground(new Color(0, 100, 0, 100));
    add(this.throttleBar, Integer.valueOf(1), 0);
    setLayout(null);
    this.throttleBar.setLocation(0, 0);
    
    add(this.throttleRatio, Integer.valueOf(2), 0);
    this.throttleRatio.setLocation(145, 12);
    
    ActionListener listener = new ActionListener()
    {
      public void actionPerformed(ActionEvent ae)
      {
        if ((secondTPSMeter.this.throttleBar.counter <= secondTPSMeter.this.throttleBar.maxBarWidth) && (!secondTPSMeter.this.throttleBar.isEnd.booleanValue()))
        {
          secondTPSMeter.this.throttleBar.counter += 1;
          secondTPSMeter.this.throttleBar.ratio = (secondTPSMeter.this.throttleBar.counter / secondTPSMeter.this.throttleBar.maxBarWidth * 100.0F);
          if (secondTPSMeter.this.throttleBar.counter == secondTPSMeter.this.throttleBar.progressBar.getWidth()) {
            secondTPSMeter.this.throttleBar.isEnd = Boolean.valueOf(true);
          }
        }
        if ((secondTPSMeter.this.throttleBar.counter >= 0) && (secondTPSMeter.this.throttleBar.isEnd.booleanValue()))
        {
          secondTPSMeter.this.throttleBar.counter -= 1;
          secondTPSMeter.this.throttleBar.ratio = (secondTPSMeter.this.throttleBar.counter / secondTPSMeter.this.throttleBar.maxBarWidth * 100.0F);
          if (secondTPSMeter.this.throttleBar.counter == 0) {
            secondTPSMeter.this.throttleBar.isEnd = Boolean.valueOf(false);
          }
        }
        secondTPSMeter.this.throttleRatio.value = ((int)secondTPSMeter.this.throttleBar.ratio);
        if (secondTPSMeter.this.throttleRatio.value < 50) {
          secondTPSMeter.this.throttleRatio.setForeground(Color.BLACK);
        } else if (secondTPSMeter.this.throttleRatio.value > 50) {
          secondTPSMeter.this.throttleRatio.setForeground(Color.WHITE);
        }
        secondTPSMeter.this.throttleRatio.setText(secondTPSMeter.this.throttleRatio.value + "%");
        secondTPSMeter.this.throttleBar.setSize(new Dimension(secondTPSMeter.this.throttleBar.progressWidth[secondTPSMeter.this.throttleBar.counter], 
          secondTPSMeter.this.throttleBar.progressBar.getHeight()));
      }
    };
    Timer timer = new Timer(10, listener);
    timer.start();
  }
  
  class secondTPSBar
    extends JLabel
  {
    public BufferedImage progressBar;
    public int counter = 0;
    public Boolean isEnd = Boolean.valueOf(false);
    public float ratio = 0.0F;
    public int maxBarWidth;
    public int maxBarHeight;
    public int[] progressWidth = new int['?'];
    
    public secondTPSBar()
    {
      setOpaque(false);
      setLayout(null);
      setLocation(245, 310);
      setBackground(new Color(0, 0, 100, 100));
      try
      {
        this.progressBar = ImageIO.read(getClass().getResource("/SecondPage/Progress Bar.png"));
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
    
    RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
    protected void paintComponent(Graphics g)
    {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      g2.drawImage(this.progressBar, 0, 0, null);
    }
  }
}
