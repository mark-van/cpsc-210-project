package model;


// represents a task with a victim, title, and url
public class Task {

    private Victim victim;   // victim this task is trying to make amends with
    private String title;    // title of task
    private String url;      // url directing user to website concerning this task

    //EFFECTS: constructs a task with victim, title and url
    public Task(Victim victim, String title, String url) {
        this.victim = victim;
        this.title = title;
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
}
