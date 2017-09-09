import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

public class TPSMeter
  extends JLayeredPane
{
  public TPSBar throttleBar;
  public ProgressRatio throttleRatio;
  public int progress;
  
  public TPSMeter(String path, int fontSize)
  {
    this.throttleBar = new TPSBar(path);
    this.throttleRatio = new ProgressRatio(fontSize);
    setSize(this.throttleBar.maxBarWidth, this.throttleBar.maxBarHeight);
    setOpaque(false);
    setBackground(new Color(0, 100, 0, 100));
    add(this.throttleBar, Integer.valueOf(1), 0);
    setLayout(null);
    this.throttleBar.setLocation(0, 0);
    add(this.throttleRatio, Integer.valueOf(2), 0);
    
    ActionListener listener = new ActionListener()
    {
      public void actionPerformed(ActionEvent ae)
      {
        TPSMeter.this.throttleRatio.value = ((int)TPSMeter.this.throttleBar.ratio);
        if (Integration.tps < 50) {
          TPSMeter.this.throttleRatio.setForeground(Color.BLACK);
        } else if (Integration.tps > 50) {
          TPSMeter.this.throttleRatio.setForeground(Color.WHITE);
        }
        TPSMeter.this.progress = (Integration.tps * TPSMeter.this.throttleBar.maxBarWidth);
        TPSMeter.this.throttleRatio.setText(Integration.tps + "%");
        TPSMeter.this.throttleBar.setSize(new Dimension(TPSMeter.this.progress / 100, TPSMeter.this.throttleBar.progressBar.getHeight()));
      }
    };
    Timer timer = new Timer(10, listener);
    timer.start();
  }
}
