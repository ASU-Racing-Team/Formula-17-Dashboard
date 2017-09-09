import java.io.PrintStream;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;

public class SerialReader2
  implements SerialPortEventListener
{
  private boolean first = true;
  private int delay;
  
  public void serialEvent(SerialPortEvent evt)
  {
    if (evt.isRXCHAR()) {
      try
      {
        Integration.power = Boolean.valueOf(true);
        if (this.first) {
          this.delay = 180;
        }
        try
        {
          Thread.sleep(this.delay);
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }
        String buffer = new String();
        
        PortGlobals.dataRead = true;
        
        buffer = PortGlobals.serialPort2.readHexString(19);
        
        System.out.println(buffer);
        
        String trimmed = buffer.replace(" ", "");
        System.out.println(trimmed);
        
        String accxbs = trimmed.substring(0, 4);
        Integration.accX = (Integer.valueOf(accxbs, 16).intValue() - 32768) * 6.103515625E-5D;
        String accYbs = trimmed.substring(4, 8);
        Integration.accY = (Integer.valueOf(accYbs, 16).intValue() - 32768) * 6.103515625E-5D;
        String accZbs = trimmed.substring(8, 12);
        Integration.accZ = (Integer.valueOf(accZbs, 16).intValue() - 32768) * 6.103515625E-5D;
        String gyroXbs = trimmed.substring(12, 16);
        Integration.gyroX = (Integer.valueOf(gyroXbs, 16).intValue() - 32768) * 0.0152587890625D;
        String gyroYbs = trimmed.substring(16, 20);
        Integration.gyroY = (Integer.valueOf(gyroYbs, 16).intValue() - 32768) * 0.0152587890625D;
        String gyroZbs = trimmed.substring(20, 24);
        Integration.gyroZ = (Integer.valueOf(gyroZbs, 16).intValue() - 32768) * 0.0152587890625D;
        
        String susp1bs = trimmed.substring(24, 26);
        Integration.susp1 = 25 - Integer.valueOf(susp1bs, 16).intValue();
        String susp2bs = trimmed.substring(26, 28);
        Integration.susp2 = 25 - Integer.valueOf(susp2bs, 16).intValue();
        String susp3bs = trimmed.substring(28, 30);
        Integration.susp3 = 25 - Integer.valueOf(susp3bs, 16).intValue();
        String susp4bs = trimmed.substring(30, 32);
        Integration.susp4 = 25 - Integer.valueOf(susp4bs, 16).intValue();
        String aerokits = trimmed.substring(32, 34);
        Integration.adk = Integer.valueOf(aerokits, 16).intValue();
        String speeds = trimmed.substring(34, 36);
        Integration.speed = Integer.valueOf(speeds, 16).intValue();
        String steerings = trimmed.substring(36, 38);
        Integration.steering = 180 - Integer.valueOf(steerings, 16).intValue();
        
        System.out.println(Integration.accX);
        System.out.println(Integration.accY);
        System.out.println(Integration.accZ);
        
        System.out.println(Integration.gyroX);
        System.out.println(Integration.gyroY);
        System.out.println(Integration.gyroZ);
        
        System.out.println(Integration.susp1);
        System.out.println(Integration.susp2);
        System.out.println(Integration.susp3);
        System.out.println(Integration.susp4);
        System.out.println(Integration.adk);
        System.out.println(Integration.speed);
        System.out.println(Integration.steering);
        
        this.first = false;
        this.delay = 180;
        PortController.sendCommand2(10);
      }
      catch (Exception e)
      {
        System.out.print("Failed to read data2.\n");
        System.out.println(e);
        PortController.sendCommand2(10);
      }
    }
  }
}
