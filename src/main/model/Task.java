package model;

public class Task {

    private Victim victim;
    private String title;
    private String url;

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
