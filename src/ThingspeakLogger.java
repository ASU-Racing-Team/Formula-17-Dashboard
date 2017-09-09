import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ThingspeakLogger
{
  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
  String writeKey = "34GPJ2KWKTKJ59UM";
  String readKey = "6N1ONQK8DLFY8KJB";
  Channel c = new Channel(Integer.valueOf(287843), this.writeKey);
  
  public ThingspeakLogger()
  {
    logData();
  }
  
  private void writeValues()
  {
    Entry e = new Entry();
    e.setField(Integer.valueOf(1), Double.toString(Integration.RPM));
    e.setField(Integer.valueOf(2), Double.toString(Integration.tps));
    e.setField(Integer.valueOf(3), Double.toString(Integration.clt));
    e.setField(Integer.valueOf(4), Double.toString(Integration.speed));
    try
    {
      this.c.update(e);
    }
    catch (UnirestException|ThingSpeakException localUnirestException) {}catch (Exception localException) {}
  }
  
  private void logData()
  {
    Runnable logger = new Runnable()
    {
      public void run()
      {
        ThingspeakLogger.this.writeValues();
      }
    };
    final ScheduledFuture<?> loggerHandle = this.scheduler.scheduleAtFixedRate(logger, 0L, 20L, TimeUnit.SECONDS);
    this.scheduler.schedule(new Runnable()
    {
      public void run()
      {
        loggerHandle.cancel(true);
      }
    }, 3600L, TimeUnit.SECONDS);
  }
}
