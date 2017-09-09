import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class FeedParameters
{
  public static enum Period
  {
    T10m(
    
      Integer.valueOf(10)),  T15m(
    
      Integer.valueOf(15)),  T20m(
    
      Integer.valueOf(20)),  T30m(
    
      Integer.valueOf(30)),  T1h(
    
      Integer.valueOf(60)),  T4h(
    
      Integer.valueOf(240)),  T12h(
    
      Integer.valueOf(720)),  T24h(
    
      Integer.valueOf(1440));
    
    private final Integer minutes;
    
    private Period(Integer minutes)
    {
      this.minutes = minutes;
    }
    
    Integer minutes()
    {
      return this.minutes;
    }
  }
  
  HashMap<String, Object> fields = new HashMap();
  private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
  
  public void results(Integer results)
  {
    if (results.intValue() > 8000) {
      throw new IllegalArgumentException("Feed cannot return more than 8000 results.");
    }
    this.fields.put("results", results);
  }
  
  public void days(Integer days)
  {
    this.fields.put("days", days);
  }
  
  public void start(Date date)
  {
    this.fields.put("start", this.formatter.format(date));
  }
  
  public void end(Date date)
  {
    this.fields.put("end", this.formatter.format(date));
  }
  
  public void offset(Integer hours)
  {
    this.fields.put("offset", hours);
  }
  
  public void status(Boolean include)
  {
    this.fields.put("status", include);
  }
  
  public void location(Boolean include)
  {
    this.fields.put("location", include);
  }
  
  public void min(Double value)
  {
    this.fields.put("min", value);
  }
  
  public void max(Double value)
  {
    this.fields.put("max", value);
  }
  
  public void round(Integer places)
  {
    this.fields.put("round", places);
  }
  
  public void timescale(FeedParameters.Period t)
  {
    this.fields.put("timescale", t.minutes());
  }
  
  public void sum(FeedParameters.Period t)
  {
    this.fields.put("sum", t.minutes());
  }
  
  public void average(FeedParameters.Period t)
  {
    this.fields.put("average", t.minutes());
  }
  
  public void median(FeedParameters.Period t)
  {
    this.fields.put("median", t.minutes());
  }
}
