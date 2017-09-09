import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import jssc.SerialPort;
import jssc.SerialPortException;

public class PortController
{
  private static int TIMEOUT = 2000;
  public static SerialPort serialPort = PortGlobals.serialPort;
  public static SerialPort serialPort2 = PortGlobals.serialPort2;
  
  public static void connect()
  {
    try
    {
      serialPort2.openPort();
      serialPort2.setParams(115200, 
        8, 
        1, 
        0);
      Thread.sleep(5000L);
      sendCommand2(10);
      PortGlobals.connected = true;
      PortGlobals.serialDetector.cancel();
      PortGlobals.serialDetector.purge();
      PortGlobals.stopSearch = true;
      
      serialPort2.addEventListener(PortGlobals.serialEvent2);
    }
    catch (SerialPortException e)
    {
      e.printStackTrace();
      System.out.println("Port is open");
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }
  
  public static void disconnect()
  {
    try
    {
      System.out.println("Disconnect Starts");
      
      System.out.println("Listener Removed");
      PortGlobals.input.close();
      PortGlobals.output.close();
      
      System.out.print("Port Closed.\n");
    }
    catch (Exception e)
    {
      System.out.print("Failed to close Port.\n");
    }
  }
  
  public static void sendCommand(int mcommand)
  {
    try
    {
      PortGlobals.serialPort.writeBytes("A".getBytes());
    }
    catch (SerialPortException e)
    {
      e.printStackTrace();
      System.out.println("Failed to send Command!");
    }
  }
  
  public static void sendCommand2(int mcommand)
  {
    try
    {
      PortGlobals.serialPort2.writeBytes("A".getBytes());
    }
    catch (SerialPortException e)
    {
      e.printStackTrace();
      System.out.println("Failed to send Command!");
    }
  }
}
