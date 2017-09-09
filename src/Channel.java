import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.mashape.unirest.request.body.MultipartBody;
import java.util.HashMap;

public class Channel
{
  private String APIURL = "http://api.thingspeak.com";
  private static final String APIHEADER = "X-THINGSPEAKAPIKEY";
  private final Integer channelId;
  private String readAPIKey;
  private String writeAPIKey;
  private final Boolean isPublic;
  private final HashMap<String, Object> fields = new HashMap();
  private final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").create();
  
  public Channel(Integer channelId)
  {
    this.isPublic = Boolean.valueOf(true);
    this.channelId = channelId;
  }
  
  public Channel(Integer channelId, String writeKey)
  {
    this.isPublic = Boolean.valueOf(true);
    this.channelId = channelId;
    this.writeAPIKey = writeKey;
  }
  
  public Channel(Integer channelId, String writeKey, String readKey)
  {
    this.channelId = channelId;
    this.readAPIKey = readKey;
    this.writeAPIKey = writeKey;
    this.isPublic = Boolean.valueOf(false);
  }
  
  private String thingRequest(String url)
    throws UnirestException, ThingSpeakException
  {
    GetRequest request = Unirest.get(url);
    if (!this.isPublic.booleanValue()) {
      request.field("key", this.readAPIKey);
    }
    HttpResponse<JsonNode> response = request.asJson();
    if (response.getCode() != 200) {
      throw new ThingSpeakException("Request failed with code " + response.getCode());
    }
    return ((JsonNode)response.getBody()).toString();
  }
  
  private String thingRequest(String url, FeedParameters options)
    throws UnirestException, ThingSpeakException
  {
    GetRequest request = Unirest.get(url);
    if (!this.isPublic.booleanValue()) {
      request.field("key", this.readAPIKey);
    }
    request.fields(options.fields);
    HttpResponse<JsonNode> response = request.asJson();
    if (response.getCode() != 200) {
      throw new ThingSpeakException("Request failed with code " + response.getCode());
    }
    return ((JsonNode)response.getBody()).toString();
  }
  
  public void setUrl(String url)
  {
    this.APIURL = url;
  }
  
  public Integer update(Entry entry)
    throws UnirestException, ThingSpeakException
  {
    HttpResponse<String> response = Unirest.post(this.APIURL + "/update")
      .header("X-THINGSPEAKAPIKEY", this.writeAPIKey)
      .header("Connection", "close")
      .fields(entry.getUpdateMap())
      .asString();
    if (response.getCode() != 200) {
      throw new ThingSpeakException("Request failed with code " + response.getCode());
    }
    if (((String)response.getBody()).equals("0")) {
      throw new ThingSpeakException("Update failed.");
    }
    return Integer.valueOf(Integer.parseInt((String)response.getBody()));
  }
  
  public Feed getChannelFeed()
    throws UnirestException, ThingSpeakException
  {
    String url = this.APIURL + "/channels/" + this.channelId + "/feed.json";
    return (Feed)this.gson.fromJson(thingRequest(url), Feed.class);
  }
  
  public Feed getChannelFeed(FeedParameters options)
    throws UnirestException, ThingSpeakException
  {
    String url = this.APIURL + "/channels/" + this.channelId + "/feed.json";
    return (Feed)this.gson.fromJson(thingRequest(url, options), Feed.class);
  }
  
  public Entry getLastChannelEntry()
    throws UnirestException, ThingSpeakException
  {
    String url = this.APIURL + "/channels/" + this.channelId + "/feed/last.json";
    return (Entry)this.gson.fromJson(thingRequest(url), Entry.class);
  }
  
  public Entry getLastChannelEntry(FeedParameters options)
    throws UnirestException, ThingSpeakException
  {
    String url = this.APIURL + "/channels/" + this.channelId + "/feed/last.json";
    return (Entry)this.gson.fromJson(thingRequest(url, options), Entry.class);
  }
  
  public Feed getFieldFeed(Integer fieldId)
    throws UnirestException, ThingSpeakException
  {
    String url = this.APIURL + "/channels/" + this.channelId + "/field/" + fieldId + ".json";
    return (Feed)this.gson.fromJson(thingRequest(url), Feed.class);
  }
  
  public Feed getFieldFeed(Integer fieldId, FeedParameters options)
    throws UnirestException, ThingSpeakException
  {
    String url = this.APIURL + "/channels/" + this.channelId + "/field/" + fieldId + ".json";
    return (Feed)this.gson.fromJson(thingRequest(url, options), Feed.class);
  }
  
  public Entry getLastFieldEntry(Integer fieldId)
    throws UnirestException, ThingSpeakException
  {
    String url = this.APIURL + "/channels/" + this.channelId + "/field/" + fieldId + "/last.json";
    return (Entry)this.gson.fromJson(thingRequest(url), Entry.class);
  }
  
  public Entry getLastFieldEntry(Integer fieldId, FeedParameters options)
    throws UnirestException, ThingSpeakException
  {
    String url = this.APIURL + "/channels/" + this.channelId + "/field/" + fieldId + "/last.json";
    return (Entry)this.gson.fromJson(thingRequest(url, options), Entry.class);
  }
  
  public Feed getStatusFeed()
    throws UnirestException, ThingSpeakException
  {
    String url = this.APIURL + "/channels/" + this.channelId + "/status.json";
    return (Feed)this.gson.fromJson(thingRequest(url), Feed.class);
  }
  
  public Feed getStatusFeed(FeedParameters options)
    throws UnirestException, ThingSpeakException
  {
    String url = this.APIURL + "/channels/" + this.channelId + "/status.json";
    return (Feed)this.gson.fromJson(thingRequest(url, options), Feed.class);
  }
  
  public void getUserInfo()
  {
    throw new UnsupportedOperationException("Not implemented.");
  }
  
  public void getUserChannels()
  {
    throw new UnsupportedOperationException("Not implemented.");
  }
  
  public boolean isAvailable()
  {
    String url = this.APIURL + "/channels/" + this.channelId + "/feed.json" + "?key=" + this.readAPIKey + "&results=0";
    try
    {
      thingRequest(url);
    }
    catch (UnirestException|ThingSpeakException e)
    {
      return false;
    }
    return true;
  }
}
