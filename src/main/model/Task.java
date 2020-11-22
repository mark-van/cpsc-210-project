package model;


import org.json.JSONObject;
import persistence.Writable;

// represents a task with a victim, title, and url
public class Task implements Writable {

    private Victim victim;   // victim this task is trying to make amends with
    private String title;    // title of task
    private String url;      // url directing user to website concerning this task

    //EFFECTS: constructs a task with victim, title and url
    public Task(Victim victim, String title, String url) {
        this.victim = victim;
        this.title = title;
        //this.url = url;
        try {
            checkURL(url);
        } catch (InvalidUrlException e) {
            this.url = "https://reflectionsfromaredhead.com/make-the-world-a-better-place/";
        }
    }

    //MODIFIES: this
    //EFFECTS: throws InvalidUrlException when the given string is not a valid website. Otherwise set task's url
    //         to given string.
    public void checkURL(String url) throws InvalidUrlException {
        if (!(url.contains(".ca") || url.contains(".com") || url.contains(".org")
                || url.contains(".net") || url.contains(".int") || url.contains(".edu"))) {
            throw new InvalidUrlException();
        }
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public Victim getVictim() {
        return victim;
    }

    //Based on JASON demo code
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("url", url);
        json.put("victim name", victim.getName());  //Vciticim iw an object but im not sure if this works
        json.put("victim message", victim.getMessage());
        return json;
    }
}
