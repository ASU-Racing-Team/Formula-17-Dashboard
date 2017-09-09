import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Pointer
  extends JLabel
{
  Dimension dim = new Dimension(4, 80);
  private float[] testArray = new float['?'];
  int counter = 0;
  int angle = 65386;
  public int speed;
  String pointerType;
  
  public Pointer(String type)
  {
    this.pointerType = type;
    setOpaque(false);
    setLayout(null);
    
    setSize(160, 160);
    setBackground(new Color(0, 0, 100, 100));
    for (int i = 0; i <= 360; i++) {
      this.testArray[i] = i;
    }
    ActionListener listener = new ActionListener()
    {
      public void actionPerformed(ActionEvent ae)
      {
        Pointer.this.angle = ((int)(-150.0D + 1.5D * Integration.speed));
        String str;
        switch ((str = Pointer.this.pointerType).hashCode())
        {
        case 113135: 
          if (str.equals("rpm")) {
            break;
          }
        case 109641799: 
          if ((goto 124) && (str.equals("speed")))
          {
            Pointer.this.angle = ((int)(-150.0D + 1.5D * Integration.speed));
            break label134;
            Pointer.this.angle = ((int)(-150.0D + 0.01875D * Integration.RPM));
          }
          break;
        }
        Pointer.this.angle = 65386;
        label134:
        Pointer.this.repaint();
      }
    };
    Timer timer = new Timer(10, listener);
    timer.start();
  }
  
  RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
  
  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g.create();
    g2.setColor(Color.YELLOW);
    g2.setRenderingHints(this.hints);
    
    g2.translate(78, 0);
    g2.rotate(Math.toRadians(this.angle), (int)this.dim.getWidth() / 2, (int)this.dim.getHeight());
    g2.fillRect(0, 0, (int)this.dim.getWidth(), (int)this.dim.getHeight());
  }
}
