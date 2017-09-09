import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import jssc.SerialPort;

public class PortGlobals
{
  public static boolean found = false;
  public static boolean connected = false;
  public static boolean dataRead = false;
  public static String currentPort;
  public static Enumeration ports = null;
  public static PortDetector serialDetector = new PortDetector();
  public static InputStream input = null;
  public static OutputStream output = null;
  public static SerialReader serialEvent = new SerialReader();
  public static SerialReader2 serialEvent2 = new SerialReader2();
  public static boolean stopSearch = false;
  public static String[] portNames;
  public static SerialPort serialPort = new SerialPort("/dev/ttyUSB0");
  public static SerialPort serialPort2 = new SerialPort("/dev/ttyUSB0");
}
