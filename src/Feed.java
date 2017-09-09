import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Feed
{
  private final Feed.ChannelInfo channel = new Feed.ChannelInfo(null);
  private final ArrayList<Entry> feeds = new ArrayList();
  
  public Date getChannelCreationDate()
  {
    return this.channel.created_at;
  }
  
  public String getChannelDescription()
  {
    return this.channel.description;
  }
  
  public String getFieldName(Integer field)
  {
    switch (field.intValue())
    {
    case 1: 
      return this.channel.field1;
    case 2: 
      return this.channel.field2;
    case 3: 
      return this.channel.field3;
    case 4: 
      return this.channel.field4;
    case 5: 
      return this.channel.field5;
    case 6: 
      return this.channel.field6;
    case 7: 
      return this.channel.field7;
    case 8: 
      return this.channel.field8;
    }
    throw new IllegalArgumentException("Invalid field.");
  }
  
  public Integer getChannelId()
  {
    return this.channel.id;
  }
  
  public Integer getChannelLastEntryId()
  {
    return this.channel.last_entry_id;
  }
  
  public String getChannelName()
  {
    return this.channel.name;
  }
  
  public Date getChannelUpdateDate()
  {
    return this.channel.updated_at;
  }
  
  public ArrayList<Entry> getEntryList()
  {
    return this.feeds;
  }
  
  public Map<Integer, Entry> getEntryMap()
  {
    HashMap<Integer, Entry> map = new HashMap();
    for (Entry entry : this.feeds) {
      map.put(entry.getEntryId(), entry);
    }
    return map;
  }
  
  public Entry getEntry(Integer id)
    throws ThingSpeakException
  {
    for (Entry entry : this.feeds) {
      if (entry.getEntryId().equals(id)) {
        return entry;
      }
    }
    throw new ThingSpeakException("Entry with ID " + id + " not found in feed.");
  }
  
  public Entry getChannelLastEntry()
    throws ThingSpeakException
  {
    return getEntry(this.channel.last_entry_id);
  }
  
  private class ChannelInfo
  {
    private Date created_at;
    private String description;
    private String field1;
    private String field2;
    private String field3;
    private String field4;
    private String field5;
    private String field6;
    private String field7;
    private String field8;
    private Integer id;
    private Integer last_entry_id;
    private String name;
    private Date updated_at;
    
    private ChannelInfo() {}
  }
}
