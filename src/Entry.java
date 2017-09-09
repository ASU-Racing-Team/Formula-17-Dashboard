import java.util.Date;
import java.util.HashMap;

public class Entry
{
  private Date created_at;
  private Integer entry_id;
  private String field1;
  private String field2;
  private String field3;
  private String field4;
  private String field5;
  private String field6;
  private String field7;
  private String field8;
  private Double latitude;
  private Double longitude;
  private Double elevation;
  private String status;
  private String twitter;
  private String tweet;
  private final HashMap<String, Object> updateMap = new HashMap();
  
  HashMap<String, Object> getUpdateMap()
  {
    return this.updateMap;
  }
  
  public Object getField(Integer field)
  {
    switch (field.intValue())
    {
    case 1: 
      return this.field1;
    case 2: 
      return this.field2;
    case 3: 
      return this.field3;
    case 4: 
      return this.field4;
    case 5: 
      return this.field5;
    case 6: 
      return this.field6;
    case 7: 
      return this.field7;
    case 8: 
      return this.field8;
    }
    throw new IllegalArgumentException("Invalid field.");
  }
  
  public void setField(Integer field, String value)
  {
    switch (field.intValue())
    {
    case 1: 
      this.field1 = value;
      this.updateMap.put("field1", value);
      return;
    case 2: 
      this.field2 = value;
      this.updateMap.put("field2", value);
      return;
    case 3: 
      this.field3 = value;
      this.updateMap.put("field3", value);
      return;
    case 4: 
      this.field4 = value;
      this.updateMap.put("field4", value);
      return;
    case 5: 
      this.field5 = value;
      this.updateMap.put("field5", value);
      return;
    case 6: 
      this.field6 = value;
      this.updateMap.put("field6", value);
      return;
    case 7: 
      this.field7 = value;
      this.updateMap.put("field7", value);
      return;
    case 8: 
      this.field8 = value;
      this.updateMap.put("field8", value);
      return;
    }
    throw new IllegalArgumentException("Invalid field.");
  }
  
  public Double getLatitude()
  {
    return this.latitude;
  }
  
  public void setLatitude(Double latitude)
  {
    this.latitude = latitude;
    this.updateMap.put("lat", latitude);
  }
  
  public Double getLongitude()
  {
    return this.longitude;
  }
  
  public void setLong(Double longitude)
  {
    this.longitude = longitude;
    this.updateMap.put("long", longitude);
  }
  
  public Double getElevation()
  {
    return this.elevation;
  }
  
  public void setElevation(Double elevation)
  {
    this.elevation = elevation;
    this.updateMap.put("elevation", elevation);
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
    this.updateMap.put("status", status);
  }
  
  public void setTwitter(String twitter)
  {
    this.twitter = twitter;
    this.updateMap.put("twitter", twitter);
  }
  
  public void setTweet(String tweet)
  {
    this.tweet = tweet;
    this.updateMap.put("tweet", tweet);
  }
  
  public void setCreated(Date created)
  {
    this.created_at = created;
    this.updateMap.put("created_at", created);
  }
  
  public Date getCreated()
  {
    return this.created_at;
  }
  
  public Integer getEntryId()
  {
    return this.entry_id;
  }
}
