import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;

public class PortDetector
  extends Timer
{
  public String currentPort = PortGlobals.currentPort;
  public Enumeration ports = PortGlobals.ports;
  public String[] portNames = PortGlobals.portNames;
  private TimerTask portCapture = new TimerTask()
  {
    public void run()
    {
      System.out.println("START!");
      
      PortController.connect();
      System.out.println("END!");
    }
  };
  
  public PortDetector()
  {
    scheduleAtFixedRate(this.portCapture, 0L, 500L);
  }
}
