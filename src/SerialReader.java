import java.io.PrintStream;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;

public class SerialReader
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
          this.delay = 400;
        }
        try
        {
          Thread.sleep(this.delay);
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }
        PortGlobals.dataRead = true;
        
        String buffer = PortGlobals.serialPort.readHexString();
        
        String trimmed = buffer.replace(" ", "");
        System.out.println(trimmed);
        String rpm = trimmed.substring(12, 16);
        int rpmS = Integer.valueOf(rpm, 16).intValue();
        Integration.RPM = rpmS;
        System.out.println("RPM= " + Integration.RPM);
        
        String coolant = trimmed.substring(44, 48);
        System.out.println(coolant);
        long cltI = Long.valueOf(coolant, 16).longValue();
        System.out.println(cltI);
        
        Integration.clt = cltI / 10L;
        System.out.println("Coolant= " + Integration.clt);
        
        String tps = trimmed.substring(48, 52);
        int tpsI = Integer.valueOf(tps, 16).intValue();
	if(tpsI > 32768){
		// binary representation of negative
		Integration.tps = 0;
	}else if( tpsI > 100){
		// positive bigger than 100
		Integration.tps = 100;
	}else{
		// between 0 -> 100
		Integration.tps = tpsI / 10;	
	}
        Integration.tps = tpsI / 10;
        System.out.println("TPS= " + Integration.tps);
        
        this.first = false;
        this.delay = 180;
        PortController.sendCommand(10);
      }
      catch (Exception e)
      {
        System.out.print("Failed to read data.\n");
        System.out.println(e);
        PortController.sendCommand(10);
      }
    }
  }
}
