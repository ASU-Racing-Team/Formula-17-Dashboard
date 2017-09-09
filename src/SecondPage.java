import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListener;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class SecondPage
{
  BackgroundPanel dashboard;
  private JLabel CLTtemp = new JLabel();
  private JLabel carSpeed = new JLabel();
  private JLabel RPM = new JLabel();
  private JLabel Gear = new JLabel();
  public static JFrame frame = new JFrame();
  
  public void DisplayGUI(GpioPinDigitalInput mybtn)
  {
    this.dashboard = new BackgroundPanel("/SecondPage/Background with Text and Bar.png", "/SecondPage/Background with Text and Bar.png");
    frame.setSize(800, 480);
    frame.setContentPane(this.dashboard);
    frame.setResizable(false);
    frame.setVisible(true);
    Font displayFont = new Font("digital-7", 1, 60);
    
    this.CLTtemp.setFont(displayFont);
    this.CLTtemp.setForeground(new Color(190, 30, 45));
    this.dashboard.add(this.CLTtemp, Integer.valueOf(1), 0);
    this.CLTtemp.setSize(200, 200);
    this.CLTtemp.setLocation(388, 175);
    
    this.carSpeed.setFont(displayFont);
    this.carSpeed.setForeground(new Color(190, 30, 45));
    this.dashboard.add(this.carSpeed);
    this.carSpeed.setSize(300, 200);
    this.carSpeed.setLocation(299, -50);
    
    this.RPM.setFont(displayFont);
    this.RPM.setForeground(new Color(190, 30, 45));
    this.dashboard.add(this.RPM);
    this.RPM.setSize(300, 200);
    this.RPM.setLocation(298, 28);
    
    this.Gear.setFont(displayFont);
    this.Gear.setForeground(new Color(190, 30, 45));
    this.dashboard.add(this.Gear);
    this.Gear.setSize(200, 200);
    this.Gear.setLocation(323, 102);
    
    Indicator ADK = Integration.secADK;
    this.dashboard.add(ADK, Integer.valueOf(7), 0);
    ADK.setLocation(565, 265);
    
    TPSMeter throttle = Integration.secthrottle;
    this.dashboard.add(throttle, Integer.valueOf(8), 0);
    throttle.setLocation(121, 335);
    throttle.throttleRatio.setLocation(145, 12);
    
    ActionListener listener = new ActionListener()
    {
      public void actionPerformed(ActionEvent ae)
      {
        SecondPage.this.CLTtemp.setText(Integration.clt + " C");
        SecondPage.this.carSpeed.setText(Integration.speed + " KM/H");
        SecondPage.this.RPM.setText(Integration.RPM + " RPM");
        SecondPage.this.Gear.setText(Integration.gear);
      }
    };
    Timer timer = new Timer(10, listener);
    timer.start();
    
    PageIcon pi = new PageIcon();
    pi.toggle.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Dashboard.frame.setVisible(true);
        SecondPage.frame.dispose();
      }
    });
    mybtn.addListener(new GpioPinListener[] { new GpioPinListenerDigital()
    {
      public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event)
      {
        if ((event.getState() == PinState.LOW) && (Integration.PageNumber == 2))
        {
          Integration.PageNumber = 1;
          
          Dashboard.frame.setVisible(true);
          
          SecondPage.frame.dispose();
        }
        else {}
      }
    } });
    this.dashboard.add(pi.toggle, Integer.valueOf(9), 0);
  }
}
