import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListener;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Dashboard
{
  BackgroundPanel dashboard;
  float[] angleArray = new float['?'];
  public static JFrame frame = new JFrame();
  public static GpioController gpio = null;
  public static GpioPinDigitalInput myButton = null;
  GpioPinDigitalOutput HighPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27, PinState.HIGH);
  
  public void DisplayGUI(final GpioPinDigitalInput mybtn)
  {
    this.dashboard = new BackgroundPanel("/Images/DashboardBackground.png", "/Images/RPM Flashing Front.png");
    frame.setSize(800, 480);
    frame.setContentPane(this.dashboard);
    frame.setResizable(false);
    frame.setVisible(true);
    
    Circle speedCircle = new Circle();
    this.dashboard.add(speedCircle, 3);
    speedCircle.setLocation(138, 152);
    
    Pointer speedPointer = Integration.speedPointer;
    this.dashboard.add(speedPointer, 2);
    speedPointer.setLocation(67, 81);
    
    Circle RPMCircle = new Circle();
    this.dashboard.add(RPMCircle, 5);
    RPMCircle.setLocation(612, 152);
    
    Pointer RPMPointer = Integration.RPMPointer;
    this.dashboard.add(RPMPointer, 4);
    RPMPointer.setLocation(541, 81);
    
    Gear gearLvl = Integration.gearLvl;
    this.dashboard.add(gearLvl, 6);
    
    Indicator temp = Integration.temp;
    this.dashboard.add(temp, 7);
    temp.setLocation(312, 273);
    
    Indicator ADK = Integration.ADK;
    this.dashboard.add(ADK, 7);
    ADK.setLocation(394, 282);
    
    TPSMeter throttle = Integration.throttle;
    this.dashboard.add(throttle, 8);
    throttle.setLocation(245, 310);
    throttle.throttleRatio.setLocation(110, 61);
    
    PageIcon pi = new PageIcon();
    
    pi.toggle.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        SecondPage sc = new SecondPage();
        Dashboard.frame.dispose();
        
        sc.DisplayGUI(mybtn);
      }
    });
    mybtn.addListener(new GpioPinListener[] { new GpioPinListenerDigital()
    {
      public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event)
      {
        if ((event.getState() == PinState.HIGH) && (Integration.PageNumber == 1))
        {
          Integration.PageNumber = 2;
          SecondPage sc = new SecondPage();
          Dashboard.frame.setVisible(false);
          
          sc.DisplayGUI(mybtn);
        }
        else {}
      }
    } });
    this.dashboard.add(pi.toggle, 9);
    Power connectionState = Integration.connection;
    this.dashboard.add(connectionState, 10);
  }
  
  public static void main(String[] args)
  {
    SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
        if (!Integration.SwitchInstanceExist.booleanValue())
        {
          Dashboard.gpio = GpioFactory.getInstance();
          
          Dashboard.myButton = Dashboard.gpio.provisionDigitalInputPin(RaspiPin.GPIO_25, "my", PinPullResistance.PULL_DOWN);
          Integration.SwitchInstanceExist = Boolean.valueOf(true);
        }
        new Dashboard().DisplayGUI(Dashboard.myButton);
        new PortGlobals();
        FileLogger f = new FileLogger();
        ThingspeakLogger t = new ThingspeakLogger();
      }
    });
  }
}
