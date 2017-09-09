public class Integration
{
  public static Pointer speedPointer = new Pointer("speed");
  public static Pointer RPMPointer = new Pointer("rpm");
  public static Gear gearLvl = new Gear();
  public static Indicator temp = new Indicator("/Images/Temp Safe.png", "/Images/Temp Warning.png", "", "temp");
  public static Indicator ADK = new Indicator("/Images/ADK Safe.png", "/Images/ADK Warning.png", "/Images/ADK Manual.png", "adk");
  public static TPSMeter throttle = new TPSMeter("/Images/TPS Bar.png", 20);
  public static Power connection = new Power("/Images/PowerON.png", "/Images/PowerOFF.png");
  public static Indicator secADK = new Indicator("/SecondPage/ADK Safe.png", "/SecondPage/ADK Warning.png", "/SecondPage/ADK Manual.png", "adk");
  public static TPSMeter secthrottle = new TPSMeter("/SecondPage/Progress Bar.png", 30);
  public static double speed = 0.0D;
  public static double clt = 0.0D;
  public static int gear = 0;
  public static double RPM = 0.0D;
  public static int tps = 0;
  public static int ffuel = 0;
  public static int adk = 0;
  public static int PageNumber = 1;
  public static Boolean SwitchInstanceExist = Boolean.valueOf(false);
  public static double accX = 0.0D;
  public static double accY = 0.0D;
  public static double accZ = 0.0D;
  public static double gyroX = 0.0D;
  public static double gyroY = 0.0D;
  public static double gyroZ = 0.0D;
  public static double magX = 0.0D;
  public static double magY = 0.0D;
  public static double magZ = 0.0D;
  public static double susp1 = 0.0D;
  public static double susp2 = 0.0D;
  public static double susp3 = 0.0D;
  public static double susp4 = 0.0D;
  public static double steering = 0.0D;
  public static Boolean power = Boolean.valueOf(false);
}
